import boofcv.alg.filter.binary.GThresholdImageOps;
import boofcv.alg.filter.binary.ThresholdImageOps;
import boofcv.gui.binary.VisualizeBinaryData;
import boofcv.io.UtilIO;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.GrayF32;
import boofcv.struct.image.GrayU8;

import java.awt.image.BufferedImage;

public class ImageToBinaryImgConverter {

    public boolean convertImage(String path, String filename, String img) {
        try {
            BufferedImage image = UtilImageIO.loadImage(UtilIO.pathExample(path + img));

            GrayF32 input = ConvertBufferedImage.convertFromSingle(image, null, GrayF32.class);
            GrayU8 binary = new GrayU8(input.width, input.height);

            double threshold = GThresholdImageOps.computeOtsu(input, 0, 255);
            ThresholdImageOps.threshold(input,binary,(float)threshold,true);

            BufferedImage visualBinary = VisualizeBinaryData.renderBinary(binary, false, null);

            UtilImageIO.saveImage(visualBinary, path + "\\binaryimages\\" + filename + ".jpg");
        } catch(NullPointerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    };
}
