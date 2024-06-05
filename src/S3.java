import java.awt.*;

import static java.lang.Math.*;


public class S3 {
    private V3 dP;
    private V3 viewDirection;
    private S2 s2;


    public S3(V3 dP,V3 viewDirection,S2 s2) {
        this.dP = dP;
        this.viewDirection = viewDirection;
        this.s2 = s2;

    }

    V2 projectOrtogonal(V3 v3){
        return new V2(v3.getY(), v3.getZ());
    }

    /*void drawAxis(Graphics g){
        s2.drawYAxis(g,);
        s2.drawXAxis();
    }*/

    void drawPoint(Graphics g, V3 p){
        s2.drawPoint(g, projectOrtogonal(p));
    }
    void drawLine(Graphics g, V3 p1,V3 p2){
        s2.drawLine(g,projectOrtogonal(p1),projectOrtogonal(p2));
    }

    void drawXAxis(Graphics g,int axisLength, int markingInterval){
        V3 p = new V3(axisLength,0,0);
        V2 pp = p.toV2(dP);
        V3 p0 = new V3(0,0,0);
        V2 pp0 = p0.toV2(dP);
        s2.drawString(g,"X",pp,1,0);
        s2.drawLine(g,pp0,pp);
        for(int i=0; i<=(axisLength/markingInterval); i++){
            V2 v1 = new V3((markingInterval)*i,0.1,0).toV2(dP);
            V2 v2 = new V3((markingInterval)*i,-0.1,0).toV2(dP);
            s2.drawLine(g,v1,v2);
        }

    }
    void drawYAxis(Graphics g,int axisLength, int markingInterval){
        V3 p = new V3(0,axisLength,0);
        V2 pp = p.toV2(dP);
        V3 p0 = new V3(0,0,0);
        V2 pp0 = p0.toV2(dP);
        s2.drawLine(g,pp0,pp);
        s2.drawString(g,"Y",pp,1,0);
        for(int i=0; i<=(axisLength/markingInterval); i++){
            V2 v1 = new V3(0,markingInterval*i,0.1).toV2(dP);
            V2 v2 = new V3(0,markingInterval*i,-0.1).toV2(dP);
            s2.drawLine(g,v1,v2);

        }

    }
    void drawZAxis(Graphics g,int axisLength, int markingInterval){
        V3 p = new V3(0,0,axisLength);
        V2 pp = p.toV2(dP);
        V3 p0 = new V3(0,0,0);
        V2 pp0 = p0.toV2(dP);

        s2.drawLine(g,pp0,pp);
        s2.drawString(g,"Z",pp,1,0);
        for(int i=0; i<=(axisLength/markingInterval); i++){
            V2 v1 = new V3(0.1,0,(markingInterval)*i).toV2(dP);
            V2 v2 = new V3(-0.1,0,(markingInterval)*i).toV2(dP);
            s2.drawLine(g,v1,v2);

        }

    }

    void xzGrid(Graphics g,int xLength,int zLength, int distance){
        for(int i=0; i<=xLength/distance; i++){
            V2 p0 = new V3(i*distance,0,0).toV2(dP);
            V2 p = new V3(i*distance,0,zLength).toV2(dP);
            s2.drawLine(g,p0,p);
        }
        for(int j=0; j<=zLength/distance; j++){
            V2 p0 = new V3(0,0,j*distance).toV2(dP);
            V2 p = new V3(xLength,0,j*distance).toV2(dP);
            s2.drawLine(g,p0,p);
        }
    }
    void yzGrid(Graphics g,int yLength,int zLength, int distance){
        for(int i=0; i<=yLength/distance; i++){
            V2 p0 = new V3(0,i*distance,0).toV2(dP);
            V2 p = new V3(0,i*distance,zLength).toV2(dP);
            s2.drawLine(g,p0,p);
        }
        for(int j=0; j<=zLength/distance; j++){
            V2 p0 = new V3(0,0,j*distance).toV2(dP);
            V2 p = new V3(0,yLength,j*distance).toV2(dP);
            s2.drawLine(g,p0,p);
        }
    }



    public V3[][] newDoughnut(V3 ocR, double rR, double rC){

        double fullRotation = PI*2;
        double incrementation = 0.1;
        V3[][] shape = new V3[(int)(fullRotation/incrementation)+1][(int)(fullRotation/incrementation)+1];
        boolean rotationBandMade = false;
        int j = 0;
        for(double aR = 0; aR<fullRotation; aR+=incrementation){
            V3 cRcC = new V3(cos(aR),0,sin(aR)).factor(rR);
            M3 turningM = new M3(cos(aR),0,0,
                                        0,1,0,
                                        0,0,sin(aR));
            //V2 oP2DPrevious = null;
            int i = 0;
            for (double aC = 0; aC<=fullRotation; aC+=incrementation){
                V3 cCp = new V3(cos(aC),sin(aC),0).factor(rC);
                V3 cRp = turningM.multiplyMV(cCp).add(cRcC);
                V3 oP = ocR.add(cRp);

                shape[i][j] = oP;

                i++;
            }

            j++;
        }
        return shape;
    }

    public V3[][] ball(V3 center,double radius){
        double halfCircle = PI;
        double circle = 2*PI;
        double increment = 0.1;
        V3[][] shape = new V3[(int)(halfCircle/increment)+1][(int)(circle/increment)+1];// = new HashMap<>();
        int j=0;
        for(double ah = 0; ah<circle; ah+=increment) {

            int i=0;
            for(double av = 0; av<halfCircle; av+=increment) {

                //V3 cP = new V3(cos(av)*cos(ah),sin(ah),sin(av)*cos(ah)).factor(radius);
                V3 cP = new V3(cos(av)*sin(ah),-cos(ah),sin(av)*sin (ah)).factor(radius);
                V3 oP = center.add(cP);
                shape[i][j] = oP;

                i++;
            }
            j++;
        }
        return shape;
    }
    public Surface[] newBall(V3 center,double radius){
        double halfCircle = PI;
        double circle = 2*PI;
        double increment = 0.1;
        Surface[] surfaces = new Surface[((int)(halfCircle/increment)+1)*((int)(circle/increment)+1)];
        int j=0;
        int n = 0;
        for(double ah = 0; ah<circle; ah+=increment) {

            int i=0;
            for(double av = 0; av<halfCircle; av+=increment) {

                //V3 cP = new V3(cos(av)*cos(ah),sin(ah),sin(av)*cos(ah)).factor(radius);
                V3 cP1 = new V3(cos(av)*sin(ah),-cos(ah),sin(av)*sin (ah)).factor(radius);
                V3 oP1 = center.add(cP1);


                surfaces[n] = null;
                n++;
                i++;
            }
            j++;
        }
        return surfaces;
    }

    public V3[][] cube(V3 center, double sideLength){
        V3[][] shape = new V3[4][4];
        for (int j = 0; j<4; j++){

            int[] f = {1,1,-1,-1,1};
                for(int i =0; i<4; i++){
                    if(j==0){
                        shape[i][j] = new V3(0,0.5*sideLength,0);
                    }else if(j==3){
                        shape[i][j] = new V3(0,-0.5*sideLength,0);
                    }else {
                        shape[i][j] = new V3(0.5 * sideLength * f[i], 0.5 * sideLength * f[i + 1], -0.5 * sideLength + (0.5 * sideLength * j-1)).add(center);
                    }

                }



        }

        return shape;
    }

    public void showShape(Graphics g, V3[][] shape, V3 light){


        V3 a,b,c,d;
        V2 aa,bb,cc,dd;
        int iLength = shape.length;
        int jLength = shape[0].length;
        for (int j=0; j<jLength-1; j++) {
            for (int i = 0; i < iLength; i++) {
                a = shape[i][j];
                aa = a.toV2(dP);
                b = shape[i][j + 1];
                bb = b.toV2(dP);
                if(i==iLength-1){
                    c = shape[0][j];
                    d = shape[0][j + 1];
                }else{
                    c = shape[i + 1][j];
                    d = shape[i + 1][j + 1];
                }
                cc = c.toV2(dP);
                dd = d.toV2(dP);

                V3 normalLocal = a.subtract(b).crossProduct(c.subtract(b)).normalize();
                g.setColor(Color.red);
                s2.drawLine(g,bb,b.add(normalLocal).toV2(dP));
                double projection =0;
                //if(normalLocal.scalar(new V3(0,0,-1))<=0) {
                if(normalLocal.getZ()<=0){

                    projection = light.scalar(normalLocal);

                    System.out.println("blue: "+ projection);
                    g.setColor(new Color(0 , 0 , (int)(abs(projection)*250)));
                    s2.drawFilledTriangle(g, aa, bb, cc);
                }
                normalLocal = c.subtract(b).crossProduct(d.subtract(b)).normalize();
                g.setColor(Color.green);
                s2.drawLine(g,bb,b.add(normalLocal).toV2(dP));
                //if(normalLocal.scalar(new V3(0,0,-1))<=0) {
                if(normalLocal.getZ()<=0){

                    projection = light.scalar(normalLocal);
                    System.out.println(projection);
                    g.setColor(new Color(0, 0, (int)(abs(projection)*250)));
                    s2.drawFilledTriangle(g,dd,bb,cc);
                }
            }
        }

    }
}
