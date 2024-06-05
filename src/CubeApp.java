import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CubeApp extends JFrame {

    public static void main(String[] args){
        JFrame frame = new CubeApp();

        frame.setSize(1900, 800);
        frame.setTitle("Cube");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);

    }

    DrawPanel drawPanel = new DrawPanel();
    CubeApp(){
        add(drawPanel);
    }
    class DrawPanel extends JPanel {
        Timer myTimer=new Timer(50, new MyTimerListener());

        Camera camera = new Camera(50,50,750,400);
        V3[] cube;
        V3 center;
        DrawPanel() {
            // Start simulation
            myTimer.start();

            cube = new V3[8];
            cube[0] = new V3(11, 11, 11);
            cube[1] = new V3(11, 15, 11);
            cube[2] = new V3(11, 11, 15);
            cube[3] = new V3(11, 15, 15);
            cube[4] = new V3(15, 11, 11);
            cube[5] = new V3(15, 15, 11);
            cube[6] = new V3(15, 11, 15);
            cube[7] = new V3(15, 15, 15);
            center = new V3(0,0,0);
            for (int i=0; i<cube.length; i++){ // tager summen af vektore
                center = center.add(cube[i]);
            }
            center = center.factor(1.00/cube.length); // finder gennemsnittet

            camera.moveCamTo(new V3(10,5,2));
            camera.directCam(center);
            camera.homeCam();
        }


        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            camera.drawXAxis(g,10,1);
            camera.drawYAxis(g,10,1);
            camera.drawZAxis(g,10,1);

            camera.drawPoint(g,center);

            camera.drawLine(g,cube[0],cube[1]);
            camera.drawLine(g,cube[0],cube[2]);
            camera.drawLine(g,cube[3],cube[2]);
            camera.drawLine(g,cube[3],cube[1]);

            camera.drawLine(g,cube[0],cube[4]);
            camera.drawLine(g,cube[1],cube[5]);
            camera.drawLine(g,cube[2],cube[6]);
            camera.drawLine(g,cube[3],cube[7]);

            camera.drawLine(g,cube[4],cube[5]);
            camera.drawLine(g,cube[4],cube[6]);
            camera.drawLine(g,cube[7],cube[5]);
            camera.drawLine(g,cube[7],cube[6]);
            double phi=Math.PI/100;
            for (int i=0; i<cube.length; i++){
                cube[i] = cube[i].rotate(new V3(1,1,1),center,phi); // ændre alle vektore hver gang løkke bliver kaldt
            }

        }


    }


    class MyTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {


            drawPanel.repaint();
        }
    }




}
