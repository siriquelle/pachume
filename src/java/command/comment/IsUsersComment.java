/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.comment;

import model.DataBaseConnection;
import java.sql.ResultSet;

import model.User;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Siriquelle
 */
public class IsUsersComment {
    //<editor-fold defaultstate="collapsed" desc="isUsersComment">

    public static boolean run(int commentId, HttpSession session) {

        boolean isUsersComment = false;

        try
          {
            User thisUser = (User) session.getAttribute("thisUser");
            int thisUser_userId = thisUser.getUserId();

            int comment_userId = -1;


            DataBaseConnection isUsersComment_db = new DataBaseConnection();
            isUsersComment_db.connect();
            String isUsersComment_sql = "SELECT userId FROM comment WHERE commentId = " + commentId + ";";
            ResultSet isUsersComment_rs = isUsersComment_db.execSQL(isUsersComment_sql);

            if (isUsersComment_rs.next())
              {
                comment_userId = isUsersComment_rs.getInt("userId");
              }
            if (thisUser_userId == comment_userId)
              {
                isUsersComment = true;
              }

            isUsersComment_db.close();
          } catch (Exception e)
          {
            isUsersComment = false;
          }

        return isUsersComment;
    }// </editor-fold>
}
