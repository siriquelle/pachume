/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.channel;

import model.User;
import model.DataBaseConnection;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Siriquelle
 */
public class IsUserJoinedChannel {
    //<editor-fold defaultstate="collapsed" desc="isUserJoinedChannel">
    public static boolean run(int channelId, HttpSession session) {

        boolean isUserJoinedChannel = false;

        try
        {

            User thisUser = (User) session.getAttribute("thisUser");
            int userId = thisUser.getUserId();

            DataBaseConnection isUserJoinedChannel_db = new DataBaseConnection();
            isUserJoinedChannel_db.connect();

            String isUserJoinedChannel_sql = "SELECT userId, channelId FROM channelmember WHERE userId = " + userId + " && channelId = " + channelId + ";";
            ResultSet isUsersFriend_rs = isUserJoinedChannel_db.execSQL(isUserJoinedChannel_sql);

            if (isUsersFriend_rs.next())
            {
                isUserJoinedChannel = true;
            }

            isUserJoinedChannel_db.close();
        } catch (Exception e)
        {
            isUserJoinedChannel = false;
        }
        return isUserJoinedChannel;
    }// </editor-fold>
}
