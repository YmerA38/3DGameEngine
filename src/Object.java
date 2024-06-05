import java.util.ArrayList;

public class Object {
    private String name;
    private ArrayList<Surface> surfaces = new ArrayList<>();
    private V3 moveDirection;
    private double moveSpeed;
    private double volume;
    private V3 center;
    private double radius;

    public void rotate(V3 rotationAxis,V3 centerOfRotation, double increment){
        for (Surface s : surfaces) {
            s.setO(s.getO().rotate(rotationAxis, centerOfRotation, increment));
            s.setP1(s.getP1().rotate(rotationAxis, centerOfRotation, increment));
            s.setP2(s.getP2().rotate(rotationAxis, centerOfRotation, increment));
        }
    }

    public void move(){
        center = center.add(moveDirection.factor(moveSpeed));
        for (Surface s : surfaces) {
            s.setO(s.getO().add(moveDirection.factor(moveSpeed)));
            s.setP1(s.getP1().add(moveDirection.factor(moveSpeed)));
            s.setP2(s.getP2().add(moveDirection.factor(moveSpeed)));
        }
    }

    public void gravity(double intervalms){ // number of milliseconds between calls
        double g = 9.81;
        V3 down = new V3(0,-1,0);
        V3 newDirection = moveDirection.factor(moveSpeed).add(down.factor(g*intervalms/1000));
        moveSpeed = newDirection.length();
        moveDirection = newDirection.normalize();
    }


    public void addSurface(Surface surface){
        surfaces.add(surface);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Surface> getSurfaces() {
        return surfaces;
    }

    public void setSurfaces(ArrayList<Surface> surfaces) {
        this.surfaces = surfaces;
    }

    public V3 getMoveDirection() {
        return moveDirection;
    }

    public void setMoveDirection(V3 moveDirection) {
        this.moveDirection = moveDirection;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public V3 getCenter() {
        return center;
    }

    public void setCenter(V3 center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
