/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.conversion;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Siriquelle
 */
public class MakeCompatable {
    //<editor-fold defaultstate="collapsed" desc="makeCompatable">

    public static String run(String text) {

        text = text.replaceAll("&", "&amp;");
        text = text.replaceAll("'", "&apos;");
        text = text.replaceAll("\"", "&#34;");
        text = text.replaceAll("\\\\", "&#092;");
        text = text.replaceAll("<", "&lt;");
        text = text.replaceAll(">", "&gt;");
        text = text.replaceAll("\\n", "<br />");
        text = text.replaceAll("\\(", "&#040;");
        text = text.replaceAll("\\)", "&#041;");
        try
          {

            text = URLDecoder.decode(text, "UTF-8");
          } catch (UnsupportedEncodingException ex)
          {
            Logger.getLogger(MakeCompatable.class.getName()).log(Level.SEVERE, null, ex);
          }

        text = text.trim();

        if (text.contains("http://"))
          {
            text = ConvertURLs.run(text);
          }

        return text;
    }// </editor-fold>
}
