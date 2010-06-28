/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.conversion;

import command.notification.SendEmail;
import javax.servlet.http.HttpServletRequest;
import template.Email;
import template.Email_ErrorNotification;

/**
 *
 * @author Siriquelle
 */
public class GetGoTo {
    //<editor-fold defaultstate="collapsed" desc="getGoTo">

    public static String run(HttpServletRequest request) {
        String goTo = null;
        if (request.getHeader("Referer") == null)
          {
            goTo = "http://pachume.com/?";
          } else if (!request.getHeader("Referer").contains("http://pachume.com"))
          {
            goTo = "http://pachume.com/?";
          } //else if (!request.getHeader("Referer").contains("localhost"))
        //{
        //  goTo = "http://pachume.com/?";
        //  }
        else
          {
            goTo = request.getHeader("Referer");
            try
              {
                goTo = goTo.replaceAll(goTo.substring(goTo.indexOf("message"), goTo.length()), "");
              } catch (Exception e)
              {
                Email email = new Email_ErrorNotification(e.toString(), 220);
                SendEmail.run("pachume@pachume.com", email.getSubject(), email.getBody());
              }

            if (goTo.contains("&"))
              {
                if (goTo.charAt(goTo.length() - 1) == '&')
                  {
                    //do nothing
                  } else
                  {
                    if (goTo.charAt(goTo.length() - 1) == '?')
                      {
                        goTo.replace(goTo.charAt(goTo.length() - 1), '&');
                      } else
                      {
                        goTo += "&";
                      }
                  }
              } else if (goTo.contains("?"))
              {
                if (goTo.charAt(goTo.length() - 1) == '?')
                  {
                    //do nothing
                  } else
                  {
                    goTo += "&";
                  }
              } else
              {
                goTo += "?";
              }
          }
        return goTo;
    }// </editor-fold>
}
