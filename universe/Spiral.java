package universe;

import mathExt.Vector;

import java.util.Random;

public class Spiral extends Galaxy{
    public Spiral(Vector pos)
    {
        this.pos = pos;
        this.type = Type.spiral;
        this.mass = 1;
        this.tempForces = new Vector(0);
        this.temp = new Vector(0);
        this.rad = 1;
    }
    public Spiral(Vector pos, double mass, double rad, Vector zero)
    {
        this.pos = pos;
        this.type = Type.spiral;
        this.mass = mass;
        this.tempForces = this.pos.around(zero,5);
        this.temp = new Vector(0);
        this.rad = rad;
    }
    public Spiral(Vector pos, double mass, double rad, Random rnd)
    {
        this.pos = pos;
        this.type = Type.spiral;
        this.mass = mass;
        this.tempForces = new Vector(rnd.nextDouble()*2-1,rnd.nextDouble()*2-1);
        this.temp = new Vector(0);
        this.rad = rad;
    }
    public Spiral(Vector pos, double mass, double rad)
    {
        this.pos = pos;
        this.type = Type.spiral;
        this.mass = mass;
        this.tempForces = new Vector(0);
        this.temp = new Vector(0);
        this.rad = rad;
    }
    public Spiral(double x, double y)
    {
        this.pos = new Vector(x,y);
        this.type = Type.spiral;
        this.mass = 1;
        this.tempForces = new Vector(0);
        this.temp = new Vector(0);
        this.rad = 1;
    }

}
