/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.group;

import java.sql.ResultSet;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class GetGroupAvatarLocation {
    //<editor-fold defaultstate="collapsed" desc="getGroupAvatarLocation">

    public static String run(String groupId) {

        String avatarLocation = "avatarLocation";

        try
          {
            String getGroupAvatarLocation_sql = "SELECT avatarLocation FROM groups WHERE groupId = '" + groupId + "';";
            DataBaseConnection getGroupAvatarLocation_db = new DataBaseConnection();
            getGroupAvatarLocation_db.connect();
            ResultSet getGroupAvatarLocation_rs = getGroupAvatarLocation_db.execSQL(getGroupAvatarLocation_sql);

            if (getGroupAvatarLocation_rs.next())
              {
                avatarLocation = getGroupAvatarLocation_rs.getString(avatarLocation);
              }
            getGroupAvatarLocation_db.close();
          } catch (Exception e)
          {
            avatarLocation = e.toString();
          }

        return avatarLocation;
    }
// </editor-fold>
}
