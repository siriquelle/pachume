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
public class UpdateBackgroundColor {
    //<editor-fold defaultstate="collapsed" desc="updateChannelBackgroundColor">
    public static boolean run(String channelName, String backgroundColor) {

        boolean isBackgroundColorUpdated = false;

        try
        {

            DataBaseConnection updateBackgroundColor_db = new DataBaseConnection();
            updateBackgroundColor_db.connect();

            String updateBackgroundColor_sql = "UPDATE channel SET backgroundColor = '" + backgroundColor + "' WHERE channelName = '" + channelName + "';";

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
