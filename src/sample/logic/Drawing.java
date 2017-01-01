package sample.logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
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

    private double getCanvasWidth(){
        return canvas.getPrefWidth();
    }

    private double getCanvasHeight(){
        return canvas.getPrefHeight();
    }

    private double[] mapScreenToCanvas(double[] coords){
        double[] screen = scene.getScreen();
        double xOffset = canvas.getPrefWidth()/2;
        double yOffset = canvas.getPrefHeight()/2;

        double screenWidth = Math.abs(screen[1]-screen[0]);
        double screenHeight = Math.abs(screen[3]-screen[2]);

        double x = (coords[0]/screenWidth)*canvas.getPrefWidth()+xOffset;
        double y = canvas.getPrefHeight()-((coords[1]/screenHeight)*canvas.getPrefHeight()+yOffset);
        return new double[]{x,y,coords[2]};
    }


    public Scene getScene(){
        return scene;
    }

    public double[] getProjection2(double[] point){
        double[] viewPoint = scene.getViewPoint();
        double[] middle = scene.getSceneMiddle();

        Transform t = new Transform();
        t.addTranslation(-middle[0], -middle[1], -middle[2]);
        double newX = viewPoint[0]-middle[0];
        double newY = viewPoint[1]-middle[1];
        double newZ = viewPoint[2]-middle[2];
        t.addOYRotation(Math.PI-Math.atan2(newX, newZ));
        t.addOXRotation(Math.atan2(newY, Math.sqrt(newX*newX + newZ*newZ)));
        viewPoint = t.tranformPoint(viewPoint);
        point = t.tranformPoint(point);

        double d = Math.abs(viewPoint[2]);
        double z = point[2];
        double factor = d/(d+z);
        return new double[]{point[0]*factor, point[1]*factor, z};
    }


    private void drawAxes(){
        double[] startX = new double[]{-1000,0,0};
        double[] startOY = new double[]{0,-1000,0};
        double[] startOZ = new double[]{0,0,-1000};
        double[] endOX = new double[]{1000,0,0};
        double[] endOY = new double[]{0,1000,0};
        double[] endOZ = new double[]{0,0,1000};

        double[] projectedMiddle = mapScreenToCanvas(getProjection2(new double[]{0,0,0}));
        double[] projectedStartOX = mapScreenToCanvas(getProjection2(startX));
        double[] projectedStartOY = mapScreenToCanvas(getProjection2(startOY));
        double[] projectedStartOZ = mapScreenToCanvas(getProjection2(startOZ));
        double[] projectedEndOX = mapScreenToCanvas(getProjection2(endOX));
        double[] projectedEndOY = mapScreenToCanvas(getProjection2(endOY));
        double[] projectedEndOZ = mapScreenToCanvas(getProjection2(endOZ));

        Line ox = new Line(projectedMiddle[0], projectedMiddle[1], projectedStartOX[0], projectedStartOX[1]);
        Line oy = new Line(projectedMiddle[0], projectedMiddle[1], projectedStartOY[0], projectedStartOY[1]);
        Line oz = new Line(projectedMiddle[0], projectedMiddle[1], projectedStartOZ[0], projectedStartOZ[1]);
        ox.setStroke(Color.RED);
        oy.setStroke(Color.GREEN);
        oz.setStroke(Color.BLUE);
        canvas.getChildren().addAll(ox, oy, oz);

        ox = new Line(projectedMiddle[0], projectedMiddle[1], projectedEndOX[0], projectedEndOX[1]);
        oy = new Line(projectedMiddle[0], projectedMiddle[1], projectedEndOY[0], projectedEndOY[1]);
        oz = new Line(projectedMiddle[0], projectedMiddle[1], projectedEndOZ[0], projectedEndOZ[1]);
        ox.setStroke(Color.RED);
        oy.setStroke(Color.GREEN);
        oz.setStroke(Color.BLUE);
        canvas.getChildren().addAll(ox, oy, oz);
    }

    public void drawScene(){
        int width = (int)canvas.getPrefWidth();
        int height = (int)canvas.getPrefHeight();
        WritableImage img = new WritableImage(width, height);
        double[][] bufferZ = new double[width][height];
        canvas.getChildren().setAll();
        drawAxes();

        for(Model m : scene.getModels()){
            ArrayList<double[]> projectedPoints = new ArrayList<>();

            for(double[] p : m.getPoints()){
                projectedPoints.add(mapScreenToCanvas(getProjection2(p)));
            }

            for(int i=0;i<m.getTriangles().size();i++){
                int[] tr = m.getTriangles().get(i);
                Color c = m.getColors().get(i);
                double[] p1 = projectedPoints.get(tr[0]);
                double[] p2 = projectedPoints.get(tr[1]);
                double[] p3 = projectedPoints.get(tr[2]);
                drawTriangle(p1, p2, p3, c, img, bufferZ, new double[][]{p1, p2, p3});
            }
        }

        ImageView iv = new ImageView();
        iv.setImage(img);
        canvas.getChildren().add(iv);
    }

    private double[] vectorProduct(double[] v1, double[] v2){
        return new double[]{v1[1]*v2[2]-v1[2]*v2[1], v1[2]*v2[0]-v1[0]*v2[2], v1[0]*v2[1]-v1[1]*v2[0]};
    }

    private void drawTriangle(double[] p1, double[] p2, double[] p3, Color color, WritableImage img, double[][] bufferZ, double[][] tr){
        PixelWriter pw = img.getPixelWriter();
        int minX = Math.min(Math.min((int) p1[0], (int) p2[0]), (int)p3[0]);
        int maxX = Math.max(Math.max((int) p1[0], (int) p2[0]), (int)p3[0]);
        int minY = Math.min(Math.min((int) p1[1], (int) p2[1]), (int)p3[1]);
        int maxY = Math.max(Math.max((int) p1[1], (int) p2[1]), (int)p3[1]);

        System.out.println(img.getWidth()+" x "+img.getHeight());
        if(minX<0)minX=0;
        else if(minX>img.getWidth())minX = (int)img.getWidth()-1;
        if(maxX<0)maxX=0;
        else if(maxX>img.getWidth())maxX = (int)img.getWidth()-1;
        if(minY<0)minY=0;
        else if(minY>img.getHeight())minY = (int)img.getHeight()-1;
        if(maxY<0)maxY=0;
        else if(maxY>img.getHeight())maxY = (int)img.getHeight()-1;

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                p1[0], p1[1],
                p2[0], p2[1],
                p3[0], p3[1]
        );

        for(int i=minX;i<=maxX;i++){
            for(int j=minY;j<=maxY;j++){
                double depth = getPixelDepth(i, j, tr);
                if(triangle.contains(i,j) && (bufferZ[i][j]==0 || bufferZ[i][j]>depth)){
                    bufferZ[i][j] = depth==0?0.0000000001:depth;
                    pw.setColor(i,j,color);
                }

                //if(triangle.contains(i,j))pw.setColor(i,j,color);
            }
        }
    }

    private double getPixelDepth(double x, double y, double[][] t){
        double w = t[0][0]*t[1][1]*t[2][2] + t[0][1]*t[1][2]*t[2][0] + t[0][2]*t[1][0]*t[2][1] - t[0][2]*t[1][1]*t[2][0] - t[0][0]*t[1][2]*t[2][1] - t[0][1]*t[1][0]*t[2][2];
        double w1 = -t[1][1]*t[2][2] - t[0][1]*t[1][2] - t[0][2]*t[2][1] + t[0][2]*t[1][1] + t[1][2]*t[2][1] + t[0][1]*t[2][2];
        double w2 = -t[0][0]*t[2][2] - t[1][2]*t[2][0] - t[0][2]*t[1][0] + t[0][2]*t[2][0] + t[0][0]*t[1][2] + t[1][0]*t[2][2];
        double w3 = -t[0][0]*t[1][1] - t[0][1]*t[2][0] - t[1][0]*t[2][1] + t[1][1]*t[2][0] + t[0][0]*t[2][1] + t[0][1]*t[1][0];

        double a = w1/w;
        double b = w2/w;
        double c = w3/w;
        double d = 1;

        return -(a*x+b*y+d)/c;
    }
}
