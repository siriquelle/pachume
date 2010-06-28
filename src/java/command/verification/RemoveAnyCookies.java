/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.verification;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Siriquelle
 */
public class RemoveAnyCookies {
    //<editor-fold defaultstate="collapsed" desc="removeAnyCookies">
    public static void run(HttpServletRequest request, HttpServletResponse response) {

        try
          {
            Cookie[] cookieList = request.getCookies();
            for (int i = 0; i < cookieList.length; i++)
              {
                Cookie thisCookie = cookieList[i];

                if (thisCookie.getName().startsWith("pas") || thisCookie.getName().startsWith("usr") || thisCookie.getName().startsWith("JSESSION"))
                  {
                    Cookie cart = new Cookie(thisCookie.getName(), null);
                    cart.setMaxAge(0);
                    response.addCookie(cart);
                  }
              }

          } catch (Exception e)
          {
            System.out.print(e);
          }

    }// </editor-fold>
}
