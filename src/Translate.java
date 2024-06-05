import javax.swing.*;
import java.awt.*;

import static java.lang.Math.PI;


public class Translate extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new Translate();
        frame.setSize(1500, 750);
        frame.setTitle("GraphicsApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
    public Translate() {
        add(new DrawPanel());
    }
    class DrawPanel extends JPanel {
       S2 s = new S2(50,50,120,300);
       V2 P = new V2(3,5);

       S2 sm = new S2(50,50,120,300);
       DrawPanel(){
           sm.moveTo(new V2 (2,2));
           sm.rotate(PI/4);
       }
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            s.drawXAxis(g,20,2);
            s.drawYAxis(g,11,2);
            s.drawPoint(g,P);

            sm.drawXAxis(g,3,1);
            sm.drawYAxis(g,3,1);




//













        }
    } // class DrawPanel

}// GraphicApp
