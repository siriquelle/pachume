/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.pachume;

import command.user.GetScreenName;
import java.sql.ResultSet;
import model.DataBaseConnection;


/**
 *
 * @author Siriquelle
 */
public class GetPachumeOwnersScreenName {
    //<editor-fold defaultstate="collapsed" desc="getPachumeOwnersScreenName">

    public static String run(int pachumeId) {

        int userId = 0;

        try
          {
            DataBaseConnection db = new DataBaseConnection();

            db.connect();
            String sql = "SELECT userId FROM pachume WHERE pachumeId = " + pachumeId + ";";
            ResultSet rs = db.execSQL(sql);

            if (rs.next())
              {
                userId = rs.getInt("userId");
              }

            db.close();

          } catch (Exception e)
          {
            System.out.println(e.getMessage());
          }

        return GetScreenName.run(userId).trim();
    }// </editor-fold>
}
