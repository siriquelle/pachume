/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.user.settings;

import command.verification.VerifyEmail;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class AlterEmail {
    //<editor-fold defaultstate="collapsed" desc="updateEmail">

    public static boolean run(int userId, String email) {

        boolean isEmailUpdated = false;

        try
          {
            if (VerifyEmail.run(email))
              {
                DataBaseConnection updateLocation_db = new DataBaseConnection();
                updateLocation_db.connect();

                String updateLocation_sql = "UPDATE user SET email = '" + email + "' WHERE userId = " + userId;

                updateLocation_db.execUpdate(updateLocation_sql);
                updateLocation_db.close();

                isEmailUpdated = true;
              }
          } catch (Exception e)
          {
            isEmailUpdated = false;
          }
        return isEmailUpdated;
    }// </editor-fold>
}
