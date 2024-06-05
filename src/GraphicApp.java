import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.PI;

public class GraphicApp extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new GraphicApp();
        frame.setSize(1500, 750);
        frame.setTitle("GraphicsApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }
    DrawPanel drawPanel = new DrawPanel();
    public GraphicApp() {
        add(drawPanel);
    }
    class DrawPanel extends JPanel {
        Timer myTimer=new Timer(50, new GraphicApp.MyTimerListener());
        S2 s = new S2(50,50,50,650);

        V3 lightDirection = new V3(1,1,1).normalize()  ;

        V3 rotationAxis = new V3(1,0,0);
        V3 centerOfRotation = new V3(25,5,10);
        Camera camera = new Camera(50,50,750,400);

        double phi = 2*PI/1000;
        ObjectHolder game = new ObjectHolder();

        DrawPanel(){
            myTimer.start();
            Object ball1 = Geometry.ball(5,centerOfRotation);
            Object ball2 = Geometry.ball(5,new V3(25,5,25));
            Object ball3 = Geometry.ball(5,new V3(25,5,-25));
            Object ball4 = Geometry.ball(5,new V3(25,5,-10));
            ball1.setMoveSpeed(0.3);
            ball2.setMoveSpeed(0.3);
            ball3.setMoveSpeed(0.3);
            ball4.setMoveSpeed(0.3);
            ball1.setMoveDirection(new V3(0.1,0,1));
            ball2.setMoveDirection(new V3(0,0.1,-1));
            ball3.setMoveDirection(new V3(0.1,1,1));
            ball4.setMoveDirection(new V3(1,0.1,-1));
            game.addObject(ball1);
            game.addObject(ball2);
            game.addObject(ball3);
            game.addObject(ball4);


        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            camera.drawXAxis(g,10,1);
            camera.drawYAxis(g,10,1); 
            camera.drawZAxis(g,10,1);

            game.sortByDistance();
            for (Object ball: game.getObjects()) {

                //ball.rotate(rotationAxis, centerOfRotation, phi);
                ball.move();
                camera.showShape(g, ball, lightDirection);
                ball.gravity(5);
                //System.out.println(ball.getCenter());
            }
            game.collisionBalls();
            game.collisionBoundaries(new V3(10,-25,-40),new V3(40,+25,40));

        }
    } // class DrawPanel

    class MyTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            drawPanel.repaint();
        }
    }

}// GraphicApp
