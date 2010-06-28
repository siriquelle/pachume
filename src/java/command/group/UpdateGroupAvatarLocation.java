/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.group;

import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class UpdateGroupAvatarLocation {
    //<editor-fold defaultstate="collapsed" desc="updateGroupAvatarLocation">
    public static boolean run(String groupId, String avatarLocation, String contactImageLocation) {

        boolean isAvatarLocationUpdated = false;

        try
          {

            DataBaseConnection updateGroupAvatarLocation_db = new DataBaseConnection();
            updateGroupAvatarLocation_db.connect();

            String updateGroupAvatarLocation_sql = "UPDATE groups SET avatarLocation = '" + avatarLocation + "', contactImageLocation = '" + contactImageLocation + "' WHERE groupId = '" + groupId + "';";
            updateGroupAvatarLocation_db.execUpdate(updateGroupAvatarLocation_sql);
            updateGroupAvatarLocation_db.close();

            isAvatarLocationUpdated = true;
          } catch (Exception e)
          {
            isAvatarLocationUpdated = false;
          }

        return isAvatarLocationUpdated;
    }// </editor-fold>
}
