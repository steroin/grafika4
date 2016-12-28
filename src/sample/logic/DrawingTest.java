package sample.logic;

import org.junit.Assert;
import org.junit.Test;
import sample.model.Scene;

import static org.junit.Assert.*;

/**
 * Created by Sergiusz on 2016-12-27.
 */
public class DrawingTest {

    @Test
    public void proj() throws Exception {
       /* Drawing d = new Drawing(null, new Scene(null, null, null));
        double[] point = new double[]{12,5,0};
        double[] viewPoint = new double[]{0,0,-10};

        double[] res = d.proj(viewPoint, point);
        System.out.println("["+res[0]+","+res[1]+","+res[2]+"]");*/
    }

    @Test
    public void moveViewPoint() throws Exception {
       /* Drawing d = new Drawing(null, new Scene(null, null, null));
        double[] point = new double[]{4,10,13};
        double[] viewPoint = new double[]{-4,16,9};
        double[] res = d.moveViewPoint(point, viewPoint);
        System.out.println("["+res[0]+","+res[1]+","+res[2]+"]");
        Assert.assertEquals(0, res[0], 0.01);
        Assert.assertEquals(0, res[1], 0.01);

        point = new double[]{23,48,-192};
        viewPoint = new double[]{227,113,421};
        res = d.moveViewPoint(point, viewPoint);
        System.out.println("["+res[0]+","+res[1]+","+res[2]+"]");
        Assert.assertEquals(0, res[0], 0.01);
        Assert.assertEquals(0, res[1], 0.01);

        for(int i=0;i<200;i++){
            double x1 = (Math.random()*2-1)+1000;
            double y1 = (Math.random()*2-1)+1000;
            double z1 = (Math.random()*2-1)+1000;
            double x2 = (Math.random()*2-1)+1000;
            double y2 = (Math.random()*2-1)+1000;
            double z2 = (Math.random()*2-1)+1000;
            res = d.moveViewPoint(new double[]{x1,y1,z1}, new double[]{x2,y2,z2});
            System.out.println("["+res[0]+","+res[1]+","+res[2]+"]");
            Assert.assertEquals(0, res[0], 0.01);
            Assert.assertEquals(0, res[1], 0.01);
        }*/
    }

    @Test
    public void getProjection() throws Exception {/*
        Drawing d = new Drawing(null, new Scene(null, null, null));
        Assert.assertArrayEquals(new double[]{4,10}, d.getProjection(new double[]{4,10,0}, new double[]{0,0,-10}), 1.00);
        Assert.assertArrayEquals(new double[]{0,0}, d.getProjection(new double[]{0,0,0}, new double[]{0,0,-10}), 1.001);
        Assert.assertArrayEquals(new double[]{0,0}, d.getProjection(new double[]{2,6,10}, new double[]{2,6,6}), 1.001);
        Assert.assertArrayEquals(new double[]{0,0}, d.getProjection(new double[]{0,0,10}, new double[]{0,0,-10}), 1.001);*/
    }

}