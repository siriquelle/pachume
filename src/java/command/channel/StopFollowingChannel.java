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
public class StopFollowingChannel {
    //<editor-fold defaultstate="collapsed" desc="leaveChannel">
    public static boolean run(int userId, int channelId) {

        boolean isChannelLeaved = false;

        try
        {
            DataBaseConnection leaveChannel_db;
            leaveChannel_db = new DataBaseConnection();
            leaveChannel_db.connect();

            String leaveChannel_sql = "DELETE FROM channelmember WHERE userId = " + userId + " AND channelId = " + channelId;
            leaveChannel_db.execUpdate(leaveChannel_sql);

            leaveChannel_db.close();

            isChannelLeaved = true;

        } catch (Exception e)
        {
            isChannelLeaved = false;
        }

        return isChannelLeaved;
    }// </editor-fold>
}
