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
public class GetUserId {
    //<editor-fold defaultstate="collapsed" desc="getUserId">

    public static int run(String screenName) {

        int userId = -1;

        try
          {
            DataBaseConnection userId_db = new DataBaseConnection();
            userId_db.connect();
            String userId_sql = "SELECT userId FROM user WHERE screenName = '" + screenName + "';";
            ResultSet userId_rs = userId_db.execSQL(userId_sql);

            if (userId_rs.next())
              {
                userId = userId_rs.getInt("userId");
              }
            userId_db.close();
          } catch (Exception e)
          {
            System.out.print(e.toString());
          }

        return userId;
    }// </editor-fold>
}
