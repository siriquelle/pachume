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
public class GetUsersPachumeCount {
    //<editor-fold defaultstate="collapsed" desc="getUsersPachumeCount">
    public static int run(int userId) {
        int usersPachumeCount = 0;
        try
          {
            String sql = "SELECT COUNT(pachumeId) AS usersPachumeCount FROM pachume WHERE userId = " + userId;
            DataBaseConnection db = new DataBaseConnection();
            db.connect();
            ResultSet rs = db.execSQL(sql);
            while (rs.next())
              {
                usersPachumeCount = rs.getInt("usersPachumeCount");
              }
            db.close();
          } catch (Exception e)
          {
            System.out.print(e);
          }

        return usersPachumeCount;
    }// </editor-fold>
}
