import mathExt.Vector;
import universe.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class MyPanelUniverse extends JPanel implements ActionListener, MouseListener
{
    Random rnd=new Random();

    int PANEL_WIDTH= 600;
    int PANEL_HIGHT= 600;
    Timer timer;

    int xVelocity=3;
    int yVelocity=1;

    int counter=1;
    int clickCount=1;

    int arraySize;
    static Galaxies cont;

    Graphics g2d;


    MyPanelUniverse()
    {
        MouseListener b = this;
        arraySize=100;
        this.setBackground(Color.black);
        this.addMouseListener(b);
        cont = new Galaxies(0.5,1,300);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HIGHT));
        ActionListener a = (ActionListener) this;
        timer= new Timer(40, (ActionListener) a);
        timer.start();
        cont.add(100,new int[]{0,PANEL_WIDTH},1);
        cont.add(1,new int[]{300,PANEL_WIDTH-300},500,10);
        //System.out.println(cont.galxs.get(0).pos.dir());
        //g2d = (Graphics2D) getGraphics();

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        g2d = getGraphics();
        cont.erase(g2d,1);
//        cont.add(10,new int[]{0,PANEL_WIDTH},1,new Vector(PANEL_WIDTH/2,PANEL_HIGHT/2));
//        cont.add(10,new int[]{0,PANEL_WIDTH},1);
        //cont.add(10,new int[]{0,PANEL_WIDTH/5},1);
        // cont.add(100,new int[]{0,PANEL_WIDTH},1);
        cont.check_collisions();
        cont.calculate();
        cont.update();
        cont.drawLoop(g2d,1);
    }
    @Override
    public void mouseClicked(MouseEvent e)
    {
        clickCount++;
        if (clickCount%2==0) this.repaint();
        else System.out.println(cont.galxs.size());;


    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

}