/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.channel;

import model.DataBaseConnection;
import java.sql.ResultSet;
import java.util.Vector;
/**
 *
 * @author Siriquelle
 */
public class GetUsersChannels {
    //<editor-fold defaultstate="collapsed" desc="getUsersChannels">
    public static Vector run(int userId) {

        Vector usersChannels = new Vector();

        try
        {
            String getUsersChannels_sql = "SELECT channelId FROM channelmember WHERE userId = " + userId + ";";
            DataBaseConnection getUsersChannels_db = new DataBaseConnection();
            getUsersChannels_db.connect();
            ResultSet getUsersChannels_rs = getUsersChannels_db.execSQL(getUsersChannels_sql);

            while (getUsersChannels_rs.next())
            {
                usersChannels.add(getUsersChannels_rs.getString("channelId"));
            }
            getUsersChannels_db.close();
        } catch (Exception e)
        {
            usersChannels.add(e.toString());
        }

        return usersChannels;
    }
    // </editor-fold>
}
