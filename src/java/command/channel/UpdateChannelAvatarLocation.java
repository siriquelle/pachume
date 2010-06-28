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
public class UpdateChannelAvatarLocation {
    //<editor-fold defaultstate="collapsed" desc="updateChannelAvatarLocation">
    public static boolean run(int channelId, String avatarLocation, String contactImageLocation) {

        boolean isAvatarLocationUpdated = false;

        try
          {

            DataBaseConnection updateChannelAvatarLocation_db = new DataBaseConnection();
            updateChannelAvatarLocation_db.connect();

            String updateChannelAvatarLocation_sql = "UPDATE channel SET avatarLocation = '" + avatarLocation + "', contactImageLocation = '" + contactImageLocation + "' WHERE channelId = " + channelId;
            updateChannelAvatarLocation_db.execUpdate(updateChannelAvatarLocation_sql);
            updateChannelAvatarLocation_db.close();

            isAvatarLocationUpdated = true;
          } catch (Exception e)
          {
            isAvatarLocationUpdated = false;
          }

        return isAvatarLocationUpdated;
    }// </editor-fold>
}
