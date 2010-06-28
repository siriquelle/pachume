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
public class IsUserChannelsAuthor {
    //<editor-fold defaultstate="collapsed" desc="isUserChannelsAuthor">
    public static boolean run(int channelId, HttpSession session) {

        boolean isUserChannelsAuthor = false;

        try
        {

            User thisUser = (User) session.getAttribute("thisUser");
            int userId = thisUser.getUserId();

            DataBaseConnection isUserChannelsAuthor_db = new DataBaseConnection();
            isUserChannelsAuthor_db.connect();

            String isUserChannelsAuthor_sql = "SELECT userId, channelId FROM channel WHERE userId = " + userId + " AND channelId = " + channelId + ";";
            ResultSet isUserChannelsAuthor_rs = isUserChannelsAuthor_db.execSQL(isUserChannelsAuthor_sql);

            if (isUserChannelsAuthor_rs.next())
            {
                isUserChannelsAuthor = true;
            }

            isUserChannelsAuthor_db.close();
        } catch (Exception e)
        {
            isUserChannelsAuthor = false;
        }
        return isUserChannelsAuthor;
    }// </editor-fold>
}
