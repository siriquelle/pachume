/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.verification;

/**
 *
 * @author Siriquelle
 */
public class VerifyPassword {
    //<editor-fold defaultstate="collapsed" desc="verifyPassword">
    public static boolean run(String userPassword, String verifyPassword) {

        boolean validPassword = false;

        if (userPassword.equals(verifyPassword))
          {
            validPassword = true;
          }

        return validPassword;
    }// </editor-fold>
}
