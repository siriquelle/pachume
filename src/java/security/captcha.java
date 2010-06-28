    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.*;

/**
 *
 * @author Siriquelle
 */
public class captcha extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try
          {
            Random random = new Random();
            Vector keyList = new Vector();
            for (int i = 0; i <= 6; i++)
              {
                keyList.add(random.nextInt(10));
              }

            String lock = "";
            Iterator listIterator = keyList.listIterator();
            while (listIterator.hasNext())
              {
                lock += listIterator.next();
              }

            HttpSession session = request.getSession();
            session.setAttribute("lock", lock);

            lock = "";
            listIterator = keyList.listIterator();
            while (listIterator.hasNext())
              {
                lock += listIterator.next();
                lock += " ";
              }

            int captchaWidth = 144;
            int captchaHeight = 26;

            int fontXPos = 6;
            int fontYPos = 22;

            int lineStartXPos = 0;
            int lineStartYPos = 0;
            int lineEndXPos = 0;
            int lineEndYPos = 0;

            Color X000000 = new Color(0, 0, 0);
            Color XFF0000 = new Color(255, 0, 0);

            BufferedImage captcha = new BufferedImage(captchaWidth, captchaHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = captcha.createGraphics();

            RenderingHints rh = new RenderingHints(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.addRenderingHints(rh);

            g.setColor(XFF0000);
            g.fillRect(0, 0, captchaWidth, captchaHeight);

            g.setColor(X000000);
            Font font = new Font("Luxi Sans", Font.PLAIN, 24);
            g.setFont(font);
            g.drawString(lock, fontXPos, fontYPos);

            g.setColor(X000000);
            lineStartXPos = 0;
            lineEndXPos = 144;
            lineStartYPos = 5;
            lineEndYPos = 5;

            for (int i = 0; i < 5; i++)
              {
                g.drawLine(lineStartXPos, lineStartYPos, lineEndXPos, lineEndYPos);
                lineStartYPos += 4;
                lineEndYPos += 4;
              }

            g.dispose();
            response.setContentType("image/jpeg");
            OutputStream outputStream = response.getOutputStream();
            ImageIO.write(captcha, "jpeg", outputStream);
            outputStream.close();

          } catch (Exception e)
          {
            System.out.println(e);
          }
    }

    /** 
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}