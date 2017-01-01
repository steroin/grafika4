package sample.model;

import java.util.ArrayList;

/**
 * Created by Sergiusz on 2016-12-23.
 */
public class Scene {

    private double[] viewPoint;
    private double[] sceneMiddle;
    private double[] lightPoint;
    private ArrayList<Model> models;
    private double cameraAngle;
    private int swap;


    public Scene(double[] viewPoint, double[] sceneMiddle, double[] lightPoint, ArrayList<Model> models){
        this.viewPoint = viewPoint;
        this.sceneMiddle = sceneMiddle;
        this.lightPoint = lightPoint;
        this.models = models;
        cameraAngle = 45;
        swap = 1;
    }


    public double[] getSceneMiddle() {
        return sceneMiddle;
    }

    public void setSceneMiddle(double x, double y, double z) {
        sceneMiddle[0] = x;
        sceneMiddle[1] = y;
        sceneMiddle[2] = z;
    }

    public double getCameraDistance() {
        return Math.sqrt((viewPoint[0]-sceneMiddle[0])*(viewPoint[0]-sceneMiddle[0]) + (viewPoint[1]-sceneMiddle[1])*(viewPoint[1]-sceneMiddle[1]) + (viewPoint[2]-sceneMiddle[2])*(viewPoint[2]-sceneMiddle[2]));
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
        viewPoint[0] = x;
        viewPoint[1] = y;
        viewPoint[2] = z;
    }

    public double[] getLightPoint() {
        return lightPoint;
    }

    public ArrayList<Model> getModels(){
        return models;
    }

    public double[] getScreen(){
        //[xFrom, xTo, yFrom, yTo]
        double a = 2*getCameraDistance()/(Math.tan(Math.toRadians(90-cameraAngle/2)));
        return new double[]{-a/2, a/2, -a/2, a/2};
    }

    public double[] cartesianToSpherical(double[] cartesian){
        double r = Math.sqrt(cartesian[0]*cartesian[0] + cartesian[1]*cartesian[1] + cartesian[2]*cartesian[2]);
        double latitude = cartesian[0]==0?0:Math.atan2(cartesian[2], cartesian[0]);
        double longitude = r==0?0:Math.acos(cartesian[1]/r);
        return new double[]{r, latitude, longitude};
    }

    public double[] sphericalToCartesian(double[] spherical){

        double x = spherical[0]*Math.cos(spherical[1])*Math.sin(spherical[2]);
        double y = spherical[0]*Math.cos(spherical[2]);

        double z = spherical[0]*Math.sin(spherical[1])*Math.sin(spherical[2]);

        return new double[]{x,y,z};
    }

    public void zoomIn(double by){
        double[] spherical = cartesianToSpherical(viewPoint);
        if(spherical[0]-by<1)by = 0;
        spherical[0]-=by;
        double[] cartesian = sphericalToCartesian(spherical);
        viewPoint = cartesian;
    }

    public void zoomOut(double by){
        double[] spherical = cartesianToSpherical(viewPoint);
        spherical[0]+=by;
        double[] cartesian = sphericalToCartesian(spherical);
        viewPoint = cartesian;
    }

    public void moveUp(double by){
        viewPoint[1]+=by;
        sceneMiddle[1]+=by;
    }

    public void moveDown(double by){
        viewPoint[1]-=by;
        sceneMiddle[1]-=by;
    }

    public void moveLeft(double by){
        Transform t = new Transform();
        double newX = viewPoint[0]-sceneMiddle[0];
        double newY = viewPoint[1]-sceneMiddle[1];
        double newZ = viewPoint[2]-sceneMiddle[2];
        double alfa = Math.PI-Math.atan2(newX, newZ);
        double beta = Math.atan2(newY, Math.sqrt(newX*newX + newZ*newZ));
        t.addTranslation(-sceneMiddle[0], -sceneMiddle[1], -sceneMiddle[2]);
        t.addOYRotation(alfa);
        t.addOXRotation(beta);
        t.addTranslation(-by, 0, 0);
        t.addOYRotation(-alfa);
        t.addOXRotation(-beta);
        t.addTranslation(sceneMiddle[0], sceneMiddle[1], sceneMiddle[2]);
        viewPoint = t.tranformPoint(viewPoint);
        sceneMiddle = t.tranformPoint(sceneMiddle);
    }

    public void moveRight(double by){
        moveLeft(-by);
    }

    public void turnUp(double by){
        double[] spherical = cartesianToSpherical(viewPoint);

        if(spherical[2]+Math.toRadians(by)>Math.PI)spherical[2] = Math.PI;
        else spherical[2]+=Math.toRadians(by);
        double[] cartesian = sphericalToCartesian(spherical);
        viewPoint = cartesian;
    }

    public void turnDown(double by){
        double[] spherical = cartesianToSpherical(viewPoint);
        if(spherical[2]-Math.toRadians(by)<=0)spherical[2] = 0.000000000001;
        else spherical[2]-=Math.toRadians(by);
        double[] cartesian = sphericalToCartesian(spherical);
        viewPoint = cartesian;
    }

    public void turnLeft(double by){
        double[] spherical = cartesianToSpherical(viewPoint);
        spherical[1]+=Math.toRadians(by);
        double[] cartesian = sphericalToCartesian(spherical);
        viewPoint = cartesian;
    }

    public void turnRight(double by){
        double[] spherical = cartesianToSpherical(viewPoint);
        spherical[1]-=Math.toRadians(by);
        double[] cartesian = sphericalToCartesian(spherical);
        viewPoint = cartesian;
    }
}
