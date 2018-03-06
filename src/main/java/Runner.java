import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);
        NeuralNetwork n = new NeuralNetwork(5, 5, 5, 0.01);
        double[][] in = {{0.1}, {0.56}, {0.4}, {0.6}, {0.1}};
        double[][] trainingOutput = {{0.1}, {0.2}, {0.2}, {0.9}, {0.9}};
        Matrix input = new Matrix(in);
        Matrix targetOutput = new Matrix(trainingOutput);
        do {
            n.train(input, targetOutput);
            String s = i.nextLine();
        } while(!n.compareError(input, targetOutput, 0.01));
    }
}
