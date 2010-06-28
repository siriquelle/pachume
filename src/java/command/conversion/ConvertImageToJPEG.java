/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.conversion;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Siriquelle
 */
public class ConvertImageToJPEG {
    //<editor-fold defaultstate="collapsed" desc="convertImageToJPEG">

    public static ByteArrayOutputStream run(Image image)
            throws Exception {

        // Make sure the aspect ratio is maintained, so the image is not skewed
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);

        // Draw the scaled image
        BufferedImage newJPEGImage = new BufferedImage(imageWidth,
                imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = newJPEGImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, imageWidth, imageHeight, null);

        // Write the scaled image to the outputstream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(newJPEGImage);
        int quality = 85; // Use between 1 and 100, with 100 being highest quality
        quality = Math.max(0, Math.min(quality, 100));
        param.setQuality((float) quality / 100.0f, false);
        encoder.setJPEGEncodeParam(param);
        encoder.encode(newJPEGImage);
        ImageIO.write(newJPEGImage, "IMAGE_JPG", out);

        return out;
    }// </editor-fold> 
}
