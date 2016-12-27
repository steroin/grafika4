package sample.model;

import java.util.ArrayList;

/**
 * Created by Sergiusz on 2016-12-23.
 */
public class Scene {

    private double[] viewPoint;
    private double[] lightPoint;
    private ArrayList<Model> models;

    public Scene(double[] viewPoint, double[] lightPoint, ArrayList<Model> models){
        this.viewPoint = viewPoint;
        this.lightPoint = lightPoint;
        this.models = models;
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
}
