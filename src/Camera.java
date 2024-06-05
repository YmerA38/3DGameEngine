import java.awt.*;

import static java.lang.Math.abs;

public class Camera {
    V3 O = new V3(0,0,0);
    V3 i = new V3(1,0,0);
    V3 j = new V3(0,1,0);
    V3 k = new V3(0,0,1);

    V3 E = new V3(0,0,0);
    V3 D = new V3(1,0,0);
    V3 U = new V3(0,1,0);
    V3 R = new V3(0,0,1);

    double zoom = 6;

    private double d,u,r;

    S2 s2;

    Camera(int sx,int sy,int ox,int oy){
        s2 = new S2(sx,sy,ox,oy);
    }


    V2 projection(V3 p){
        V3 EP = p.subtract(E);
         d = D.scalar(EP); // direction
         u = U.scalar(EP);
         r = R.scalar(EP);
        double rm = r* zoom /d;
        double um = u* zoom /d;

        return new V2(rm,um);
    }


    void moveCamTo(V3 p){
        E= new V3(p.getX(), p.getY(),p.getZ());
    }

    void directCam(V3 p){
        D = p.subtract(E).normalize();
        R = D.crossProduct(k).normalize(); // vandret
        U = R.crossProduct(D);
    }
    void homeCam(){
        D = i;
        U = k;
        R = j;
        E = new V3 (zoom,10,5);
    }

    void drawLine(Graphics g, V3 p1, V3 p2){
        s2.drawLine(g,projection(p1),projection(p2));
    }
    void drawPoint(Graphics g, V3 p){
        s2.drawPoint(g, projection(p));
    }

    void drawXAxis(Graphics g,int axisLength, int markingInterval){
        V3 p = new V3(axisLength,0,0);
        V2 pp = projection(p);
        V3 p0 = new V3(0,0,0);
        V2 pp0 = projection(p0);
        s2.drawString(g,"X",pp,1,0);
        s2.drawLine(g,pp0,pp);
        for(int i=0; i<=(axisLength/markingInterval); i++){
            V2 v1 = projection(new V3((markingInterval)*i,0.1,0)) ;
            V2 v2 = projection(new V3((markingInterval)*i,-0.1,0));
            s2.drawLine(g,v1,v2);
        }

    }
    void drawYAxis(Graphics g,int axisLength, int markingInterval){
        V3 p = new V3(0,axisLength,0);
        V2 pp = projection(p);
        V3 p0 = new V3(0,0,0);
        V2 pp0 = projection(p0);
        s2.drawLine(g,pp0,pp);
        s2.drawString(g,"Y",pp,1,0);
        for(int i=0; i<=(axisLength/markingInterval); i++){
            V2 v1 = projection(new V3(0,markingInterval*i,0.1));
            V2 v2 = projection(new V3(0,markingInterval*i,-0.1));
            s2.drawLine(g,v1,v2);

        }

    }
    void drawZAxis(Graphics g,int axisLength, int markingInterval){
        V3 p = new V3(0,0,axisLength);
        V2 pp = projection(p);
        V3 p0 = new V3(0,0,0);
        V2 pp0 = projection(p0);

        s2.drawLine(g,pp0,pp);
        s2.drawString(g,"Z",pp,1,0);
        for(int i=0; i<=(axisLength/markingInterval); i++){
            V2 v1 = projection(new V3(0.1,0,(markingInterval)*i));
            V2 v2 = projection(new V3(-0.1,0,(markingInterval)*i));
            s2.drawLine(g,v1,v2);

        }

    }

    public void showShape(Graphics g, Object object, V3 light) {
         for (Surface surface : object.getSurfaces()){
             //System.out.println(surface.getNormal().scalar(light));
             if(object.getCenter().subtract(E).normalize().scalar(surface.getNormal())<0) {
                 g.setColor(new Color(0, (int) (abs(light.scalar(surface.getNormal())) * 250), 0));
                 s2.drawFilledTriangle(g, projection(surface.getO()), projection(surface.getP1()), projection(surface.getP2()));
             }
         }
    }







}
