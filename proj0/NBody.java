import java.util.Scanner;

public class NBody {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double T = scanner.nextDouble();
        double dt = scanner.nextDouble();
        String filename = scanner.next();
        double radius = readRadius(filename);
        Body[] bodyArray = readBodies(filename);
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
