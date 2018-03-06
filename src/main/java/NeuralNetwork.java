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

    public void train(Matrix input, Matrix targetOutput) {
        Matrix output = this.query(input);
        Matrix error = targetOutput.minus(output);

        hiddenNodes = inHiddenWeights.multiply(input);
        hiddenNodes = hiddenNodes.applySigmoid();

        //back-propagation
        Matrix outError = hiddenOutWeights.transpose().multiply(error);
        Matrix updatedWeights = outError.multiply(learningRate);
        updatedWeights = updatedWeights.elementsMultiply(output.applySigmoid());
        updatedWeights = updatedWeights.elementsMultiply(output.applySigmoid().oneMinusMatrix());
        hiddenOutWeights = updatedWeights.multiply(hiddenNodes.transpose());
        System.out.println("updated weights: " + hiddenOutWeights);
    }

    public Matrix query(Matrix input) {
        System.out.println("input nodes: " + input);
        hiddenNodes = inHiddenWeights.multiply(input);
        hiddenNodes = hiddenNodes.applySigmoid();
        outputNodes = hiddenOutWeights.multiply(hiddenNodes);
        outputNodes = outputNodes.applySigmoid();
        System.out.println("output nodes: " + outputNodes);
        return outputNodes;
    }

    public boolean compareError(Matrix input, Matrix targetOutput, double targetThreshold) {
        Matrix output = this.query(input);
        Matrix error = targetOutput.minus(output);
        for(int i = 0; i < error.getLength(); i++) {
            for(int j = 0; j < error.getHeight(); j++) {
                if(targetOutput.getElement(i, j) - error.getElement(i, j) > targetThreshold) {
                    return false;
                }
            }
        }
        System.out.println("final errors: " + error);
        return true;
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
