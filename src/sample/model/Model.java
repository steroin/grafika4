package sample.model;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Sergiusz on 2016-12-23.
 */
public class Model {
    private ArrayList<double[]> points;
    private ArrayList<int[]> triangles;

    public Model(ArrayList<double[]> points, ArrayList<int[]> triangles){
        this.points = points;
        this.triangles = triangles;
    }

    public ArrayList<double[]> getPoints() {
        return points;
    }

    public ArrayList<int[]> getTriangles() {
        return triangles;
    }

    public static Model fromFile(File f){
        Model m = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            ArrayList<double[]> points = new ArrayList<>();
            ArrayList<int[]> triangles = new ArrayList<>();

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
                line = br.readLine();
                String[] tempArray = line.split(",");
                int[] triangle = {Integer.parseInt(tempArray[0]), Integer.parseInt(tempArray[1]), Integer.parseInt(tempArray[2])};
                triangles.add(triangle);
            }

            m = new Model(points, triangles);
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
