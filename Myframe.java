import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;

public class Myframe extends JFrame
{

    JPanel panel;
    JSlider slider1 = new JSlider(40,150);
    Myframe()
    {
        panel=new MyPanelEvolution(slider1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        panel.add(slider1);
    }



}