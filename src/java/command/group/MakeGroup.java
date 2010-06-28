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
public class MakeGroup {
    //<editor-fold defaultstate="collapsed" desc="createGroup">
    public static String run(String groupId, int userId, String groupName, String groupDescription, String avatarLocation, String contactImageLocation, String backgroundImageLocation, String backgroundImageRepeat, String backgroundColor) {


        try
          {
            String createGroup_sql = "INSERT INTO groups(groupId, userId, groupName, groupDescription, avatarLocation, contactImageLocation, backgroundImageLocation, backgroundImageRepeat,backgroundColor)" +
                    " VALUES('" + groupId + "'," + userId + ", '" + groupName + "', '" + groupDescription + "', '" + avatarLocation + "', '" + contactImageLocation + "', '" + backgroundImageLocation + "', '" + backgroundImageRepeat + "', '" + backgroundColor + "');";

            DataBaseConnection createGroup_db = new DataBaseConnection();
            createGroup_db.connect();
            createGroup_db.execUpdate(createGroup_sql);

            createGroup_db.close();
          } catch (Exception e)
          {
            groupId = e.toString();
          }

        return groupId;

    }
// </editor-fold>
}
