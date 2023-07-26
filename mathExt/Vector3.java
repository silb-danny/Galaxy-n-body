package mathExt;

public class Vector3 {
    // declaring variables <<<----------------------->>>
    public double x,y,z;

    // making class        <<<----------------------->>>
    public Vector3(double x, double y,double z) // with two variables
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3(double x, double y) // with two variables
    {
        this.x = x;
        this.y = y;
        this.z = 0;
    }
    public Vector3(double all) // with one variable
    {
        this.x = all;
        this.y = all;
        this.z = all;
    }
    public Vector3(Vector3 vec) // basically a copy function
    {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
    }
    public Vector3(Vector vec) // basically a copy function
    {
        this.x = vec.x;
        this.y = vec.y;
        this.z = 0;
    }
    // default functions
    public void zero() // zeros out vector
    {this.x = 0; this.y = 0; this.z = 0;}
    public void one() // makes vector one
    {this.x = 1; this.y = 1; this.z = 1;}
    public void set(Vector3 vec) // sets this vector again
    {this.x=vec.x;this.y=vec.y; this.z = vec.z;}
    // simple functions <<<----------------------->>>
    public void add(Vector3 vec) // adding two vectors
    {
        this.x+=vec.x;
        this.y+=vec.y;
        this.z+=vec.z;
    }
    public void add(double scalar) // adding scalar to vector
    {
        this.x+=scalar;
        this.y+=scalar;
        this.z+=scalar;
    }
    public Vector3 ret_add(Vector3 vec) // returns addition result ****->adjust for better preformance
    {
        Vector3 vec2 = new Vector3(vec);
        vec2.sub(this);
        return vec2;
    }
    public void sub(Vector3 vec) // subtract two vectors
    {
        this.x-=vec.x;
        this.y-=vec.y;
        this.z-=vec.z;
    }
    public void sub(double scalar) // subtract scalar from vector
    {
        this.x-=scalar;
        this.y-=scalar;
        this.z-=scalar;
    }
    public Vector3 ret_sub(Vector3 vec) // returns subtraction result
    {
        Vector3 vec2 = new Vector3(vec);
        vec2.sub(this);
        return vec2;
    }
    public void multNot(Vector3 vec) // multiplying two vectors -> maybe not correct
    {
        this.x*=vec.x;
        this.y*=vec.y;
        this.z*=vec.z;
    }
    public void mult(double scalar) // multiplying scalar to vector
    {
        this.x*=scalar;
        this.y*=scalar;
        this.z*=scalar;
    }
    public void div(double scalar) // dividing scalar to vector
    {
        if(scalar != 0) {
            this.x/=scalar;
            this.y/=scalar;
            this.z/=scalar;
        }
    }

    // overide functions <<<----------------------->>>
    public boolean equals(Vector3 o) // equals function
    {
        return this.x==o.x&&this.y==o.y&&this.z==o.z;
    }
    public String toString() // returning class values
    {
        return "x:"+this.x+"|y:"+this.y+"|z:"+this.z;
    }

    // complex functions <<<----------------------->>>
    public double dir() // returns vector scalar direction-- not fully correct
    {
        double direction = this.y/this.x;
        direction = Math.atan(direction);
        return direction;
    }
    public double mag() // returns magnitude of vector
    {
        double magnitude = Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z);
        return magnitude;
    }
    public double magSqr() // returns magnitude of vector squared
    {
        double magnitude = this.x*this.x+this.y*this.y+this.z*this.z;
        return magnitude;
    }
    public Vector3 normal() // returns normal of vector
    {
        double mag = mag();
        if(mag > 0)
            return new Vector3(this.x/mag,this.y/mag,this.z/mag);
        else
            return new Vector3(0);
    }
    public void normalize() // normalizes all vector values
    {
        Vector3 n = normal();
        set(n);
    }
    public void distanceVector(Vector3 other, Vector3 other2) // makes a distance vector
    {
        this.set(other);
        this.sub(other2);
    }
}