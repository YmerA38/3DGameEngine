public class V2 {
    private double x;
    private double y;

    public V2(double x, double y) {
        this.x = x;
        this.y = y;
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

    V2 add(V2 v){
        return new V2( x + v.x, y + v.y);
    }
    V2 subtract(V2 v){
        return new V2( x - v.x, y - v.y);
    }
    V2 octagonal(){
        return new V2(-y,x);
    }
    V2 factor(double factor){
        return new V2(factor*x,factor*y);
    }
    double length(){
       return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }
    double scalar(V2 v){
        return x*v.getX()+y* v.getY();
    }
    double angleToX(){
        double l = new V2(x,y).length();
        if(x >= 0) {
            return Math.asin(y) * l;
        }else {
            return Math.acos(x) * l;
        }
    }
    double angleTo(V2 v){
        return new V2(x,y).angleToX() - v.angleToX();
    }

    V2 rotate(double angle, V2 rotationPoint){
        V2 p = new V2(x,y);
        V2 pp = p.subtract(rotationPoint);
        M2 rotationM = new M2(Math.cos(angle),-Math.sin(angle),Math.sin(angle),Math.cos(angle));
        return rotationM.mulVM(pp).add(rotationPoint);
    }
    V2 mirrorVertical(V2 mirrorPoint){
        M2 mirrorM = new M2(1,0,0,-1);
        return mirrorM.mulVM((new V2(x,y).subtract(mirrorPoint))).add(mirrorPoint);
    }
    V2 mirrorHorisont(V2 mirrorPoint){
        M2 mirrorM = new M2(-1,0,0,1);
        return mirrorM.mulVM((new V2(x,y).subtract(mirrorPoint))).add(mirrorPoint);
    }
    V2 normalise(){
        return new V2(x,y).factor(1/length());
    }

    V2 mirror(V2 start, V2 end){

        V2 mid = subtract(start).projectOn(end.subtract(start)).add(start);
        V2 fmid = subtract(mid).factor(-1); // the vector from mid to target reversed


        return fmid.add(mid);
    }

    V2 squeeze(double factorX, double factorY, V2 toPoint ){
        M2 squeezeM = new M2(factorX,0,0,factorY);
        return squeezeM.mulVM(new V2(x,y).subtract(toPoint)).add(toPoint);
    }

    V2 skew(double skewX,double skewY, V2 fromPoint){
        M2 skewM = new M2(1,skewX,skewY,1);
        return skewM.mulVM(new V2(x,y).subtract(fromPoint)).add(fromPoint);
    }

    V2 projectOn(V2 on){
        return on.factor(scalar(on)/(Math.pow(on.x, 2)+Math.pow(on.y, 2)));
    }








    @Override
    public String toString() {
        return  "("+ x + " , "+ y +")";
    }

    public static void main(String[] args){
        V2 v1 = new V2(1,2);
        V2 v2 = new V2(1,2);
        V2 v3 = v1.add(v2);
        System.out.println("v1 = "+v1);
        System.out.println("v2 = "+v2);
        System.out.println("v3 = "+v3);
    }








}
