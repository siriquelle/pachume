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
public class IsUsersPost {
    //<editor-fold defaultstate="collapsed" desc="isUsersPost">
    public static boolean run(int pachumeId, HttpSession session) {

        boolean isUsersPost = false;
        try
          {
            User thisUser = (User) session.getAttribute("thisUser");
            int thisUser_userId = thisUser.getUserId();

            int pachume_userId = -1;


            DataBaseConnection db = new DataBaseConnection();
            db.connect();
            String sql = "SELECT userId FROM pachume WHERE pachumeId = " + pachumeId + ";";
            ResultSet rs = db.execSQL(sql);

            if (rs.next())
              {
                pachume_userId = rs.getInt("userId");
              }
            if (thisUser_userId == pachume_userId)
              {
                isUsersPost = true;
              }

            db.close();
          } catch (Exception e)
          {
            isUsersPost = false;
          }

        return isUsersPost;
    }// </editor-fold>
}
