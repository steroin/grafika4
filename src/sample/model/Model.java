package sample.model;

import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Sergiusz on 2016-12-23.
 */
public class Model {
    private ArrayList<double[]> points;
    private ArrayList<int[]> triangles;
    private ArrayList<Color> colors;

    public Model(ArrayList<double[]> points, ArrayList<int[]> triangles, ArrayList<Color> colors){
        this.points = points;
        this.triangles = triangles;
        this.colors = colors;
    }

    public ArrayList<double[]> getPoints() {
        return points;
    }

    public ArrayList<int[]> getTriangles() {
        return triangles;
    }

    public ArrayList<Color> getColors(){
        return colors;
    }

    public static Model fromFile(File f){
        Model m = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            ArrayList<double[]> points = new ArrayList<>();
            ArrayList<int[]> triangles = new ArrayList<>();
            ArrayList<Color> colors = new ArrayList<>();

            String line = br.readLine();
            int pointsNum = Integer.parseInt(line);

            for(int i=0;i<pointsNum;i++){
                line = br.readLine();
                String[] tempArray = line.split(",");
                double[] point = {Double.parseDouble(tempArray[0]), Double.parseDouble(tempArray[1]), Double.parseDouble(tempArray[2])};
                points.add(point);
            }
            line = br.readLine();
            int trianglesNum = Integer.parseInt(line);

            for(int i=0;i<trianglesNum;i++){
                Color c = Color.web(br.readLine());
                line = br.readLine();
                String[] tempArray = line.split(",");
                int[] triangle = {Integer.parseInt(tempArray[0]), Integer.parseInt(tempArray[1]), Integer.parseInt(tempArray[2])};
                triangles.add(triangle);
                colors.add(c);
            }

            m = new Model(points, triangles, colors);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return m;
    }

    public static Model fromFile(String file){
        return fromFile(new File(file));
    }
}
