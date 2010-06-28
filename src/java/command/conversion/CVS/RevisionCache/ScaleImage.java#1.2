/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.conversion;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import org.apache.xmlgraphics.image.codec.png.PNGEncodeParam;
import org.apache.xmlgraphics.image.codec.png.PNGImageEncoder;

/**
 *
 * @author Siriquelle
 */
public class ScaleImage {
    //<editor-fold defaultstate="collapsed" desc="scaleImage">

    public static ByteArrayOutputStream run(Image image, int required_width, int required_height)
            throws Exception {

        int scaledWidth = required_width;
        int scaledHeight = required_height;

        int diff = 0;
        int yDiff = 0;
        int xDiff = 0;

        // Make sure the aspect ratio is maintained, so the image is not skewed
        double thumbRatio = (double) scaledWidth / (double) scaledHeight;

        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);

        double imageRatio = (double) imageWidth / (double) imageHeight;
        if (thumbRatio < imageRatio)
          {
            scaledHeight = (int) (scaledWidth / imageRatio);
          } else
          {
            scaledWidth = (int) (scaledHeight * imageRatio);
          }

        if (scaledWidth < required_width)
          {
            diff = required_width - scaledWidth;
            yDiff = diff / 2;
          }
        if (scaledHeight < required_height)
          {
            diff = required_height - scaledHeight;
            xDiff = diff / 2;
          }

        scaledHeight += diff;
        scaledWidth += diff;

        // Draw the scaled image
        BufferedImage thumbImage = new BufferedImage(scaledWidth,
                scaledHeight, BufferedImage.TRANSLUCENT);
        Graphics2D graphics2D = thumbImage.createGraphics();

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);

        thumbImage = thumbImage.getSubimage(xDiff, yDiff, required_width, required_width);

        // Write the scaled image to the outputstream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PNGImageEncoder encoder = new PNGImageEncoder(out, PNGEncodeParam.getDefaultEncodeParam(thumbImage));
        encoder.encode(thumbImage);
        ImageIO.write(thumbImage, "IMAGE_PNG", out);

        return out;
    }// </editor-fold>
}
