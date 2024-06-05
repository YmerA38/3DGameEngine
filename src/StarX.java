import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class StarX extends JFrame {
        JPanel panel = new JPanel();
        JButton makeStar = new JButton("Make Star");
        JTextField points = new JTextField(3);
        JTextField skips = new JTextField(3);
        JButton turnBtn = new JButton("turn");



    public static void main(String[] args){
        JFrame frame = new StarX();

        frame.setSize(700, 800);
        frame.setTitle("Star");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);



    }
    DrawPanel drawPanel = new DrawPanel();
    StarX(){

        setLayout(new BorderLayout());
        add(panel,BorderLayout.NORTH);

        panel.add(new JLabel("Points"));
        panel.add(points);
        points.setText("5");

        panel.add(new JLabel("Skips"));
        panel.add(skips);
        skips.setText("1");

        panel.add(new JLabel(""));
        panel.add(makeStar);

        panel.add(turnBtn);

        MyListener listener = new MyListener();
        makeStar.addActionListener(listener);
        turnBtn.addActionListener(listener);

        add(drawPanel);
    }
    double speed = 0.1;
    double turn = 0;

    int starPoints = 5;
    int skip = 1;

    class DrawPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Random random = new Random();
            int starX0 = 350;
            int starY0 = 350;
            int radius = 300;

            double circleDegrees= 2*Math.PI;



            double segmentDegrees = circleDegrees*(1+skip)/starPoints;

            System.out.println("COSpi: "+Math.cos(Math.PI));

            for (int i=0;i<starPoints;i++){
                g.setColor(new Color((254+(i*20))%255,(127+(i*19))%255,(i*18)%255));
                //g.setColor(new Color(random.nextInt(0,255),random.nextInt(0,255),random.nextInt(0,255)));
                g.drawLine(starX0+(int)((radius*Math.cos(segmentDegrees*i+turn))),starY0+(int)((radius*Math.sin(segmentDegrees*i+turn))),starX0+(int)((radius*Math.cos(segmentDegrees*(i+1)+turn))),starY0+(int)((radius*Math.sin(segmentDegrees*(i+1)+turn))));

            }



        }




    }

    public class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Make Star")){
                starPoints = Integer.parseInt(points.getText());
                skip = Integer.parseInt(skips.getText());
                drawPanel.repaint();
            }
            if (e.getActionCommand().equals("turn")){
                if (timer.isRunning()){
                    timer.stop();
                }else {
                    timer.start();
                }
            }

        }
    }

    Timer timer=new Timer(100, new MyTimerListener());

    class MyTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            turn += speed;
                //timer.stop();

            drawPanel.repaint();
        }
    }



}
