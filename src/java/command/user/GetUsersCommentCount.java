/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.user;

import java.sql.ResultSet;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class GetUsersCommentCount {
    //<editor-fold defaultstate="collapsed" desc="getUsersCommentCount">
    public static int run(int userId) {
        int usersCommentCount = 0;
        try
          {
            String sql = "SELECT COUNT(commentId) as usersCommentCount FROM comment WHERE userId = " + userId;
            DataBaseConnection db = new DataBaseConnection();
            db.connect();
            ResultSet rs = db.execSQL(sql);
            while (rs.next())
              {
                usersCommentCount = rs.getInt("usersCommentCount");
              }
            db.close();
          } catch (Exception e)
          {
            System.out.print(e);
          }

        return usersCommentCount;
    }// </editor-fold>
}
