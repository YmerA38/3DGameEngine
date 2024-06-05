public class M2 {
    private double x1,x2,y1,y2;

    public M2(double x1, double x2, double y1, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
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

   V2 mulVM(V2 v){

        return new V2(x1*v.getX()+x2*v.getY(),
                y1*v.getX()+y2*v.getY());
    }

    M2 mulMM(M2 m){

        return new M2(x1*m.x1+x2*m.y1, x1*m.x2+x2*m.y2,
                    y1*m.x1+y2*m.y1, y1*m.x2+y2*m.y2);
    }

    @Override
    public String toString() {
        return  "("+x1+ " , "+ x2 +")\n("+y1+" , "+y2+")";
    }

    public static void main(String[] args){
        V2 v = new V2(1,2);
        M2 m1 = new M2(1,2,3,4);
        M2 m2 = new M2(1,2,3,4);

    }




}
