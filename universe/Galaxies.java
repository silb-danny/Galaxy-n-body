package universe;
import mathExt.*;

import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

public class Galaxies {
    public static Random rnd = new Random();
    public double def_rad = 2;
    public double timefrm = 1;
    public double const_G = 1;
    public double max_dist = 100;
    public double min_dist = 0.1; // radius of galaxy -> could be private value of each galaxy
    public int max_gal = 100;
    public ArrayList<Galaxy> galxs = new ArrayList<>();

    // constructors
    public Galaxies(double timefrm, double const_G, double max_dist, double min_dist, int max_gal)
    {
        this.timefrm = timefrm;
        this.const_G = const_G;
        this.max_dist = max_dist;
        this.min_dist = min_dist;
        this.max_gal = max_gal;
    }
    public Galaxies(double timefrm, double const_G, int max_gal)
    {
        this.timefrm = timefrm;
        this.const_G = const_G;
        this.max_gal = max_gal;
    }
    public Galaxies(int max_gal)
    {
        this.max_gal = max_gal;
    }
    public Galaxies()
    {
        this.max_gal = 100;
    }
    // functions
    public void check_collisions() // checks collisions
    {
        for (int j = 0; j < galxs.size(); j++)
        {
            galxs.get(j).findCol(galxs, this);
        }
    }
    /*
     * @param text calculates data foreach object without updating object positions
     */
    public void calculate()
    {
        for (int i = 0; i < galxs.size(); i ++) { // calculating
            galxs.get(i).calc(galxs, timefrm, const_G, max_dist, min_dist);
        }
    }
    /*
     * @param text updates object values using temporary stored values (tempForces)
     */
    public void update()
    {
        for (int i = 0; i < galxs.size(); i ++) { // updating
            galxs.get(i).update(timefrm);
        }
    }
     // add functions
    public void add(int amount, int[] pos_range, int[] mass_range) // all random rang: {min,max}
    {
        for (int i = 0; i < amount; i++) {
            int typee = rnd.nextInt(Type.values().length);
            Vector pos = new Vector(rnd.nextInt((pos_range[1]-pos_range[0])+1)+pos_range[0],rnd.nextInt((pos_range[1]-pos_range[0])+1)+pos_range[0]);
            int mass = rnd.nextInt(mass_range[1]-mass_range[0]+1)+mass_range[0];
            createGal(typee,mass,pos,def_rad);
        }
    }
    public void add(int amount, int[] pos_range, int massIn, double rad) // all random rang: {min,max}
    {
        for (int i = 0; i < amount; i++) {
            int typee = rnd.nextInt(Type.values().length);
            Vector pos = new Vector(rnd.nextInt((pos_range[1]-pos_range[0])+1)+pos_range[0],rnd.nextInt((pos_range[1]-pos_range[0])+1)+pos_range[0]);
            int mass = massIn;
            createGal(typee,mass,pos,rad);
        }
    }
    public void add(int amount, int[] pos_range, int massIn, Vector zero) // all random rang: {min,max}
    {
        for (int i = 0; i < amount; i++) {
            int typee = rnd.nextInt(Type.values().length);
            Vector pos = new Vector(rnd.nextInt((pos_range[1]-pos_range[0])+1)+pos_range[0],rnd.nextInt((pos_range[1]-pos_range[0])+1)+pos_range[0]);
            int mass = massIn;
            createGal(typee,mass,pos,zero);
        }
    }
    public void add(int amount, int[] pos_range, int massIn) // all random rang: {min,max}
    {
        for (int i = 0; i < amount; i++) {
            int typee = rnd.nextInt(Type.values().length);
            Vector pos = new Vector(rnd.nextInt((pos_range[1]-pos_range[0])+1)+pos_range[0],rnd.nextInt((pos_range[1]-pos_range[0])+1)+pos_range[0]);
            int mass = massIn;
            createGal(typee,mass,pos);
        }
    }
    public void add(int[] pos_range, int[] mass_range, int[] am_ofeach) // all random rang: {min,max} except type
    {
        for (int j = 0; j < am_ofeach.length; j++) {
            for (int i = 0; i < am_ofeach[j]; i++) {
                Vector pos = new Vector(rnd.nextInt((pos_range[1] - pos_range[0]) + 1) + pos_range[0], rnd.nextInt((pos_range[1] - pos_range[0]) + 1) + pos_range[0]);
                int mass = rnd.nextInt(mass_range[1] - mass_range[0] + 1) + mass_range[0];
                createGal(j,mass,pos,def_rad);
            }
        }
    }
    public void add(int[] pos_range, int massIn, int[] am_ofeach) // all random rang: {min,max} except type and mass
    {
        for (int j = 0; j < am_ofeach.length; j++) {
            for (int i = 0; i < am_ofeach[j]; i++) {
                Vector pos = new Vector(rnd.nextInt((pos_range[1] - pos_range[0]) + 1) + pos_range[0], rnd.nextInt((pos_range[1] - pos_range[0]) + 1) + pos_range[0]);
                int mass = massIn;
                createGal(j,mass,pos,def_rad);
            }
        }
    }
    private void createGal(int type, double mass, Vector pos,Vector zero) // creating galaxies
    {
        if(galxs.size() < max_gal) { // there are less than the max amount of galaxies add another one
            switch (type) {
                case 0:
                    galxs.add(new Spiral(pos, mass, def_rad, new Vector(zero)));
                    break;
                case 1:
                    galxs.add(new Ellipse(pos, mass, def_rad, 1,new Vector(zero)));
                    break;
            }
        }
    }
    private void createGal(int type, double mass, Vector pos) // creating galaxies
    {
        if(galxs.size() < max_gal) { // there are less than the max amount of galaxies add another one
            switch (type) {
                case 0:
                    galxs.add(new Spiral(pos, mass, def_rad, rnd));
                    break;
                case 1:
                    galxs.add(new Ellipse(pos, mass, def_rad, rnd));
                    break;
            }
        }
    }
    private void createGal(int type, double mass, Vector pos,double rad) // creating galaxies
    {
        if(galxs.size() < max_gal) { // there are less than the max amount of galaxies add another one
            switch (type) {
                case 0:
                    galxs.add(new Spiral(pos, mass, rad));
                    break;
                case 1:
                    galxs.add(new Ellipse(pos, mass, rad));
                    break;
            }
        }
    }
    // simulation functions
    public void simulate_frame() { // run all functions together -> except for add
        check_collisions();
        calculate();
        update();
    }
    public void run(int amount, int[] range_pos, int mass, Graphics g2D, double size) // everything for a frame --> better to use simulate_frame
    {
        add(amount,range_pos,mass,def_rad);
        simulate_frame();
        drawLoop(g2D, size);
    }
    public int[] drawLoop(Graphics g2D, double size)
    {
        for (Galaxy galaxy : galxs) {
            switch (galaxy.type) {
                case spiral:
                    g2D.setColor(Color.blue);
                    break;
                case ellipse:
                    g2D.setColor(Color.red);
                    break;
            }
            g2D.fillOval((int)((galaxy.pos.x-galaxy.rad)*size) , (int)((galaxy.pos.y-galaxy.rad)*size), (int)(2*galaxy.rad), (int)(2*galaxy.rad));
        }
        return how_many_of_all();
    }
    public void erase(Graphics g2D,double size)
    {
        for (Galaxy galaxy: galxs) {
            g2D.setColor(Color.white);
            g2D.fillOval((int)((galaxy.pos.x-galaxy.rad)*size) , (int)((galaxy.pos.y-galaxy.rad)*size), (int)(2*galaxy.rad), (int)(2*galaxy.rad));
        }
    }
    // counting on list functions -->
    public double normalize_size()
    {
        double maxMass = galxs.get(0).mass;
        for (Galaxy galaxy:galxs) {
            maxMass = Math.max(maxMass,galaxy.mass);
        }
        return maxMass;
    }
    // check how many of a certain type of galaxy there is
    public int how_many_of_type(Type type)
    {
        int counter = 0;
        for (Galaxy galaxy : galxs) {
            if(galaxy.type == type)
                counter ++;
        }
        return counter;
    }
    public int[] how_many_of_all()
    {
        int[] amounts = new int[Type.values().length];
        int i = 0;
        for (Type type : Type.values()) {
            amounts[i] = how_many_of_type(type);
            i++;
        }
        return amounts;
    }
}

