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
public class AlterBackgroundImageLocation {
    //<editor-fold defaultstate="collapsed" desc="updateBackgroundImageLocation">

    public static boolean run(int userId, String backgroundImageLocation) {

        boolean isBackgroundImageLocationUpdated = false;

        try
          {

            DataBaseConnection updateBackgroundImageLocation_db = new DataBaseConnection();
            updateBackgroundImageLocation_db.connect();

            String updateBackgroundImageLocation_sql = "UPDATE user SET backgroundImageLocation = '" + backgroundImageLocation + "' WHERE userId = " + userId;

            updateBackgroundImageLocation_db.execUpdate(updateBackgroundImageLocation_sql);
            updateBackgroundImageLocation_db.close();

            isBackgroundImageLocationUpdated = true;

          } catch (Exception e)
          {
            isBackgroundImageLocationUpdated = false;
          }


        return isBackgroundImageLocationUpdated;
    }// </editor-fold>
}
