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
public class AlterAvatarLocation {
    //<editor-fold defaultstate="collapsed" desc="updateAvatarLocation">

    public static boolean run(int userId, String avatarLocation, String pachumeImageLocation, String contactImageLocation) {

        boolean isLocationUpdated = false;

        try
          {

            DataBaseConnection updateAvatarLocation_db = new DataBaseConnection();
            updateAvatarLocation_db.connect();

            String updateAvatarLocation_sql = "UPDATE user SET avatarLocation = '" + avatarLocation + "', pachumeImageLocation = '" + pachumeImageLocation + "', contactImageLocation = '" + contactImageLocation + "' WHERE userId = " + userId;
            updateAvatarLocation_db.execUpdate(updateAvatarLocation_sql);

            updateAvatarLocation_db.close();

            isLocationUpdated = true;
          } catch (Exception e)
          {
            isLocationUpdated = false;
          }

        return isLocationUpdated;
    }// </editor-fold>
}
