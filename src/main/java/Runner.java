public class Runner {
    public static void main(String[] args) {
        double[][] d1 = {
                {1, 2, 3},
                {4, 5, 6}
        };

        double[][] d2 = {
                {7, 8,},
                {9, 10},
                {11, 12}
        };

        Matrix m1 = new Matrix(d1);
        Matrix m2 = new Matrix(d2);

        Matrix m3 = m1.multiply(m2);
        System.out.println(m3.toString());
    }
}
