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
public class GetPachumeGroupId {
    //<editor-fold defaultstate="collapsed" desc="getPachumeGroupId">
    public static String run(int pachumeId) {

        String groupId = "";

        try
          {
            DataBaseConnection getPachumeGroupId_db = new DataBaseConnection();

            getPachumeGroupId_db.connect();

            String getPachumeGroupId_sql = "SELECT groupId FROM pachume WHERE pachumeId = " + pachumeId + ";";
            ResultSet getPachumeGroupId_rs = getPachumeGroupId_db.execSQL(getPachumeGroupId_sql);

            if (getPachumeGroupId_rs.next())
              {
                groupId = getPachumeGroupId_rs.getString("groupId");
              }

            getPachumeGroupId_db.close();

          } catch (Exception e)
          {
            System.out.println(e.getMessage());
          }

        return groupId;
    }// </editor-fold>
}
