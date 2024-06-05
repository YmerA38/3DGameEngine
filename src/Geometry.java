import static java.lang.Math.*;
import static java.lang.Math.sin;

public class Geometry {

    public static Object ball(double radius, V3 center ){
        Object ball = new Object();
        ball.setCenter(center);
        ball.setRadius(radius);
        double quarterCircle = PI/2;
        double circle = 2*PI;
        double increment = 0.1;
        for(double horizontalAngel = 0 ; horizontalAngel < circle; horizontalAngel+=increment){
            for(double verticalAngle = -quarterCircle; verticalAngle < quarterCircle; verticalAngle+=increment){
                V3 cP1 = new V3(cos(verticalAngle)*sin(horizontalAngel),-cos(horizontalAngel),sin(verticalAngle)*sin (horizontalAngel)).factor(radius);
                V3 oP1 = center.add(cP1);
                V3 cP2 = new V3(cos(verticalAngle+increment)*sin(horizontalAngel),-cos(horizontalAngel),sin(verticalAngle+increment)*sin (horizontalAngel)).factor(radius);
                V3 oP2 = center.add(cP2);
                V3 cP3 = new V3(cos(verticalAngle)*sin(horizontalAngel+increment),-cos(horizontalAngel+increment),sin(verticalAngle)*sin (horizontalAngel+increment)).factor(radius);
                V3 oP3 = center.add(cP3);
                V3 cP4 = new V3(cos(verticalAngle+increment)*sin(horizontalAngel+increment),-cos(horizontalAngel+increment),sin(verticalAngle+increment)*sin (horizontalAngel+increment)).factor(radius);
                V3 oP4 = center.add(cP4);
                ball.addSurface(new Surface(oP1,oP2,oP3));
                ball.addSurface(new Surface(oP4,oP3,oP2));
            }

        }
        return ball;
    }

    public static Object cube(){
        Object cube = new Object();




        return cube;
    }
}
