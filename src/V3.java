public class V3 {
    private double x,y,z;

    public V3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void setV(V3 v){
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    V3 add(V3 v){
        return new V3(x+v.x,y+v.y,z+v.z);
    }
    V3 subtract(V3 v) {
        return new V3(x-v.x,y-v.y,z-v.z);
    }

    double length(){ //skal rettes
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));
    }
    V3 factor(double factor){
        return new V3(factor*x,factor*y,factor*z);
    }
    double scalar(V3 v){
        return x*v.getX()+y*v.getY()+z*v.getZ();
    }

    V3 crossProduct(V3 v){
        return new V3(y*v.z-z*v.y,
                      z*v.x-x*v.z,
                      x*v.y-y*v.x);
    }



    M3 skewSymMatrix(){
        return new M3(0,-z,y,
                      z,0,-x,
                     -y,x,0);
    }

    V3 normalize(){
        return new V3(x,y,z).factor(1/length());
    }

    double angleToZ(){
        return Math.acos(z/(length()));
    }

    public M3 rotationMatrix(double angel){
        V3 rotationAxis = normalize(); // laver normalvektor of den vektor mtoden bliver taget på
        M3 I = new M3(1,0,0,
                        0,1,0,
                        0,0,1);
        M3 S = rotationAxis.skewSymMatrix();

        return  I.add(S.factor(Math.sin(angel))).add(S.multiplyMM(S).factor(1-Math.cos(angel)));
    }

    public V3 rotate(V3 rotationAxis,V3 CenterOfRotation,double angel){
        M3 R = rotationAxis.rotationMatrix(angel);
        return R.multiplyMV(new V3(x,y,z).subtract(CenterOfRotation)).add(CenterOfRotation);
    }

    public V2 toV2(V3 disappearPoint){
        return perspective(disappearPoint);
        //alignZToNormal(normal).
    }
//
//    public V3 alignZToNormal(V3 normal){
//        return normal.rotationMatrix().multiplyMV(new V3(x,y,z));
//    }

    public V2 perspective(V3 dP){
        V3 p = new V3(x,y,z);
        //V3 p = dP.rotationMatrix().multiplyMV(new V3(x,y,-z));
        V3 fromDP = p.subtract(dP);
        if(p.z>dP.z){
            fromDP.z = 0;
        }

        return new V2(dP.x,dP.y).add(new V2(fromDP.x, fromDP.y).factor(Math.pow((-fromDP.z/dP.z),dP.z/20)));

    }

    V3 turn(V3 point,V3 pivotPoint,V3 direction){

        return null;
    }

    @Override
    public String toString() {
        return "("+x+","+y+","+z+")";
    }
}
