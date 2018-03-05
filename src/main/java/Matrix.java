public class Matrix {

    private final int length;
    private final int height;
    private double[][] elements;

    public Matrix(int l, int h) {
        length = l;
        height = h;
        elements = new double[l][h];
    }

    public Matrix(double e[][]) {
        elements = e;
        length = e.length;
        height = e[0].length;
    }

    public void setElement(int l, int h, double value) {
        if(l < length && h < height)
            elements[l][h] = value;
    }

    public double getElement(int l, int h) {
        if(l < length && h < height)
            return elements[l][h];
        return 0;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public Matrix multiply(Matrix m2) {
        Matrix m1 = this;
        if(m1.getLength() != m2.getHeight()) {
            return null;
        }
        Matrix result = new Matrix(m1.getHeight(), m2.getLength());
        double dotProduct = 0;
        for(int i = 0; i < m1.getLength(); i++) { //choosing rows of m1
            for(int k = 0; k < m2.getHeight(); k++) { //choosing columns of m2
                for(int j = 0; j < m1.getHeight(); j++) { // choosing individual numbers
                    dotProduct = dotProduct + (m1.getElement(i, j) * m2.getElement(j, k));
                }
                result.setElement(i, k, dotProduct);
                dotProduct = 0;
            }
        }
        return result;
    }

    public Matrix transpose() {
        Matrix m = new Matrix(height, length);
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < height; j++) {
                m.setElement(j, i, this.getElement(i, j));
            }
        }
        return m;
    }

    public Matrix applySigmoid() {
        Matrix result = new Matrix(length, height);
        for (int i = 0; i < length; i++) {
            for(int j = 0; j < height; j++) {
                double sig = 1 / (1 + (Math.pow(Math.E, this.getElement(i, j))));
                result.setElement(i, j, sig);
            }
        }
        return result;
    }

    public String toString() {
        String s = "[\n";
        for(int i = 0; i < length; i++) {
            s += "[";
            for(int j = 0; j < height; j++) {
                s += this.getElement(i, j) + ",";
            }
            s += "]\n";
        }
        s += "]";
        return s;
    }
}
