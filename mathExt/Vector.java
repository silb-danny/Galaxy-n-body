package mathExt;

import java.lang.management.MemoryType;
import java.util.Random;

public class Vector {
    // declaring variables <<<----------------------->>>
    public double x,y;

    // making class        <<<----------------------->>>
    public Vector(double x, double y) // with two variables
    {
        this.x = x;
        this.y = y;
    }
    public Vector(double both) // with one variable
    {
        this.x = both;
        this.y = both;
    }
    public Vector(Vector vec) // basically a copy function
    {
        this.x = vec.x;
        this.y = vec.y;
    }

    // default functions
    public void zero() // zeros out vector
    {this.x = 0; this.y = 0;}
    public void one() // makes vector one
    {this.x = 1; this.y = 1;}
    public void down()
    {this.x = 0; this.y = -1;}
    public void set(Vector vec) // sets this vector again
    {this.x=vec.x;this.y=vec.y;}
    public void nRandom(Random rnd)
    {
        this.x = rnd.nextDouble()-rnd.nextInt(2);
        this.y = rnd.nextDouble()-rnd.nextInt(2);
    }
    public void random(Random rnd, double[] range) // random vector range:{min,max}
    {
        this.x = UMath.clamp(rnd.nextDouble()+rnd.nextInt((int)(range[1]-range[0])+1)+range[0],range[0],range[1]);
        this.y = UMath.clamp(rnd.nextDouble()+rnd.nextInt((int)(range[1]-range[0])+1)+range[0],range[0],range[1]);
    }
    public void random(Random rnd, Range range) // random vector range:{min,max}
    {
        this.x = UMath.clamp(rnd.nextDouble()+rnd.nextInt((int)(range.max-range.min)+1)+range.min,range);
        this.y = UMath.clamp(rnd.nextDouble()+rnd.nextInt((int)(range.max-range.min)+1)+range.min,range);
    }
    // simple functions <<<----------------------->>>
    public void add(Vector vec) // adding two vectors
    {
        this.x+=vec.x;
        this.y+=vec.y;
    }
    public void add(double scalar) // adding scalar to vector
    {
        this.x+=scalar;
        this.y+=scalar;
    }
    public Vector ret_add(Vector vec) // returns addition result ****->adjust for better preformance
    {
        Vector vec2 = new Vector(vec);
        vec2.sub(this);
        return vec2;
    }
    public void sub(Vector vec) // subtract two vectors
    {
        this.x-=vec.x;
        this.y-=vec.y;
    }
    public void sub(double scalar) // subtract scalar from vector
    {
        this.x-=scalar;
        this.y-=scalar;
    }
    public Vector ret_sub(Vector vec) // returns subtraction result
    {
        Vector vec2 = new Vector(vec);
        vec2.sub(this);
        return vec2;
    }
    public void multNot(Vector vec) // multiplying two vectors -> maybe not correct
    {
        this.x*=vec.x;
        this.y*=vec.y;
    }
    public void mult(double scalar) // multiplying scalar to vector
    {
        this.x*=scalar;
        this.y*=scalar;
    }
    public void multOnAdd(Vector vec, double scal) // multiplies then adds value then divides by initial multplication
    {
        vec.mult(scal);
        this.add(vec);
        vec.div(scal);
    }
    public void div(double scalar) // dividing scalar to vector
    {
        if(scalar != 0){
            this.x/=scalar;
            this.y/=scalar;
        }
    }

    // overide functions <<<----------------------->>>
    public boolean equals(Vector o) // equals function
    {
        return this.x==o.x&&this.y==o.y;
    }
    public String toString() // returning class values
    {
        return "x:"+this.x+"|y:"+this.y;
    }

    // complex functions <<<----------------------->>>
    public double dir() // returns vector scalar direction
    {
        if(this.x == 0) {
            return Math.PI/2*Math.signum(this.y);
        }else {
            double direction = this.y / this.x;
            direction = Math.atan(direction);
            return direction;
        }
    }
    public double mag() // returns magnitude of vector
    {
        double magnitude = Math.sqrt(this.x*this.x+this.y*this.y);
        return magnitude;
    }
    public double magSqr() // returns magnitude of vector squared
    {
        double magnitude = this.x*this.x+this.y*this.y;
        return magnitude;
    }
    public Vector normal() // returns normal of vector
    {
        double mag = mag();
        if(mag > 0)
            return new Vector(this.x/mag,this.y/mag);
        else
            return new Vector(0);
    }
    public void normalize() // normalizes all vector values
    {
        Vector n = normal();
        set(n);
    }
    public void setMag(double mag)
    {
        normalize();
        mult(mag);
    }
    public void distanceVector(Vector other, Vector other2) // makes a distance vector
    {
        this.set(other);
        this.sub(other2);
    }
    public double distanceSqrd(Vector other, Vector other2) // makes a distance vector
    {
        this.set(other);
        this.sub(other2);
        return magSqr();
    }
    public double distance(Vector other, Vector other2) // makes a distance vector
    {
        this.set(other);
        this.sub(other2);
        return mag();
    }
    public void setDir(double dir)
    {
        this.x = Math.cos(dir);
        this.y = Math.sin(dir);
    }
    public void multX(double x)
    {
        this.x*=x;
    }
    public void multY(double y)
    {
        this.y*=y;
    }
    public Vector around(Vector zero, double strength)
    {
        zero.sub(this);
        double x1 = Math.signum(zero.x);
        double dir = zero.dir() + Math.PI*0.25;
//        System.out.println(dir);
        zero.setDir(dir);
        zero.mult(strength);
        zero.multX(x1);
        zero.multY(x1);
//        System.out.println(zero);(

        return zero;
    }
}
