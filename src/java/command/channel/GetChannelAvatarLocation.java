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
public class GetChannelAvatarLocation {
//<editor-fold defaultstate="collapsed" desc="getChannelAvatarLocation">
    public static String run(int channelId) {

        String avatarLocation = "avatarLocation";

        try
        {
            String getChannelAvatarLocation_sql = "SELECT avatarLocation FROM channel WHERE channelId = " + channelId + ";";
            DataBaseConnection getChannelAvatarLocation_db = new DataBaseConnection();
            getChannelAvatarLocation_db.connect();
            ResultSet getChannelAvatarLocation_rs = getChannelAvatarLocation_db.execSQL(getChannelAvatarLocation_sql);

            if (getChannelAvatarLocation_rs.next())
            {
                avatarLocation = getChannelAvatarLocation_rs.getString(avatarLocation);
            }
            getChannelAvatarLocation_db.close();
        } catch (Exception e)
        {
            avatarLocation = e.toString();
        }

        return avatarLocation;
    }
// </editor-fold>
}
