/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.conversion;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.apache.xmlgraphics.image.codec.png.PNGEncodeParam;
import org.apache.xmlgraphics.image.codec.png.PNGImageEncoder;

/**
 *
 * @author Siriquelle
 */
public class ConvertImageToPNG {
    //<editor-fold defaultstate="collapsed" desc="convertImageToPNG">

    public static InputStream run(InputStream p_image)
            throws Exception {

        InputStream imageStream = new BufferedInputStream(p_image);
        Image image = (Image) ImageIO.read(imageStream);

        // Make sure the aspect ratio is maintained, so the image is not skewed
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);


        // Draw the scaled image
        BufferedImage newPNGImage = new BufferedImage(imageWidth,
                imageHeight, BufferedImage.TRANSLUCENT);
        Graphics2D graphics2D = newPNGImage.createGraphics();

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.addRenderingHints(rh);

        RenderingHints rh2 = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.addRenderingHints(rh2);

        RenderingHints rh3 = new RenderingHints(
                RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics2D.addRenderingHints(rh3);
        graphics2D.drawImage(image, 0, 0, imageWidth, imageHeight, null);

        // Write the scaled image to the outputstream
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PNGImageEncoder encoder = new PNGImageEncoder(out, PNGEncodeParam.getDefaultEncodeParam(newPNGImage));

        encoder.encode(newPNGImage);
        ImageIO.write(newPNGImage, "IMAGE_PNG", out);

        ByteArrayInputStream bais = new ByteArrayInputStream(out.toByteArray());
        InputStream PNGImage = bais;

        return PNGImage;
    }// </editor-fold>
}
