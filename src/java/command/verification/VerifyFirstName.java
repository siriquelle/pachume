/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.verification;

/**
 *
 * @author Siriquelle
 */
public class VerifyFirstName {
    //<editor-fold defaultstate="collapsed" desc="verifyFirstName">
    public static boolean run(String firstName) {

        boolean isValidFirstName = false;

        if (firstName.length() > 0)
          {
            isValidFirstName = true;
          }

        return isValidFirstName;
    }// </editor-fold>
}
