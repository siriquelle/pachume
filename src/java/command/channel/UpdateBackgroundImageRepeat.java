/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.channel;

import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class UpdateBackgroundImageRepeat {
    //<editor-fold defaultstate="collapsed" desc="updateBackgroundImageRepeat">
    public static boolean run(String channelName, String backgroundImageRepeat) {

        boolean isBackgroundColorUpdated = false;

        try
          {

            DataBaseConnection updateBackgroundColor_db = new DataBaseConnection();
            updateBackgroundColor_db.connect();

            String updateBackgroundColor_sql = "UPDATE channel SET backgroundImageRepeat = '" + backgroundImageRepeat + "' WHERE channelName = '" + channelName + "';";

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
