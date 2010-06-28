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
public class GetPachumeImageLocation {
    //<editor-fold defaultstate="collapsed" desc="getPachumeImageLocation">

    public static String run(int userId) {

        String pachumeImageLocation = " ";

        try
          {
            DataBaseConnection getPachumeImageLocation_db = new DataBaseConnection();
            getPachumeImageLocation_db.connect();
            String getPachumeImageLocation_sql = "SELECT pachumeImageLocation FROM user WHERE userId = " + userId + ";";
            ResultSet getPachumeImageLocation_rs = getPachumeImageLocation_db.execSQL(getPachumeImageLocation_sql);

            if (getPachumeImageLocation_rs.next())
              {
                pachumeImageLocation = getPachumeImageLocation_rs.getString("pachumeImageLocation");
              }
            getPachumeImageLocation_db.close();
          } catch (Exception e)
          {
            pachumeImageLocation = e.getMessage();
          }
        return pachumeImageLocation;
    }// </editor-fold>
}
