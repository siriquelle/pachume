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
public class UpdateBackgroundImageLocation {
    //<editor-fold defaultstate="collapsed" desc="updateChannelBackgroundImageLocation">
    public static boolean run(int channelId, String backgroundImageLocation) {

        boolean isBackgroundImageLocationUpdated = false;

        try
        {

            DataBaseConnection updateBackgroundImageLocation_db = new DataBaseConnection();
            updateBackgroundImageLocation_db.connect();

            String updateBackgroundImageLocation_sql = "UPDATE channel SET backgroundImageLocation = '" + backgroundImageLocation + "' WHERE channelId = " + channelId;

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
