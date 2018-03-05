public class NeuralNetwork {

    private Matrix inputNodes;
    private Matrix hiddenNodes;
    private Matrix outputNodes;
    private Matrix inHiddenWeights;
    private Matrix hiddenOutWeights;
    private double learningRate;

    public NeuralNetwork(int i, int h, int o, double l) {
        inputNodes = new Matrix(i, 1);
        hiddenNodes = new Matrix(h, 1);
        outputNodes = new Matrix(o, 1);
        learningRate = l;
        inHiddenWeights = new Matrix(h, i);
        hiddenOutWeights = new Matrix(o, h);
        initRandomWeights();
    }

    public void train(Matrix input, Matrix trainingOutput) {

    }

    public Matrix query(Matrix input) {
        System.out.println("input nodes: " + input);
        inputNodes = input.transpose();
        hiddenNodes = inHiddenWeights.multiply(inputNodes);
        hiddenNodes = hiddenNodes.applySigmoid();
        System.out.println("hidden nodes: " + hiddenNodes);
        outputNodes = hiddenOutWeights.multiply(hiddenNodes.transpose());
        outputNodes = outputNodes.applySigmoid();
        System.out.println("output nodes: " + outputNodes);
        return outputNodes;
    }

    private void initRandomWeights() {
        double rand;
        for(int i = 0; i < inHiddenWeights.getLength(); i++) {
            for(int j = 0; j < inHiddenWeights.getHeight(); j++) {
                rand  = Math.random();
                inHiddenWeights.setElement(i, j, rand);
            }
        }
        for(int i = 0; i < hiddenOutWeights.getLength(); i++) {
            for(int j = 0; j < hiddenOutWeights.getHeight(); j++) {
                rand  = Math.random();
                hiddenOutWeights.setElement(i, j, rand);
            }
        }
    }
}
