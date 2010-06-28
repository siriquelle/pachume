/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.verification;

import java.sql.ResultSet;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class IsScreenNameAvailable {
    //<editor-fold defaultstate="collapsed" desc="isScreenNameAvailable">

    public static boolean run(String screenName) {

        boolean isScreenNameAvailable = false;

        try
          {
            DataBaseConnection db = new DataBaseConnection();
            db.connect();
            String sql = "SELECT screenName FROM user WHERE screenName = '" + screenName + "';";
            ResultSet rs = db.execSQL(sql);

            if (rs.next())
              {
                isScreenNameAvailable = false;
              } else
              {
                isScreenNameAvailable = true;
              }
            db.close();
          } catch (Exception e)
          {
            System.out.print(e.toString());
          }

        return isScreenNameAvailable;

    }// </editor-fold>
}
