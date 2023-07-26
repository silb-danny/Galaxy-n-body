package universe;

import mathExt.Vector;

import java.util.Random;

public class Ellipse extends Galaxy{
    public Ellipse(Vector pos)
    {
        this.pos = pos;
        this.type = Type.ellipse;
        this.mass = 1;
        this.tempForces = new Vector(0);
        this.temp = new Vector(0);
        this.rad = 1;
    }
    public Ellipse(Vector pos, double mass, double rad, int i, Vector zero)
    {
        this.pos = pos;
        this.type = Type.ellipse;
        this.mass = mass;
        this.tempForces = this.pos.around(zero,5);
        this.temp = new Vector(0);
        this.rad = rad;
    }
    public Ellipse(Vector pos, double mass, double rad, Random rnd)
    {
        this.pos = pos;
        this.type = Type.ellipse;
        this.mass = mass;
        this.tempForces = new Vector(rnd.nextDouble()*2-1,rnd.nextDouble()*2-1);
        this.temp = new Vector(0);
        this.rad = rad;
    }
    public Ellipse(Vector pos, double mass, double rad)
    {
        this.pos = pos;
        this.type = Type.ellipse;
        this.mass = mass;
        this.tempForces = new Vector(0);
        this.temp = new Vector(0);
        this.rad = rad;
    }
    public Ellipse(Vector pos, double mass, double rad, Vector iniV)
    {
        this.pos = pos;
        this.type = Type.ellipse;
        this.mass = mass;
        this.tempForces = iniV;
        this.temp = new Vector(0);
        this.rad = rad;
    }
    public Ellipse(double x, double y)
    {
        this.pos = new Vector(x,y);
        this.type = Type.ellipse;
        this.mass = 1;
        this.tempForces = new Vector(0);
        this.temp = new Vector(0);
        this.rad = 1;
    }
}
