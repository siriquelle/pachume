/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.verification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Siriquelle
 */
public class VerifyEmail {
    //<editor-fold defaultstate="collapsed" desc="verifyEmail">
    public static boolean run(String email) {

        boolean isValidEmail = false;

        if (email.contains("@") && email.lastIndexOf(".") > email.indexOf("@"))
          {
            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
            Matcher m = p.matcher(email);
            boolean matchFound = m.matches();
            if (matchFound)
              {
                isValidEmail = true;
              }
          }

        return isValidEmail;
    }// </editor-fold>
}
