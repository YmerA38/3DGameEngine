public class M3 {
    private double x1,x2,x3,y1,y2,y3,z1,z2,z3;

    public M3(double x1, double x2, double x3, double y1, double y2, double y3, double z1, double z2, double z3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.z1 = z1;
        this.z2 = z2;
        this.z3 = z3;
    }
    public M3(){   //identityMatrix
        this.x1 = 1;
        this.x2 = 0;
        this.x3 = 0;
        this.y1 = 0;
        this.y2 = 1;
        this.y3 = 0;
        this.z1 = 0;
        this.z2 = 0;
        this.z3 = 1;
    }

//    M2 toM2(M3 m3){ //if the two xy planes are parallel
//
//        return new M2(m3.getX1(), m3.getX2(), m3.getY1(), m3.getY2());
//    }
    public V3 multiplyMV(V3 v){
        return new V3(x1*v.getX()+x2*v.getY()+x3*v.getZ(),
                      y1*v.getX()+y2*v.getY()+y3*v.getZ(),
                      z1*v.getX()+z2*v.getY()+z3*v.getZ());
    }

    public L3 multiplyML(L3 line){
        V3 start = line.getStart();
        V3 end = line.getEnd();
        return new L3(new V3(x1*start.getX()+x2*start.getY()+x3*start.getZ(),y1*start.getX()+y2*start.getY()+y3*start.getZ(),z1*start.getX()+z2*start.getY()+z3*start.getZ()),
                        new V3(x1*end.getX()+x2*end.getY()+x3*end.getZ(),y1*end.getX()+y2*end.getY()+y3*end.getZ(),z1*end.getX()+z2*end.getY()+z3*end.getZ()));
    }

    public M3 multiplyMM(M3 m){
        return new M3(x1*m.x1+x2*m.y1+x3*m.z1,x1*m.x2+x2*m.y2+x3*m.z2,x1*m.x3+x2*m.y3+x3*m.z3,
                      y1*m.x1+y2*m.y1+y3*m.z1,y1*m.x2+y2*m.y2+y3*m.z2,y1*m.x3+y2*m.y3+y3*m.z3,
                      z1*m.x1+z2*m.y1+z3*m.z1,z1*m.x2+z2*m.y2+z3*m.z2,z1*m.x3+z2*m.y3+z3*m.z3);
    }

    public M3 add(M3 m){
        return new M3(x1+m.x1,x2+m.x2,x3+m.x3,
                      y1+m.y1,y2+m.y2,y3+m.y3,
                      z1+m.z1,z2+m.z2,z3+m.z3);
    }

    public M3 factor(double factor){
        return new M3(x1*factor,x2*factor,x3*factor,
                      y1*factor,y2*factor,y3*factor,
                      z1*factor,z2*factor,z3*factor);
    }










    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }


    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }


    public double getZ1() {
        return z1;
    }

    public void setZ1(double z1) {
        this.z1 = z1;
    }

    public double getZ2() {
        return z2;
    }

    public void setZ2(double z2) {
        this.z2 = z2;
    }

    public double getX3() {
        return x3;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public double getY3() {
        return y3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    public double getZ3() {
        return z3;
    }

    public void setZ3(double z3) {
        this.z3 = z3;
    }
}
