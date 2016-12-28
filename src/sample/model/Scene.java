package sample.model;

import java.util.ArrayList;

/**
 * Created by Sergiusz on 2016-12-23.
 */
public class Scene {

    private double[] viewPoint;
    private double[] lightPoint;
    private ArrayList<Model> models;
    private double cameraDistance;
    private double cameraAngle;

    public Scene(double[] viewPoint, double[] lightPoint, ArrayList<Model> models){
        this.viewPoint = viewPoint;
        this.lightPoint = lightPoint;
        this.models = models;
        cameraDistance = 10;
        cameraAngle = 45;
    }

    public double getCameraDistance() {
        return cameraDistance;
    }

    public void setCameraDistance(double distance) {
        this.cameraDistance = distance;
    }

    public double getCameraAngle() {
        return cameraAngle;
    }

    public void setCameraAngle(double cameraAngle) {
        this.cameraAngle = cameraAngle;
    }

    public double[] getViewPoint() {
        return viewPoint;
    }

    public void setViewPoint(double x, double y, double z){
        viewPoint = new double[]{x,y,z};
    }

    public double[] getLightPoint() {
        return lightPoint;
    }

    public ArrayList<Model> getModels(){
        return models;
    }

    public double[] getScreen(){
        //[xFrom, xTo, yFrom, yTo]
        double a = 2*cameraDistance/(Math.tan(Math.toRadians(90-cameraAngle/2)));
        return new double[]{-a/2, a/2, -a/2, a/2};
    }

}
