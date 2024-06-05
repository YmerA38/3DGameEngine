import java.awt.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class S2 {
    V2 O; //origon
    M2 S; //scale
    M2 F; // flip
    M2 T; // S*F

    S2(int sx, int sy, int Ox, int Oy){
        O = new V2(Ox,Oy);

        S = new M2(sx,0,
                   0,sy);

        F = new M2(1,0,
                  0,-1);

        T = F.mulMM(S);
    }

    V2 transform(V2 v){
        return  T.mulVM(v).add(O);
    }

    V2[] drawLine(Graphics g,V2 p1, V2 p2){
        V2 pp1 = transform(p1);
        V2 pp2 = transform(p2);

        g.drawLine((int)pp1.getX(),(int)pp1.getY(),(int)pp2.getX(),(int)pp2.getY());
        return new V2[]{pp1,pp2}; // returns the star end endpoint of the line
    }
    public void circle(Graphics g, V2 center, double radius){
        double r = transform(new V2(radius,0)).getX();
        V2 c = transform(center);
        g.drawOval((int)Math.round(c.getX()),(int)Math.round(c.getY()),(int)Math.round(2*r),(int)Math.round(2*r));

    }

    public void drawString(Graphics g,String s,V2 point, double distanceCenterFactor, double angel){
       FontMetrics fm = g.getFontMetrics();
       int w = fm.stringWidth(s)/2;
       int h = fm.getHeight()/2;
       V2 stringCenter = new V2(-w,h);
       V2 orientation = new V2(distanceCenterFactor*h* cos(angel),-distanceCenterFactor*h*Math.sin(angel));
       V2 pp = transform(point).add(stringCenter).add(orientation);

       g.drawString(s,(int)pp.getX(),(int)pp.getY());
    }

    void drawXAxis(Graphics g,int axisLength,int markingInterval){
        V2 p = new V2(axisLength,0);
        V2 p0 = new V2(0,0);
        drawLine(g,p0,p);

        for(int i=0; i<=(axisLength/markingInterval); i++){
            V2 v1 = new V2((markingInterval)*i,0.1);
            V2 v2 = new V2((markingInterval)*i,-0.1);
            drawLine(g,v1,v2);
            drawString(g,""+(i*markingInterval),v2,2,-Math.PI/2);
            //g.drawString(""+(i*markingInterval),(int)startEnd[1].getX(),(int)startEnd[1].getY());
        }

    }
    void drawYAxis(Graphics g,int axisLength,int markingInterval){
        V2 p = new V2(0,axisLength);
        V2 p0 = new V2(0,0);
        drawLine(g,p0,p);
        for(int i=0; i<=(axisLength/markingInterval); i++){
            V2 v1 = new V2(0.1,markingInterval*i);
            V2 v2 = new V2(-0.1,markingInterval*i);
            drawLine(g,v1,v2);
            drawString(g,""+(i*markingInterval),v2,2,Math.PI);
        }

    }

    void drawPoint(Graphics g, V2 p){

        V2 pp= transform(p);
        g.fillOval((int)pp.getX(),(int)pp.getY(),5,5);
    }

    void drawShape(Graphics g,V2 centerPoint, double radius, int numberOfPoints, int skips, double angelOrientation ){

        double r = transform(new V2(radius,0)).getX();
        V2 pp = transform(centerPoint);
        double segmentDegrees = 2*Math.PI*(1+skips)/numberOfPoints;
        for (int i=0;i<numberOfPoints;i++){

            double angleFrom = segmentDegrees*i+angelOrientation;
            double angleTo = segmentDegrees*(i+1)+angelOrientation;
            g.drawLine((int)(pp.getX()+(r* cos(angleFrom))),(int)(pp.getY()+(r*Math.sin(angleFrom))),(int)(pp.getX()+(r* cos(angleTo))),(int)(pp.getY()+(r*Math.sin(angleTo))));

        }



    }
    public void drawFilledTriangle(Graphics g, V2 a,V2 b, V2 c){
        a = transform(a);
        b = transform(b);
        c = transform(c);
       int[] x = {(int)a.getX(),(int)b.getX(),(int)c.getX()} ;
       int[] y = {(int)a.getY(),(int)b.getY(),(int)c.getY()} ;

        g.fillPolygon(x,y,3);
    }

    public void rotate(double v) {
        M2 R = new M2( cos(v), -sin(v),
                        sin(v), cos(v));
        T = T.mulMM(R);
    }

    public void moveTo(V2 p) {
        O = transform(p);
    }
}
