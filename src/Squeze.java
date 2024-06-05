import javax.swing.*;
import java.awt.*;

public class Squeze extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new Squeze();
        frame.setSize(1500, 750);
        frame.setTitle("GraphicsApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
    public Squeze() {
        add(new DrawPanel());
    }
    class DrawPanel extends JPanel {
        S2 s = new S2(50,50,50,650);

        V2 center = new V2(2,2);
        double radius = 1;
        double a = 2;
        double b = 0.5;


        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            s.drawXAxis(g,20,2);
            s.drawYAxis(g,11,2);
            for(double v = 0; v<Math.PI*2; v += 0.1){
                V2 p =new V2(Math.cos(v),Math.sin(v)).factor(radius).add(center).squeeze(2,0.5,center);
                V2 p1 =new V2(Math.cos(v+0.1),Math.sin(v+0.1)).factor(radius).add(center).squeeze(2,0.5,center);
                s.drawLine(g,p,p1);
            }

        }
    } // class DrawPanel

}// GraphicApp
