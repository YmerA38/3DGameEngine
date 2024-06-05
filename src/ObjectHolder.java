import java.util.ArrayList;
import java.util.Collections;

public class ObjectHolder {
    private ArrayList<Object> objects = new ArrayList<>();

    public void addObject(Object object){
        objects.add(object);
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void sortByDistance(){
        Collections.sort(objects,new DistanceComparator());
    }

    public boolean collisionBalls(){
        boolean collision = false;
        V3 normalCollision1;
        V3 normalCollision2;
        V3 collisionPoint;
        for(Object ball1 : objects){
            for(Object ball2 : objects){ // how to exclude self?
                if (ball1 == ball2) continue;
                if(ball1.getCenter().subtract(ball2.getCenter()).length()<= ball1.getRadius()+ball2.getRadius()){
                    collision = true;
                    normalCollision1 = ball2.getCenter().subtract(ball1.getCenter()).normalize();
                    normalCollision2 = normalCollision1.factor(-1);
                    collisionPoint = ball1.getCenter().add(normalCollision1.factor(ball1.getRadius()));
                    ball1.setMoveDirection(ball1.getMoveDirection().subtract(normalCollision2.factor(2*normalCollision2.scalar(ball1.getMoveDirection()))).normalize());
                    ball2.setMoveDirection(ball2.getMoveDirection().subtract(normalCollision1.factor(2*normalCollision1.scalar(ball2.getMoveDirection()))).normalize());

                    break;
                }

            }

            if (collision) break;
        }

        return collision;
    }

    public boolean collisionBoundaries(V3 min, V3 max){
        V3 nMinX = new V3(1,0,0);
        V3 nMaxX = new V3(-1,0,0);
        V3 nMinY = new V3(0,1,0);
        V3 nMaxY = new V3(0,-1,0);
        V3 nMinZ = new V3(0,0,1);
        V3 nMaxZ = new V3(0,0,-1);
        V3 normalCollision = null;
        boolean collision = false;

        for(Object ball : objects){
            if (ball.getCenter().getX() - ball.getRadius()<= min.getX()){
                collision = true;
                normalCollision = nMinX;
                System.out.println("1");
            }
            if (ball.getCenter().getX() + ball.getRadius()>= max.getX()){
                collision = true;
                normalCollision = nMaxX;
                System.out.println("2");
            }
            if (ball.getCenter().getY() - ball.getRadius()<= min.getY()){
                collision = true;
                normalCollision = nMinY;
                System.out.println("3");
            }
            if (ball.getCenter().getY() + ball.getRadius()>= max.getY()){
                collision = true;
                normalCollision = nMaxY;
                System.out.println("4");
            }
            if (ball.getCenter().getZ() - ball.getRadius()<= min.getZ()){
                collision = true;
                normalCollision = nMinZ;
                System.out.println("5");
            }
            if (ball.getCenter().getZ() + ball.getRadius()>= max.getZ()){
                collision = true;
                normalCollision = nMaxZ;
                System.out.println("6");
            }

            if(collision){
                //System.out.println(ball.getMoveDirection());
                ball.setMoveDirection(ball.getMoveDirection().subtract(normalCollision.factor(2*normalCollision.scalar(ball.getMoveDirection()))).normalize());
                //System.out.println(ball.getMoveDirection());
                //System.out.println("collision");
                //break;
                collision = false;
            }
        }
        return collision;
    }






}
