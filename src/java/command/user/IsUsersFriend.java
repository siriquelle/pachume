/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.user;

import java.sql.ResultSet;
import javax.servlet.http.HttpSession;
import model.DataBaseConnection;
import model.User;

/**
 *
 * @author Siriquelle
 */
public class IsUsersFriend {
    //<editor-fold defaultstate="collapsed" desc="isUsersFriend">

    public static boolean run(int friendId, HttpSession session) {

        boolean isUsersFriend = false;
        try
          {

            User thisUser = (User) session.getAttribute("thisUser");
            int thisUser_userId = thisUser.getUserId();
            DataBaseConnection db = new DataBaseConnection();
            db.connect();
            String sql = "SELECT userId, friendId FROM contact WHERE userId = " + thisUser_userId + " && friendId = " + friendId + ";";
            ResultSet rs = db.execSQL(sql);

            if (rs.next())
              {
                isUsersFriend = true;
              }
            db.close();
          } catch (Exception e)
          {
            isUsersFriend = false;
          }
        return isUsersFriend;
    }// </editor-fold>
}
