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
public class GetChannelName {
//<editor-fold defaultstate="collapsed" desc="getChannelName">
    public static String run(int channelId) {
        String channelName = "";

        try
        {
            String getChannelName_sql = "SELECT channelName FROM channel WHERE channelId = " + channelId + ";";
            DataBaseConnection getChannelName_db = new DataBaseConnection();
            getChannelName_db.connect();
            ResultSet getChannelName_rs = getChannelName_db.execSQL(getChannelName_sql);

            if (getChannelName_rs.next())
            {
                channelName = getChannelName_rs.getString("channelName");
            }
            getChannelName_db.close();
        } catch (Exception e)
        {
            channelName = e.toString();
        }

        return channelName;
    }
// </editor-fold>
}
