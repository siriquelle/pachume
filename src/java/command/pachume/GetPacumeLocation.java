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
public class GetPacumeLocation {
    //<editor-fold defaultstate="collapsed" desc="getPacumeLocation">

    public static String run(int pachumeId) {

        String location = "";

        try
          {
            DataBaseConnection location_db = new DataBaseConnection();
            location_db.connect();
            String location_sql = "SELECT location FROM pachume WHERE pachumeId = " + pachumeId + ";";
            ResultSet location_rs = location_db.execSQL(location_sql);

            if (location_rs.next())
              {
                location = location_rs.getString("location");
              }
            location_db.close();
          } catch (Exception e)
          {
            location = e.getMessage();
          }

        if (location.equals("null") || location == null || location.trim().equals("from location unknown"))
          {
            location = " ";
          }

        return location;
    }// </editor-fold>
}
