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
}
