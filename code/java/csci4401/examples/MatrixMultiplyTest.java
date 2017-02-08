package csci4401.examples;

/**
 * Test code implementing matrix multiplication.
 */
public class MatrixMultiplyTest {

    static int MAX = 500, LOOPS = 10;

    public static void main(String[] argv) {
        double[][] a = new double[MAX][MAX];
        double[][] b = new double[MAX][MAX];
        double[][] c = new double[MAX][MAX];

        for(int i=0; i<MAX; i++) {
            for(int j=0; j<MAX; j++) {
                a[i][j] = (i+1)*(j+1);
                a[i][j] = (i+2)*(j+2);
            }
        }
        long start = System.currentTimeMillis();
        for(int l=0; l<LOOPS; l++) {
            for(int i=0; i<MAX; i++)
                for(int j=0; j<MAX; j++)
                    for(int k=0; k<MAX; k++)
                        c[i][j] += a[i][k]*b[k][j];
        }
        long end = System.currentTimeMillis();
        System.out.println((end-start)/LOOPS);
    }
}
