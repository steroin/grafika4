package sample.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import sample.model.Model;
import sample.model.Scene;
import sample.model.Transform;

import java.util.ArrayList;

/**
 * Created by Sergiusz on 2016-12-23.
 */
public class Drawing {

    private Pane canvas;
    private Scene scene;

    public Drawing(Pane canvas, Scene scene){
        this.canvas = canvas;
        this.scene = scene;
    }


    public Scene getScene(){
        return scene;
    }


    private void drawAxis(){
        double[] viewPoint = scene.getViewPoint();
        double[] middle = new double[]{0,0,0};
        double[] x = new double[]{1000,0,0};
        double[] y = new double[]{0,1000,0};
        double[] z = new double[]{0,0,1000};

        Transform t = new Transform();
        double[] middleT = getProjection(middle, viewPoint);
        double[] xT = getProjection(x, viewPoint);
        double[] yT = getProjection(y, viewPoint);
        double[] zT = getProjection(z, viewPoint);
        Line lineX = new Line(middleT[0]+200, middleT[1]+200, xT[0]+200, xT[1]+200);
        Line lineY = new Line(middleT[0]+200, middleT[1]+200, yT[0]+200, yT[1]+200);
        Line lineZ = new Line(middleT[0]+200, middleT[1]+200, zT[0]+200, zT[1]+200);

        lineX.setStroke(Color.BLACK);
        lineY.setStroke(Color.BLACK);
        lineZ.setStroke(Color.BLACK);

        canvas.getChildren().setAll(lineX, lineY, lineZ);
    }

    public double[] moveViewPoint(double[] point, double[] viewPoint){
        Transform t = new Transform();
        //t.addTranslation(-point[0], -point[1], -point[2]);
        //viewPoint = t.tranformPoint(viewPoint);
        //t.addTranslation(point[0], point[1], point[2]);
        //t.addOYRotation(3*Math.PI/2 - Math.atan2(viewPoint[2], viewPoint[0]));        //krecimy w lewo
        t.addOYRotation(Math.PI/2 + Math.atan2(viewPoint[2], viewPoint[0]));        //krecimy w prawo //dobrze kurwa !


        //
        //viewPoint = t.tranformPoint(viewPoint);
        //t = new Transform();
        //
        double alfa = Math.atan2(viewPoint[1], Math.sqrt(viewPoint[0]*viewPoint[0] + viewPoint[2]*viewPoint[2]));
        t.addOXRotation(Math.PI-alfa);    //krecimy w lewo

        /*double alfa = Math.atan2(viewPoint[1], viewPoint[2]);
        System.out.println("punkt: ["+viewPoint[0]+","+viewPoint[1]+","+viewPoint[2]+"]");
        System.out.println("alfa: "+alfa);
        t.addOXRotation(1.0584068);
        viewPoint = t.tranformPoint(viewPoint);
        System.out.println("rotacja o 1*:");
        alfa = Math.atan2(viewPoint[1], viewPoint[2]);
        System.out.println("punkt: ["+viewPoint[0]+","+viewPoint[1]+","+viewPoint[2]+"]");
        System.out.println("alfa: "+alfa);*/



      //  t.addOXRotation(3*Math.PI/2-Math.atan2(viewPoint[1], viewPoint[2]));    //krecimy w prawo

        //System.out.println("OY atan2("+viewPoint[2]+", "+viewPoint[0]+")");
        //System.out.println(3*Math.PI/2 - Math.atan2(viewPoint[2], viewPoint[0]));

        System.out.println("OX atan2("+viewPoint[1]+", "+viewPoint[2]+")");
        System.out.println(alfa);
        return t.tranformPoint(viewPoint);
    }

    public double[] getProjection(double[] point, double[] viewPoint){
        Transform t = new Transform();
        t.addTranslation(-point[0], -point[1], -point[2]);

        double d = Math.abs(point[2]-viewPoint[2]);

        System.out.println("Point: ["+point[0]+","+point[1]+","+point[2]+"]");
        System.out.println("ViewPoint: ["+viewPoint[0]+","+viewPoint[1]+","+viewPoint[2]+"]");
        System.out.println("ViewPoint po translacji: ["+viewPoint[0]+","+viewPoint[1]+","+viewPoint[2]+"]");
        System.out.println("Obrót OY: "+Math.atan2(viewPoint[0], viewPoint[2]));
        t.addOYRotation(Math.atan2(viewPoint[0], viewPoint[2]));

        Transform t1 = new Transform();
        t1.addOYRotation(Math.atan2(viewPoint[0], viewPoint[2]));
        viewPoint = t1.tranformPoint(viewPoint);
        System.out.println("ViewPoint po obrocie: ["+viewPoint[0]+","+viewPoint[1]+","+viewPoint[2]+"]");

        System.out.println("Obrót OX; "+(-Math.PI / 2 - Math.atan2(viewPoint[2], viewPoint[1])));
        t.addOXRotation(-Math.PI / 2 - Math.atan2(viewPoint[2], viewPoint[1]));
        //t.addOZRotation(fi);
        point = t.tranformPoint(0,1,0);
        System.out.println("Wektor po transformacji: ["+point[0]+","+point[1]+","+point[2]+"]");


        double z = point[2];
        double factor = (d/(d+z));
        System.out.println("d = "+d+", z = "+z+", factor = "+factor);
        System.out.println("result: ["+(point[0]*factor)+","+(point[1]*factor)+"]");
        return new double[]{point[0]*factor, point[1]*factor};
    }

    /*public double[] getProjection(double[] point, double[] viewPoint){
        Transform t = new Transform();
        t.addTranslation(-point[0], -point[1], -point[2]);

        double fi = Math.PI - Math.atan2(viewPoint[0], viewPoint[2]);
        double d = Math.abs(point[2]-viewPoint[2]);

        t.addOYRotation(fi);
        t.addOXRotation(-Math.PI / 2 - Math.atan2(viewPoint[2], viewPoint[1]));
        t.addOZRotation(fi);
        point = t.tranformPoint(Math.sin(fi),Math.cos(fi),0);


        double z = point[2];
        double factor = (d/(d+z));
        return new double[]{point[0]*factor, point[1]*factor};
    }*/

    public void test(){
        double x = 0;
        double y = 10;
        double z = 0;

        double[] viewPoint = new double[]{0,0,-10};
        double[] proj = getProjection(new double[]{x,y,z}, viewPoint);

        System.out.println("Punkt: ["+x+","+y+","+z+"]");
        System.out.println("Widok: ["+viewPoint[0]+","+viewPoint[1]+","+viewPoint[2]+"]");
        System.out.println("Po rzutowaniu: ["+proj[0]+","+proj[1]+"]");
    }

    public void drawScene(){
        drawAxis();
        Transform t = new Transform();
        double[] viewPoint = scene.getViewPoint();
        double[][] axisPoints = new double[][]{
                {0,0,0},
                {1000000000,0,0},
                {0,1000000000,0},
                {0,0,1000000000}
        };

        /*if(!scene.getModels().isEmpty() && !scene.getModels().get(0).getPoints().isEmpty()){
            double[] point = scene.getModels().get(0).getPoints().get(0);
            //System.out.println("Wejściowy punkt: [" + p[0] + ", " + p[1] + ", " + p[2] + "]");

            //System.out.println("matrix1:");
            //t.printMatrix();
            t.addTranslation(-point[0], -point[1], -point[2]);
            //System.out.println("matrix2:");
            //t.printMatrix();
            t.addOYRotation(Math.PI - Math.atan2(viewPoint[0], viewPoint[2]));
            //System.out.println("alfa: "+(Math.PI-Math.atan2(viewPoint[0], viewPoint[2])));
            //System.out.println("matrix3:");
            //t.printMatrix();
            t.addOXRotation(-Math.PI / 2 - Math.atan2(viewPoint[2], viewPoint[1]));
            //System.out.println("matrix4:");
            //t.printMatrix();
        }*/

        //double[] transformedView = t.tranformPoint(viewPoint[0], viewPoint[1], viewPoint[2]);
        /*double[][] axisPrim = new double[4][2];
        for(int i=0;i<axisPoints.length;i++) {
            axisPoints[i] = t.tranformPoint(axisPoints[i][0], axisPoints[i][1], axisPoints[i][2]);
            double d = Math.abs(transformedView[2]);
            double z = Math.abs(axisPoints[i][2]);
            double factor = d/(d+z);
            axisPrim[i][0] = axisPoints[i][0]*factor;
            axisPrim[i][1] = axisPoints[i][1]*factor;
        }


        canvas.getChildren().setAll(new Line(axisPrim[0][0]+200, axisPrim[0][1]+200, axisPrim[1][0]+200, axisPrim[1][1]+200),
                new Line(axisPrim[0][0]+200, axisPrim[0][1]+200, axisPrim[2][0]+200, axisPrim[2][1]+200),
                new Line(axisPrim[0][0]+200, axisPrim[0][1]+200, axisPrim[3][0]+200, axisPrim[3][1]+200));
        */

        for(Model m : scene.getModels()){
            ArrayList<double[]> projectedPoints = new ArrayList<>();

            for(double[] p : m.getPoints()){
                //System.out.println("Punkt po transformacji: ["+transformedP[0]+", "+transformedP[1]+", "+transformedP[2]+"]");
                //System.out.println("Punkt n rzutni: ["+xPrim+", "+yPrim+"]");
                projectedPoints.add(getProjection(p, viewPoint));
            }

            for(int[] tr : m.getTriangles()){
                Polygon triangle = new Polygon();
                double[] p1 = projectedPoints.get(tr[0]);
                double[] p2 = projectedPoints.get(tr[1]);
                double[] p3 = projectedPoints.get(tr[2]);
                /*System.out.println("Obliczone punkty:");
                System.out.println("\t"+p1[0]+", "+p1[1]);
                System.out.println("\t"+p2[0]+", "+p2[1]);
                System.out.println("\t"+p3[0]+", "+p3[1]);*/
                        triangle.getPoints().addAll(
                        p1[0]+200, p1[1]+200,
                        p2[0]+200, p2[1]+200,
                        p3[0]+200, p3[1]+200
                );
                triangle.setStroke(Color.BLACK);
                canvas.getChildren().add(triangle);
            }
        }

    }

    private double[] vectorProduct(double[] v1, double[] v2){
        return new double[]{v1[1]*v2[2]-v1[2]*v2[1], v1[2]*v2[0]-v1[0]*v2[2], v1[0]*v2[1]-v1[1]*v2[0]};
    }
}
