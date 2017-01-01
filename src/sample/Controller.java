package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.logic.Drawing;
import sample.model.Model;
import sample.model.Scene;

import java.util.ArrayList;


public class Controller {

    @FXML
    Pane canvas;

    private Drawing drawing;

    public Controller(){
        drawing = null;
    }

    @FXML
    public void initialize(){
        ArrayList<Model> models = new ArrayList<>();
        models.add(Model.fromFile("C:/Users/Sklep/Desktop/example.brp"));
        Scene scene = new Scene(new double[]{0,0,-50}, new double[]{0,0,0}, new double[]{-10,-30,20}, models);
        drawing = new Drawing(canvas, scene);
        drawing.drawScene();
    }

    public void moveUp(){
        drawing.getScene().moveUp(1);
        drawing.drawScene();
    }

    public void moveDown(){
        drawing.getScene().moveDown(1);
        drawing.drawScene();
    }

    public void moveLeft(){
        drawing.getScene().moveLeft(1);
        drawing.drawScene();
    }

    public void moveRight(){
        drawing.getScene().moveRight(1);
        drawing.drawScene();
    }

    public void turnUp(){
        drawing.getScene().turnUp(10);
        drawing.drawScene();
    }

    public void turnDown(){
        drawing.getScene().turnDown(10);
        drawing.drawScene();
    }

    public void turnLeft(){
        drawing.getScene().turnLeft(10);
        drawing.drawScene();
    }

    public void turnRight(){
        drawing.getScene().turnRight(10);
        drawing.drawScene();
    }

    public void zoomIn(){
        drawing.getScene().zoomIn(1);
        drawing.drawScene();
    }

    public void zoomOut(){
        drawing.getScene().zoomOut(1);
        drawing.drawScene();
    }
}
