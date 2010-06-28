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
public class GetPachume {
    //<editor-fold defaultstate="collapsed" desc="getPachume">
    public static String run(int pachumeId) {

        String pachume = "pachume";

        try
          {
            DataBaseConnection getPachume_db = new DataBaseConnection();

            getPachume_db.connect();
            String getPachume_sql = "SELECT pachume FROM pachume WHERE pachumeId = " + pachumeId + ";";
            ResultSet getPachume_rs = getPachume_db.execSQL(getPachume_sql);

            if (getPachume_rs.next())
              {
                pachume = getPachume_rs.getString(pachume);
              }

            getPachume_db.close();

          } catch (Exception e)
          {
            System.out.println(e.getMessage());
          }

        return pachume;
    }// </editor-fold>
}
