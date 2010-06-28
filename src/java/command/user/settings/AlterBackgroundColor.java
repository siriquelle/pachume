/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.user.settings;

import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class AlterBackgroundColor {
    //<editor-fold defaultstate="collapsed" desc="updateBackgroundColor">

    public static boolean run(int userId, String backgroundColor) {

        boolean isBackgroundColorUpdated = false;

        try
          {

            DataBaseConnection updateBackgroundColor_db = new DataBaseConnection();
            updateBackgroundColor_db.connect();

            String updateBackgroundColor_sql = "UPDATE user SET backgroundColor = '" + backgroundColor + "' WHERE userId = " + userId;

            updateBackgroundColor_db.execUpdate(updateBackgroundColor_sql);
            updateBackgroundColor_db.close();

            isBackgroundColorUpdated = true;

          } catch (Exception e)
          {
            isBackgroundColorUpdated = false;
          }

        return isBackgroundColorUpdated;
    }// </editor-fold>
}
