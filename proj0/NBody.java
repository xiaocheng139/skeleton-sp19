import java.util.Scanner;

public class NBody {
    public static void main(String[] args) {
        // Read the variables in
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bodyArray = readBodies(filename);
        String image_path = "images/starfield.jpg";

        /* Enable double buffering */
        StdDraw.enableDoubleBuffering();

        // Drawing the background
        /* Set the scale */
        StdDraw.setScale(-radius, radius);

        /* Clears the drawing window. */
        StdDraw.clear();

        /* Draw the background */
        StdDraw.picture(-radius, radius, image_path);

        for (Body body: bodyArray)
        {
            body.draw();
        }

        /* Create animation*/
        for(double timer = 0; timer <= T; timer += dt)
        {
            double[] xForces = new double[bodyArray.length];
            double[] yForces = new double[bodyArray.length];

            for(int bodyIndex = 0; bodyIndex < bodyArray.length; bodyIndex++)
            {
             double netForceByX = bodyArray[bodyIndex].calcNetForceExertedByX(bodyArray);
             double netForceByY = bodyArray[bodyIndex].calcNetForceExertedByY(bodyArray);

             xForces[bodyIndex] = netForceByX;
             yForces[bodyIndex] = netForceByY;
             bodyArray[bodyIndex].update(dt, netForceByX, netForceByY);
            }

            StdDraw.picture(-radius, radius, image_path);
            for (Body body: bodyArray)
            {
                body.draw();
            }

            StdDraw.show();
            StdDraw.pause(2000);
        }

        /* Print out the final state of universe */
        System.out.println(bodyArray.length);
        System.out.println(radius);


    }

    // Get a double corresponding to the radius of the universe in that file
    public static double readRadius(String filename)
    {
        In reader = new In(filename);
        int numOfPlanets =  reader.readInt();
        return reader.readDouble();
    }

    // return an array of Bodys corresponding to the bodies in the file
    public static Body[] readBodies(String filename)
    {
        In reader = new In(filename);
        int numOfPlanets =  reader.readInt();
        double radius =  reader.readDouble();
        Body[] bodyArray = new Body[numOfPlanets];

        double xP;
        double yP;
        double xV;
        double yV;
        double m;
        String img;
        int indexOfPlanet = 0;

        while(indexOfPlanet < numOfPlanets)
        {
            xP = reader.readDouble();
            yP = reader.readDouble();
            xV = reader.readDouble();
            yV = reader.readDouble();
            m = reader.readDouble();
            img = reader.readString();

            bodyArray[indexOfPlanet] = new Body(xP, yP, xV, yV, m, img);
            indexOfPlanet ++;
        }

        return bodyArray;
    }
}
