package sample.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sergiusz on 2016-12-27.
 */
public class TransformTest {
    @Test
    public void addOZRotation() throws Exception {
        Transform t = new Transform();
        double[] point = new double[]{4,0,-2};
        double[] expected = new double[]{-4,0,-2};
        t.addOZRotation(Math.PI);
        Assert.assertArrayEquals(t.tranformPoint(point[0], point[1], point[2]), expected, 0.01);
    }

    @Test
    public void addOYRotation() throws Exception {
        Transform t = new Transform();
        double[] point = new double[]{4,0,0};
        double[] expected = new double[]{-4,0,0};
        t.addOYRotation(Math.PI);
        Assert.assertArrayEquals(t.tranformPoint(point[0], point[1], point[2]), expected, 0.01);
    }

    @Test
    public void addOXRotation() throws Exception {
        Transform t = new Transform();
        double[] point = new double[]{0,0,4};
        double[] expected = new double[]{0,0,-4};
        t.addOXRotation(Math.PI);
        Assert.assertArrayEquals(t.tranformPoint(point[0], point[1], point[2]), expected, 0.01);
    }

}