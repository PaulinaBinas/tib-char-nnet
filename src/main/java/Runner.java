import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
//        NeuralNetwork n = new NeuralNetwork(5, 5, 5, 0.001);
//        double[][] in = {{0.1}, {0.56}, {0.4}, {0.6}, {0.1}};
//        double[][] trainingOutput = {{0.1}, {0.2}, {0.2}, {0.9}, {0.9}};
//        Matrix input = new Matrix(in);
//        Matrix targetOutput = new Matrix(trainingOutput);
//        do {
//            n.train(input, targetOutput);
//        } while(!n.compareError(input, targetOutput, 0.005));
//        System.out.println("final output: " + n.query(input));
        String path = "D:\\my programs\\Java programs\\TibetanCharactersNeuralNetwork\\tib-char-nnet\\src\\trainingData\\";
        makeBinaryImages(path);

        try {

            File file = new File(path + "binaryImages\\1.jpg");
            BufferedImage originalImage = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);
            byte[] imageInByte = baos.toByteArray();
            StringBuilder check = new StringBuilder();
            for(int g = 0; g < imageInByte.length; g++)
            {
                check.append(Integer.toBinaryString(imageInByte[g]));
            }

            String array[] = check.toString().split("");
            String finalTest[] = array;

            NeuralNetwork n = new NeuralNetwork(array.length, 4, 4, 0.05);

            for(int j = 1; j < 5; j++) {
                file = new File(path + "binaryImages\\" + j + ".jpg");
                originalImage = ImageIO.read(file);
                baos = new ByteArrayOutputStream();
                ImageIO.write(originalImage, "jpg", baos);
                imageInByte = baos.toByteArray();
                check = new StringBuilder();
                for(int g = 0; g < imageInByte.length; g++)
                {
                    check.append(Integer.toBinaryString(imageInByte[g]));
                }

                String temp[] = check.toString().split("");
                if(temp.length > array.length) {
                    array = Arrays.copyOfRange(temp, 0, array.length);
                }
                double[][] in = new double[array.length][1];
                for(int i = 0; i < array.length; i++) {
                    in[i][0] = Integer.parseInt(array[i]);
                }
                double[][] trainingOutput = {{0.01}, {0.01}, {0.01}, {0.01}};
                trainingOutput[j - 1][0] = 0.99;
                Matrix input = new Matrix(in);
                Matrix targetOutput = new Matrix(trainingOutput);
                do {
                    n.train(input, targetOutput);
                } while(!n.compareError(input, targetOutput, 0.1));
                System.out.println("final output: " + n.query(input));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void makeBinaryImages(String path) {
        ImageToBinaryImgConverter converter = new ImageToBinaryImgConverter();
        String imagename = "ka.jpg";
        converter.convertImage(path, "1", imagename);
        imagename = "kha.jpg";
        converter.convertImage(path, "2", imagename);
        imagename = "ga.jpg";
        converter.convertImage(path, "3", imagename);
        imagename = "nga.jpg";
        converter.convertImage(path, "4", imagename);
    }

}
