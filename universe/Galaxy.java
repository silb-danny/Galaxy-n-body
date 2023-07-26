package universe;
import mathExt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Galaxy {
    // declaring variables
    public Vector pos;
    public Type type;
    public Vector tempForces;
    public double mass;
    public Vector temp;
    public double rad;
    private double calc_Grav_ForceA(double g, double mass, double distSqr)
    {
        return g*(mass/distSqr);
    }
    //calc functions
    private void calc_gravity(Galaxy other, double timefrm, double const_G, double max_dist, double min_dist) // calculating gravity for inputed object
    {
        double r = temp.distanceSqrd(other.pos, this.pos); // distance squared
        double calc = calc_Grav_ForceA(const_G,other.mass,UMath.clamp_m(r,min_dist)); // basic acceleration calculation
        calc *= (timefrm); // converting to velocity
        temp.setMag(calc);
    }
    private void calc_all_v2(ArrayList<Galaxy> galxs, double timefrm, double const_G)
    {
        int tMass = 0; // temporary mass
        for (int i = 0; i < galxs.size(); i ++) { // looping for each galaxy
            if(galxs.get(i).equals(this)) {
                continue;
            }
            galxs.get(i).temp.distanceVector(galxs.get(i).pos,this.pos); // adding distance vector
            temp.add(galxs.get(i).temp);
            tMass += galxs.get(i).mass;

            // other calculative function
        }
        temp.setMag(calc_Grav_ForceA(const_G,tMass,temp.magSqr())*timefrm*timefrm); // calculating graviy force vector: acceleration -> velocity -> displacement
        tempForces.add(temp);
    }
    // calculating list values
    private void calc_all_v1(ArrayList<Galaxy> galxs, double timefrm, double const_G, double max_dist, double min_dist) // calculations for each object loop - > classic version
    {
        for (int i = 0; i < galxs.size(); i ++) { // looping for each galaxy
            if(galxs.get(i) == this) {
                continue;
            }
            calc_gravity(galxs.get(i), timefrm, const_G,min_dist,max_dist);
            tempForces.add(temp);
            // other calculative function
        }
    }
    // calculating everything
    public void calc(ArrayList<Galaxy> galxs, double timefrm , double const_G , double max_dist, double min_dist)
    {
        calc_all_v1(galxs, timefrm, const_G ,max_dist, min_dist);
//        calc_all_v2(galxs, timefrm, const_G);
    }
    // updating everything
    public void update(double timefrm)
    {
        this.pos.multOnAdd(tempForces,timefrm);
        temp.zero(); // reseting all temp values
        //tempForces.zero(); // reseting all temp values
    }
    public boolean collision(Galaxy other) // checking collisions
    {
        // temp.distanceVector(other.pos,this.pos); // better preformance? less new classes
        return temp.distance(other.pos, this.pos) < (this.rad + other.rad);
    }
    public void findCol(ArrayList<Galaxy> galxs, Galaxies gl) // finding collisions
    {
        for (Galaxy galaxy:galxs) {
            if(collision(galaxy) && galaxy != this){
                gl.max_gal --;
                onCol(galxs, galaxy);
                break;
            }
        }
    }
    public void onCol(ArrayList<Galaxy> galxs, Galaxy other) // function for over-ride
    {
        galxs.remove(other);
        galxs.remove(this);
        double nrad = Math.max(other.rad,this.rad) + Math.min(other.rad,this.rad)/10;
        Vector nPos = (other.mass > mass)?other.pos:this.pos;
        this.tempForces.set((other.mass > mass)?other.tempForces:this.tempForces); // check physics ->
        this.mass += other.mass;
//        this.tempForces.add(other.tempForces);
//        this.tempForces.div(2);
        galxs.add(new Ellipse(nPos,this.mass,nrad,this.tempForces));
    }
    //default functions
    public String toString()
    {
        return "pos-"+this.pos.toString()+" type-"+this.type;
    }
    public boolean equals(Galaxy o) // equals function
    {
        return this.pos.equals(o.pos)&&this.mass==o.mass&&this.type==o.type;
    }



    //<<<----------------------------trash------------------trash---------------------------->>>
    /*less preformant calc_gravity
     * public double calc_gravity(Galaxy other, double timefrm, double const_G,
     * double max_dist, double min_dist) // calculating gravity for inputed object {
     * double r = pos.ret_sub(other.pos).magSqr(); // distance squared double calc =
     * const_G*(other.mass/UMath.clamp(r,min_dist,max_dist)); // basic acceleration
     * calculation calc = calc * (timefrm*timefrm); // converting to position return
     * calc; }
     */
}
