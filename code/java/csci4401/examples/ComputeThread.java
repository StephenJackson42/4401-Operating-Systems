package csci4401.examples;

public class ComputeThread extends Thread {
    double[][] a, b, c;
    int MAX = 300;
    String marker;
    
    public ComputeThread ( String marker) {
        this.marker = marker;
        a = new double[MAX][MAX];
        b = new double[MAX][MAX];
        c = new double[MAX][MAX];
        for( int i=0; i<MAX; i++)
            for( int j=0; j<MAX; j++) {
                a[i][j] = (i+1)*(j+1);
                a[i][j] = (i+2)*(j+2);
            }
    }

    public void run () {
        int i, j, k, loop_cnt = 400;

        for( ; loop_cnt>0; loop_cnt--) {
            for( i=0; i<MAX; i++)
                for( j=0; j<MAX; j++)
                    for( k=0; k<MAX; k++)
                        c[i][j] += a[i][k]*b[k][j];
            System.out.print( marker);
        }
    }

    public static void main( String[] argv) {
        for( int i=0; i<Integer.parseInt( argv[0]); i++) {
            new ComputeThread( ""+i).start();
        }
    }
}
