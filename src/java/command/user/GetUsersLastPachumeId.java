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
public class GetUsersLastPachumeId {
    //<editor-fold defaultstate="collapsed" desc="getUsersLastPachumeId">
    public static int run(int userId) {
        int pachumeId = -1;

        try
          {
            DataBaseConnection getUsersLastPachumeId_db = new DataBaseConnection();
            getUsersLastPachumeId_db.connect();

            String getUsersLastPachumeId_sql = "SELECT pachumeId from pachume where userId = " + userId + " ORDER BY pachumeId DESC LIMIT 1";

            ResultSet getUsersLastPachumeId_rs = getUsersLastPachumeId_db.execSQL(getUsersLastPachumeId_sql);

            if (getUsersLastPachumeId_rs.next())
              {
                pachumeId = getUsersLastPachumeId_rs.getInt("pachumeId");
              }

            getUsersLastPachumeId_db.close();

          } catch (Exception e)
          {
            String err = e.toString();
            System.out.print(err);
          }

        return pachumeId;
    }// </editor-fold>
}
