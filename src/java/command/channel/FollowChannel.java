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
public class FollowChannel {
    //<editor-fold defaultstate="collapsed" desc="joinChannel">
    public static boolean run(int userId, int channelId) {

        boolean isChanelJoined = true;

        try
        {

            String channelName = GetChannelName.run(channelId);

            String joinChannel_sql = "INSERT INTO channelmember(userId, channelId, channelName)" +
                    " VALUES(" + userId + ", " + channelId + ", '" + channelName + "');";

            DataBaseConnection joinChannel_db = new DataBaseConnection();
            joinChannel_db.connect();
            joinChannel_db.execUpdate(joinChannel_sql);
            joinChannel_db.close();

        } catch (Exception e)
        {
            isChanelJoined = false;
        }

        return isChanelJoined;

    }
// </editor-fold>
}
