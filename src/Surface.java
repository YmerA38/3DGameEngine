public class Surface {
    private V3 p1;
    private V3 p2;
    private V3 o;


    public Surface(V3 o, V3 p1, V3 p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.o = o;
    }

    public V3 getP1() {
        return p1;
    }

    public void setP1(V3 p1) {
        this.p1 = p1;
    }

    public V3 getP2() {
        return p2;
    }

    public void setP2(V3 p2) {
        this.p2 = p2;
    }

    public V3 getO() {
        return o;
    }

    public void setO(V3 o) {
        this.o = o;
    }

    public V3 getNormal() {
        return p1.subtract(o).crossProduct(p2.subtract(o)).normalize();
    }


}
