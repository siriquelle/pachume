/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.user.settings;

import command.conversion.MakeCompatable;
import java.io.UnsupportedEncodingException;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class AlterLocation {
    //<editor-fold defaultstate="collapsed" desc="updateLocation">

    public static boolean run(int userId, String location) {

        boolean isLocationUpdated = false;

        location = MakeCompatable.run(location);

        try
          {
            DataBaseConnection updateLocation_db = new DataBaseConnection();
            updateLocation_db.connect();

            String updateLocation_sql = "UPDATE user SET location = '" + location + "' WHERE userId = " + userId;

            updateLocation_db.execUpdate(updateLocation_sql);
            updateLocation_db.close();

            isLocationUpdated = true;

          } catch (Exception e)
          {
            isLocationUpdated = false;
          }
        return isLocationUpdated;
    }// </editor-fold>
}
