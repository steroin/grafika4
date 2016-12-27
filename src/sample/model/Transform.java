package sample.model;

/**
 * Created by Sergiusz on 2016-12-23.
 */
public class Transform {

    private double[] matrix;

    public Transform(){
        matrix = new double[]{1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1};
    }

    public void addOZRotation(double alfa){
        double[] newMatrix = new double[16];

        newMatrix[0] = matrix[0]*Math.cos(alfa)-matrix[1]*Math.sin(alfa);
        newMatrix[1] = matrix[0]*Math.sin(alfa)+matrix[1]*Math.cos(alfa);
        newMatrix[2] = matrix[2];
        newMatrix[3] = matrix[3];
        newMatrix[4] = matrix[4]*Math.cos(alfa)-matrix[5]*Math.sin(alfa);
        newMatrix[5] = matrix[4]*Math.sin(alfa)+matrix[5]*Math.cos(alfa);
        newMatrix[6] = matrix[6];
        newMatrix[7] = matrix[7];
        newMatrix[8] = matrix[8]*Math.cos(alfa)-matrix[9]*Math.sin(alfa);
        newMatrix[9] = matrix[8]*Math.sin(alfa)+matrix[9]*Math.cos(alfa);
        newMatrix[10] = matrix[10];
        newMatrix[11] = matrix[11];
        newMatrix[12] = matrix[12]*Math.cos(alfa)-matrix[13]*Math.sin(alfa);
        newMatrix[13] = matrix[12]*Math.sin(alfa)+matrix[13]*Math.cos(alfa);
        newMatrix[14] = matrix[14];
        newMatrix[15] = matrix[15];

        matrix = newMatrix;
    }

    public void addOYRotation(double alfa){
        double[] newMatrix = new double[16];

        newMatrix[0] = matrix[0]*Math.cos(alfa)+matrix[2]*Math.sin(alfa);
        newMatrix[1] = matrix[1];
        newMatrix[2] = -matrix[0]*Math.sin(alfa)+matrix[2]*Math.cos(alfa);
        newMatrix[3] = matrix[3];
        newMatrix[4] = matrix[4]*Math.cos(alfa)+matrix[6]*Math.sin(alfa);
        newMatrix[5] = matrix[5];
        newMatrix[6] = -matrix[4]*Math.sin(alfa)+matrix[6]*Math.cos(alfa);
        newMatrix[7] = matrix[7];
        newMatrix[8] = matrix[8]*Math.cos(alfa)+matrix[10]*Math.sin(alfa);
        newMatrix[9] = matrix[9];
        newMatrix[10] = -matrix[8]*Math.sin(alfa)+matrix[10]*Math.cos(alfa);
        newMatrix[11] = matrix[11];
        newMatrix[12] = matrix[12]*Math.cos(alfa)+matrix[14]*Math.sin(alfa);
        newMatrix[13] = matrix[13];
        newMatrix[14] = -matrix[12]*Math.sin(alfa)+matrix[14]*Math.cos(alfa);
        newMatrix[15] = matrix[15];

        matrix = newMatrix;
    }

    public void addOXRotation(double alfa){
        double[] newMatrix = new double[16];

        newMatrix[0] = matrix[0];
        newMatrix[1] = matrix[1]*Math.cos(alfa)-matrix[2]*Math.sin(alfa);
        newMatrix[2] = matrix[1]*Math.sin(alfa)+matrix[2]*Math.cos(alfa);
        newMatrix[3] = matrix[3];
        newMatrix[4] = matrix[4];
        newMatrix[5] = matrix[5]*Math.cos(alfa)-matrix[6]*Math.sin(alfa);
        newMatrix[6] = matrix[5]*Math.sin(alfa)+matrix[6]*Math.cos(alfa);
        newMatrix[7] = matrix[7];
        newMatrix[8] = matrix[8];
        newMatrix[9] = matrix[9]*Math.cos(alfa)-matrix[10]*Math.sin(alfa);
        newMatrix[10] = matrix[9]*Math.sin(alfa)+matrix[10]*Math.cos(alfa);
        newMatrix[11] = matrix[11];
        newMatrix[12] = matrix[12];
        newMatrix[13] = matrix[13]*Math.cos(alfa)-matrix[14]*Math.sin(alfa);
        newMatrix[14] = matrix[13]*Math.sin(alfa)+matrix[14]*Math.cos(alfa);
        newMatrix[15] = matrix[15];

        matrix = newMatrix;
    }

    public void addScale(double sx, double sy, double sz){
        matrix[0] *= sx;
        matrix[4] *= sx;
        matrix[8] *= sx;
        matrix[12] *= sx;
        matrix[1] *= sy;
        matrix[5] *= sy;
        matrix[9] *= sy;
        matrix[13] *= sy;
        matrix[2] *= sz;
        matrix[6] *= sz;
        matrix[10] *= sz;
        matrix[14] *= sz;
    }

    public void addTranslation(double x, double y, double z){
        matrix[0] += matrix[3]*x;
        matrix[1] += matrix[3]*y;
        matrix[2] += matrix[3]*z;
        matrix[4] += matrix[7]*x;
        matrix[5] += matrix[7]*y;
        matrix[6] += matrix[7]*z;
        matrix[8] += matrix[11]*x;
        matrix[9] += matrix[11]*y;
        matrix[10] += matrix[11]*z;
        matrix[12] += matrix[15]*x;
        matrix[13] += matrix[15]*y;
        matrix[14] += matrix[15]*z;
    }

    public double[] tranformPoint(double x, double y, double z){
        double[] ret = new double[3];
        double w = x*matrix[3]+y*matrix[7]+z*matrix[11]+matrix[15];
        ret[0] = (x*matrix[0]+y*matrix[4]+z*matrix[8]+matrix[12])/w;
        ret[1] = (x*matrix[1]+y*matrix[5]+z*matrix[9]+matrix[13])/w;
        ret[2] = (x*matrix[2]+y*matrix[6]+z*matrix[10]+matrix[14])/w;
        return ret;
    }


    public double[] tranformPoint(double[] p) {
        return tranformPoint(p[0], p[1], p[2]);
    }

    public void printMatrix(){
        System.out.println(matrix[0]+"\t"+matrix[1]+"\t"+matrix[2]+"\t"+matrix[3]);
        System.out.println(matrix[4]+"\t"+matrix[5]+"\t"+matrix[6]+"\t"+matrix[7]);
        System.out.println(matrix[8]+"\t"+matrix[9]+"\t"+matrix[10]+"\t"+matrix[11]);
        System.out.println(matrix[12]+"\t"+matrix[13]+"\t"+matrix[14]+"\t"+matrix[15]);
    }
}
