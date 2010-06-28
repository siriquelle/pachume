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
public class GetEmailFromUserId {
    //<editor-fold defaultstate="collapsed" desc="GetEmailFromUserId">

    public static String run(int userId) {
        String email = "email";
        String sql = "sql";

        try
          {
            DataBaseConnection db = new DataBaseConnection();
            db.connect();

            sql = "SELECT email FROM user WHERE userId = " + userId + ";";
            ResultSet rs = db.execSQL(sql);
            if (rs.next())
              {
                email = rs.getString(email);
              }

            db.close();
          } catch (Exception e)
          {
            System.out.print(e);
          }

        return email;
    }// </editor-fold>
}
