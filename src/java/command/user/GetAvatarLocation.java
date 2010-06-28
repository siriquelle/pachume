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
public class GetAvatarLocation {
    //<editor-fold defaultstate="collapsed" desc="getAvatarLocation : screenName">
    public static String run(String screenName) {

        String avatarLocation = " ";

        try
          {
            DataBaseConnection avatarLocation_db = new DataBaseConnection();
            avatarLocation_db.connect();
            String avatarLocation_sql = "SELECT avatarLocation FROM user WHERE screenName = '" + screenName + "';";
            ResultSet avatarLocation_rs = avatarLocation_db.execSQL(avatarLocation_sql);

            if (avatarLocation_rs.next())
              {
                avatarLocation = avatarLocation_rs.getString("avatarLocation");
              }
            avatarLocation_db.close();
          } catch (Exception e)
          {
            avatarLocation = e.getMessage();
          }

        return avatarLocation;
    }// </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getAvatarLocation : userId">
    public static String run(int userId) {

        String avatarLocation = " ";

        try
          {
            DataBaseConnection avatarLocation_db = new DataBaseConnection();
            avatarLocation_db.connect();
            String avatarLocation_sql = "SELECT avatarLocation FROM user WHERE userId = " + userId + ";";
            ResultSet avatarLocation_rs = avatarLocation_db.execSQL(avatarLocation_sql);

            if (avatarLocation_rs.next())
              {
                avatarLocation = avatarLocation_rs.getString("avatarLocation");
              }
            avatarLocation_db.close();
          } catch (Exception e)
          {
            avatarLocation = e.getMessage();
          }
        return avatarLocation;
    }// </editor-fold>
}
