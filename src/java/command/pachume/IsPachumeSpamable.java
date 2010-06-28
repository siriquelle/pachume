/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.pachume;

import java.sql.ResultSet;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class IsPachumeSpamable {
    //<editor-fold defaultstate="collapsed" desc="isPachumeSpamable">

    public static boolean run(int userId, int pachumeId) {

        boolean isPachumeSpamable = true;

        try
          {
            DataBaseConnection location_db = new DataBaseConnection();

            location_db.connect();
            String location_sql = "SELECT * FROM spam WHERE userId = " + userId + " AND pachumeId = " + pachumeId + ";";
            ResultSet location_rs = location_db.execSQL(location_sql);

            if (location_rs.next())
              {
                isPachumeSpamable = false;
              }

            location_db.close();

          } catch (Exception e)
          {
            System.out.println(e.getMessage());
          }

        return isPachumeSpamable;
    }// </editor-fold>
}
