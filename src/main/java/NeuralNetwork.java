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
        hiddenNodes = inHiddenWeights.multiply(input);
        hiddenNodes = hiddenNodes.applySigmoid();

        Matrix finalOutputs = hiddenOutWeights.multiply(hiddenNodes);
        finalOutputs = finalOutputs.applySigmoid();

        Matrix outputError = targetOutput.minus(finalOutputs);
        Matrix hiddenError = hiddenOutWeights.transpose().multiply(outputError);

        Matrix temp = outputError.elementsMultiply(finalOutputs);
        temp = temp.elementsMultiply(finalOutputs.oneMinusMatrix());
        temp = temp.multiply(hiddenNodes.transpose());
        temp = temp.multiply(learningRate);
        hiddenOutWeights = hiddenOutWeights.plus(temp);

        temp = hiddenError.elementsMultiply(hiddenNodes);
        temp = temp.elementsMultiply(hiddenNodes.oneMinusMatrix());
        temp = temp.multiply(input.transpose());
        temp = temp.multiply(learningRate);
        inHiddenWeights = inHiddenWeights.plus(temp);
    }

    public Matrix query(Matrix input) {
        hiddenNodes = inHiddenWeights.multiply(input);
        hiddenNodes = hiddenNodes.applySigmoid();
        outputNodes = hiddenOutWeights.multiply(hiddenNodes);
        outputNodes = outputNodes.applySigmoid();
        return outputNodes;
    }

    public boolean compareError(Matrix input, Matrix targetOutput, double targetThreshold) {
        Matrix output = this.query(input);
        Matrix error = targetOutput.minus(output);
        for(int i = 0; i < error.getLength(); i++) {
            for(int j = 0; j < error.getHeight(); j++) {
                double e = error.getElement(i, j);
                if((e > targetThreshold  && e > 0) || (e < ((-1) * targetThreshold) && e < 0)) {
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
