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
public class GetScreenNameFromEmail {
    //<editor-fold defaultstate="collapsed" desc="getScreenNameFromEmail">

    public static String run(String recipientEmail) {
        String screenName = "screenName";
        String sql = "sql";

        try
          {
            DataBaseConnection db = new DataBaseConnection();
            db.connect();

            sql = "SELECT screenName FROM user WHERE email = '" + recipientEmail + "';";
            ResultSet rs = db.execSQL(sql);
            if (rs.next())
              {
                screenName = rs.getString(screenName);
              }

            db.close();
          } catch (Exception e)
          {
            System.out.print(e);
          }

        return screenName;
    }// </editor-fold>
}
