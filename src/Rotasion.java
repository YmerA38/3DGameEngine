import javax.swing.*;
import java.awt.*;

public class Rotasion extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new Rotasion();
        frame.setSize(1500, 750);
        frame.setTitle("GraphicsApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
    public Rotasion() {
        add(new DrawPanel());
    }
    class DrawPanel extends JPanel {
        S2 s = new S2(50,50,50,650);
        V3 normal = new V3(0,0,1);
        V2 A = new V2(4,4);
        V2 B = new V2(2,2);
        V2 C = new V2(6,2);
        V2 D = new V2(2,4);
        V2 P = A.add(B).add(C).add(D).factor(1.0/4);
        V2 P2 = new V2(0,0);
        double angle = Math.PI/6;

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            s.drawXAxis(g,20,2);
            s.drawYAxis(g,11,2);

            s.drawLine(g,A,B);
            s.drawLine(g,C,B);
            s.drawLine(g,C,A);




//            V2 A2 = A.rotate(angle,P2);
//            V2 B2 = B.rotate(angle,P2);
//            V2 C2 = C.rotate(angle,P2);
//
            V2 A2 = A.mirrorVertical(new V2(0,0));
            V2 B2 = B.mirrorVertical(new V2(0,0));
            V2 C2 = C.mirrorVertical(new V2(0,0));

            s.drawLine(g,A2,B2);
            s.drawLine(g,C2,B2);
            s.drawLine(g,C2,A2);











        }
    } // class DrawPanel

}// GraphicApp
