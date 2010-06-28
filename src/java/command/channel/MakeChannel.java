/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.channel;

import model.DataBaseConnection;
import java.sql.ResultSet;

/**
 *
 * @author Siriquelle
 */
public class MakeChannel {
    //<editor-fold defaultstate="collapsed" desc="createChannel">
    public static int run(int userId, String channelName, String channelDescription, String avatarLocation, String contactImageLocation, String backgroundImageLocation, String backgroundImageRepeat, String backgroundColor) {

        int channelId = -1;

        try
        {
            String createChannel_sql = "INSERT INTO channel(userId, channelName, channelDescription, avatarLocation, contactImageLocation, backgroundImageLocation, backgroundImageRepeat,backgroundColor)" +
                    " VALUES(" + userId + ", '" + channelName + "', '" + channelDescription + "', '" + avatarLocation + "', '" + contactImageLocation + "', '" + backgroundImageLocation + "', '" + backgroundImageRepeat + "', '" + backgroundColor + "');";

            DataBaseConnection createChannel_db = new DataBaseConnection();
            createChannel_db.connect();
            createChannel_db.execUpdate(createChannel_sql);

            String channelId_sql = "SELECT channelId FROM channel where channelName = '" + channelName + "';";
            ResultSet channelId_rs = createChannel_db.execSQL(channelId_sql);

            if (channelId_rs.next())
            {
                channelId = channelId_rs.getInt("channelId");
            }

            createChannel_db.close();
        } catch (Exception e)
        {
            channelId = -1;
        }

        return channelId;

    }
// </editor-fold>
}
