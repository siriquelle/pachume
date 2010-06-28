/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.conversion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Siriquelle
 */
public class ConvertURLs {

    public static String run(String text) {
        String link = "";
        String tinyLink = "";
        String anchor = "";

        int linkStart = 0;
        int linkEnd = 0;

        text = text.concat(" ");

        try
          {
            while (linkStart != -1)
              {
                linkStart = text.indexOf("http://", linkEnd);
                if (linkEnd != -1)
                  {
                    linkEnd = text.indexOf(" ", linkStart);

                    link = text.substring(linkStart, linkEnd);

                    if (link.length() > 30)
                      {

                        URL tinyurl = new URL("http://url.ie/site/api/txt/create/?url=" + link);

                        URLConnection tc = tinyurl.openConnection();

                        BufferedReader in = new BufferedReader(new InputStreamReader(tc.getInputStream()));

                        tinyLink = in.readLine();

                        tinyLink = tinyLink.replace("OK: ", "");

                        in.close();

                        text = text.replace(link, tinyLink);
                      } else
                      {
                        tinyLink = link;
                      }
                    anchor = "<p class=\"viewport_valid_element_container\"><a href=\"" + tinyLink + "\" class=\"outlink\" >" + tinyLink.replace("http://", "") + "</a></p>";
                    text = text.replace(tinyLink, anchor);
                    linkEnd = text.indexOf("</p>", linkEnd);
                  } else
                  {
                    linkStart = linkEnd;
                  }
              }
          } catch (Exception e)
          {
            tinyLink = e.toString();
          }
        return text;
    }
}
