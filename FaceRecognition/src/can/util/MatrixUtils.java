package can.util;

import java.util.Random;

/**
 * ROW AND COLUMNS ALWAYS 
 * M X C
 * @author FelipeC
 *
 */


public class MatrixUtils {
	
	private static MatrixUtils matrix;
    private Random random;
    private long seed;
    
    private MatrixUtils() {
        this.seed = System.currentTimeMillis();
        this.random = new Random(seed);
    }
    
    public static MatrixUtils getInstance() {
    	if (matrix == null) {
    		matrix = new MatrixUtils();
    	}
    	return matrix;
    }

    public long getSeed() {
        return seed;
    }


    public Double nextDouble() {
        return random.nextDouble();
    }

    public int nextInt(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("argument must be positive: " + n);
        }
        return random.nextInt(n);
    }


    public long nextLong(long n) {
        if (n <= 0L) {
            throw new IllegalArgumentException("argument must be positive: " + n);
        }

        long r = random.nextLong();
        long m = n - 1;

        // power of two
        if ((n & m) == 0L) {
            return r & m;
        }

        // reject over-represented candidates
        long u = r >>> 1;
        while (u + m - (r = u % n) < 0L) {
            u = random.nextLong() >>> 1;
        }
        return r;
    }

    public int nextInt(int a, int b) {
        if ((b <= a) || ((long) b - a >= Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + ")");
        }
        return a + nextInt(b - a);
    }

    
    public Double nextDouble(Double a, Double b) {
        if (!(a < b)) {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + ")");
        }
        return a + nextDouble() * (b - a);
    }


    public Double[][] random(int m, int n) {
        Double[][] a = new Double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = nextDouble(0.0, 1.0);
            }
        }
        return a;
    }
    
    /**
     * Transpose of a matrix
     *
     * @param a matrix
     * @return b = A^T
     */
    public Double[][] transpose(Double[][] a) {
        int m = a.length;
        int n = a[0].length;
        Double[][] b = new Double[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                b[j][i] = a[i][j];
            }
        }
        return b;
    }

    /**
     * @param a matrix
     * @param b matrix
     * @return c = a + b
     */
    public Double[][] add(Double[][] a, Double[][] b) {
        int m = a.length;
        int n = a[0].length;
        Double[][] c = new Double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }

    /**
     * @param a matrix
     * @param b matrix
     * @return c = a - b
     */
    public Double[][] subtract(Double[][] a, Double[][] b) {
        int m = a.length;
        int n = a[0].length;
        Double[][] c = new Double[m][n];
       
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }

    /**
     * Element wise subtraction
     *
     * @param a scaler
     * @param b matrix
     * @return c = a - b
     */
    public Double[][] subtract(Double a, Double[][] b) {
        int m = b.length;
        int n = b[0].length;
        Double[][] c = new Double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a - b[i][j];
            }
        }
        return c;
    }

    /**
     * @param a matrix
     * @param b matrix
     * @return c = a * b
     */
    public Double[][] dot(Double[][] a, Double[][] b) {
        int m1 = a.length;
        int n1 = a[0].length;
        int m2 = b.length;
        int n2 = b[0].length;
        if (n1 != m2) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        Double[][] c = zeroMatrix(m1,n2);
        for (int i = 0; i < m1; i++) {
            for (int j = 0; j < n2; j++) {
                for (int k = 0; k < n1; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    /**
     * Element wise multiplication
     *
     * @param a matrix
     * @param x matrix
     * @return y = a * x
     */
    public Double[][] multiply(Double[][] x, Double[][] a) {
        int m = a.length;
        int n = a[0].length;

        if (x.length != m || x[0].length != n) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        Double[][] y = new Double[m][n];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                y[j][i] = a[j][i] * x[j][i];
            }
        }
        return y;
    }

    /**
     * Element wise multiplication
     *
     * @param a matrix
     * @param x scaler
     * @return y = a * x
     */
    public Double[][] multiply(Double x, Double[][] a) {
        int m = a.length;
        int n = a[0].length;

        Double[][] y = new Double[m][n];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                y[j][i] = a[j][i] * x;
            }
        }
        return y;
    }

    /**
     * Element wise power
     *
     * @param x matrix
     * @param a scaler
     * @return y
     */
    public Double[][] power(Double[][] x, int a) {
        int m = x.length;
        int n = x[0].length;

        Double[][] y = new Double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                y[i][j] = Math.pow(x[i][j], a);
            }
        }
        return y;
    }

    /**
     * @param a matrix
     * @return shape of matrix a
     */
    public String shape(Double[][] a) {
        int m = a.length;
        int n = a[0].length;
        String Vshape = "(" + m + "," + n + ")";
        return Vshape;
    }

    /**
     * @param a matrix
     * @return sigmoid of matrix a
     */
    public Double[][] sigmoid(Double[][] a) {
        int m = a.length;
        int n = a[0].length;
        Double[][] z = new Double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                z[i][j] = (1.0 / (1 + Math.exp(-a[i][j])));
            }
        }
        return z;
    }

    /**
     * Element wise division
     *
     * @param a scaler
     * @param x matrix
     * @return x / a
     */
    public Double[][] divide(Double[][] x, int a) {
        int m = x.length;
        int n = x[0].length;

        Double[][] z = new Double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                z[i][j] = (x[i][j] / a);
            }
        }
        return z;
    }
    /**
     * Element wise division
     *
     * @param A matrix
     * @param Y matrix
     * @param batch_size scaler
     * @return loss
     */
    public Double cross_entropy(int batch_size, Double[][] Y, Double[][] A) {
        int m = A.length;
        int n = A[0].length;
        Double[][] z = new Double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                z[i][j] = (Y[i][j] * Math.log(A[i][j])) + ((1 - Y[i][j]) * Math.log(1 - A[i][j]));
            }
        }

        Double sum = new Double(0.0);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += z[i][j];
            }
        }
        return -sum / batch_size;
    }
    public Double[][] softMax(Double[][] z) {
        Double[][] zout = new Double[z.length][z[0].length];
        Double sum = 0.;
        for (int i = 0; i < z.length; i++) {
            for (int j = 0; j < z[0].length; j++) {
                sum += Math.exp(z[i][j]);
            }
        }
        for (int i = 0; i < z.length; i++) {
            for (int j = 0; j < z[0].length; j++) {
                zout[i][j] = Math.exp(z[i][j]) / sum;
            }
        }
        return zout;
    }
    
    public Double[][] zeroMatrix(int m, int n) {
        Double[][] a = new Double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = 0.0;
            }
        }
        return a;
    }
    
    public Double[][][] zeroMatrix(Integer w, Integer h, Integer d) {
        Double[][][] a = new Double[w][h][d];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
            	for (int k = 0; k < d; k++) {
            		a[i][j][k] = 0.0;
            	}
            }
        }
        return a;
    }
   
	
    public Double[][][] random(Integer w, Integer h, Integer d) {
        Double[][][] a = new Double[w][h][d];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
            	for (int k = 0; k < d; k++) {
            		a[i][j][k] = nextDouble(0.0, 1.0);
            	}
            }
        }
        return a;
    }
    
    public String shape(Double[][][] a) {
        int w = a.length;
        int l = a[0].length;
        int d = a[0][0].length;
        return  "(" + w + "," + l  + "," + d +")";
    }
    
    public static void printMatrix(Double[][][] matrix) {
        for (int d = 0; d < matrix[0][0].length; d++) {
        	System.out.println("Dimension --> " + d);
            for (int h = 0; h < matrix[0].length; h++) {
            	System.out.print("[");
            	for (int w = 0; w < matrix.length; w++) {
            		System.out.printf("%.0f", matrix[w][h][d]);
            		if( w != (matrix.length-1)) {
            			System.out.print(" , ");	
            		}
            	}
            	System.out.println("]");
            }
            System.out.println("");
        }
    }
   
}