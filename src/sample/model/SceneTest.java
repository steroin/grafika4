package sample.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sergiusz on 2016-12-30.
 */
public class SceneTest {
    @Test
    public void cartesianToSpherical() throws Exception {
        Scene s = new Scene(null, null, null, null);

        for(int i=0;i<10000;i++){
            double[] point = new double[]{(Math.random()*2-1)*100, (Math.random()*2-1)*100, (Math.random()*2-1)*100};
            Assert.assertArrayEquals(point, s.sphericalToCartesian(s.cartesianToSpherical(point)), 0.0001);
        }


        double[] point = new double[]{7,8,9};
        Assert.assertArrayEquals(point, s.sphericalToCartesian(s.cartesianToSpherical(point)), 0.0001);

        point = new double[]{7,8,-9};
        Assert.assertArrayEquals(point, s.sphericalToCartesian(s.cartesianToSpherical(point)), 0.0001);

        point = new double[]{7,-8,9};
        Assert.assertArrayEquals(point, s.sphericalToCartesian(s.cartesianToSpherical(point)), 0.0001);

        point = new double[]{-7,8,9};
        Assert.assertArrayEquals(point, s.sphericalToCartesian(s.cartesianToSpherical(point)), 0.0001);

        point = new double[]{7,-8,-9};
        Assert.assertArrayEquals(point, s.sphericalToCartesian(s.cartesianToSpherical(point)), 0.0001);

        point = new double[]{-7,-8,9};
        Assert.assertArrayEquals(point, s.sphericalToCartesian(s.cartesianToSpherical(point)), 0.0001);

        point = new double[]{-7,8,-9};
        Assert.assertArrayEquals(point, s.sphericalToCartesian(s.cartesianToSpherical(point)), 0.0001);

        point = new double[]{-7,-8,-9};
        Assert.assertArrayEquals(point, s.sphericalToCartesian(s.cartesianToSpherical(point)), 0.0001);
    }

}