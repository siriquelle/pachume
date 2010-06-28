/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.verification;

/**
 *
 * @author Siriquelle
 */
public class VerifyLastName {
    //<editor-fold defaultstate="collapsed" desc="verifyLastName">
    public static boolean run(String lastName) {

        boolean isValidLastName = false;

        if (lastName.length() > 0)
          {
            isValidLastName = true;
          }

        return isValidLastName;
    }// </editor-fold>
}
