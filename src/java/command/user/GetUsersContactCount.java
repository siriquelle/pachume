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
public class GetUsersContactCount {
    //<editor-fold defaultstate="collapsed" desc="getUsersContactCount">

    public static int run(int userId) {
        int usersContactCount = 0;
        try
          {
            String sql = "SELECT COUNT(userId) as usersContactCount FROM contact WHERE userId = " + userId;
            DataBaseConnection db = new DataBaseConnection();
            db.connect();
            ResultSet rs = db.execSQL(sql);
            while (rs.next())
              {
                usersContactCount = rs.getInt("usersContactCount");
              }
            db.close();
          } catch (Exception e)
          {
            System.out.print(e);
          }

        return usersContactCount;
    }// </editor-fold>
}
