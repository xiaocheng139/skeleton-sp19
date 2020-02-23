public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    final static double g = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b)
    {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    // calculates the distance between two bodies
    public double calcDistance(Body b)
    {
        double xDistance = b.xxPos - xxPos;
        double yDistance = b.yyPos - yyPos;
        return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    // returns a double describing the force exerted on this body by the given body
    public double calcForceExertedBy(Body b)
    {
        double distance = calcDistance(b);
        return g * mass * b.mass / (distance * distance);
    }

    // returns the total force, these two methods describe the force exerted in the X direction
    public double calcForceExertedByX(Body b)
    {
        double force = calcForceExertedBy(b);
        double distance = calcDistance(b);
        return force * (b.xxPos - xxPos) / distance;
    }

    // returns the total force, these two methods describe the force exerted in the Y direction
    public double calcForceExertedByY(Body b)
    {
        double force = calcForceExertedBy(b);
        double distance = calcDistance(b);
        return force * (b.yyPos - yyPos) / distance;
    }

    //get the net X force exerted by all bodies in that array upon the current Body
    public double calcNetForceExertedByX(Body[] b)
    {
        Body selfBody = this;
        double netForceExertedByX = 0;
        for(Body singleBody: b)
        {
            if(!singleBody.equals(selfBody))
            {
                netForceExertedByX += calcForceExertedByX(singleBody);
            }
        }
        return netForceExertedByX;
    }

    // get the net Y force exerted by all bodies in that array upon the current Body
    public double calcNetForceExertedByY(Body[] b)
    {
        Body selfBody = this;
        double netForceExertedByY = 0;
        for(Body singleBody: b)
        {
            if(!singleBody.equals(selfBody))
            {
                netForceExertedByY += calcForceExertedByY(singleBody);
            }
        }
        return netForceExertedByY;
    }

    // determines how much the forces exerted on the body will cause that body to accelerate, and the resulting change in the body’s velocity and position in a small period of time
    public void update(double dt, double fx, double fy)
    {
        // Calculate the accerlation using the provided x and y forces
        double accerlerationX = fx / mass;
        double accerlerationY = fy / mass;

        // Calculate the new velocity by using the acceleration and current velocity
        xxVel += dt * accerlerationX;
        yyVel += dt * accerlerationY;

        // update the positions by using the velocity and the current position
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
}

