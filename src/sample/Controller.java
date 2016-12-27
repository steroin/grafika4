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
import sample.logic.Drawing;
import sample.model.Model;
import sample.model.Scene;

import java.util.ArrayList;


public class Controller {

    @FXML
    Button but1;
    @FXML
    Button but2;
    @FXML
    Pane canvas;

    private Drawing drawing;

    public Controller(){
        drawing = null;
    }

    public void button1_OnClick(){
        canvas.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        ArrayList<Model> models = new ArrayList<>();
        models.add(Model.fromFile("C:/Users/Sklep/Desktop/example.brp"));
        Scene scene = new Scene(new double[]{0,0,0}, new double[]{0,0,0}, models);
        drawing = new Drawing(canvas, scene);
        drawing.drawScene();
        //drawing.test();
    }

    public void up(){
        double[] viewPoint = drawing.getScene().getViewPoint();
        drawing.getScene().setViewPoint(viewPoint[0], viewPoint[1], viewPoint[2]+1);
        drawing.drawScene();
    }
    public void down(){
        double[] viewPoint = drawing.getScene().getViewPoint();
        drawing.getScene().setViewPoint(viewPoint[0], viewPoint[1], viewPoint[2]-1);
        drawing.drawScene();
    }
    public void left(){
        double[] viewPoint = drawing.getScene().getViewPoint();
        drawing.getScene().setViewPoint(viewPoint[0], viewPoint[1]-1, viewPoint[2]);
        drawing.drawScene();
    }

    public void right(){
        double[] viewPoint = drawing.getScene().getViewPoint();
        drawing.getScene().setViewPoint(viewPoint[0], viewPoint[1]+1, viewPoint[2]);
        drawing.drawScene();
    }


    public void forward(){
        double[] viewPoint = drawing.getScene().getViewPoint();
        drawing.getScene().setViewPoint(viewPoint[0]-1, viewPoint[1], viewPoint[2]);
        drawing.drawScene();
    }


    public void backward(){
        double[] viewPoint = drawing.getScene().getViewPoint();
        drawing.getScene().setViewPoint(viewPoint[0]+1, viewPoint[1], viewPoint[2]);
        drawing.drawScene();
    }
}
