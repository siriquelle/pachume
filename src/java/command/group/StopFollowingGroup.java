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
public class StopFollowingGroup {
    //<editor-fold defaultstate="collapsed" desc="leaveGroup">
    public static boolean run(int userId, String groupId) {

        boolean isGroupLeaved = false;

        try
          {
            DataBaseConnection leaveGroup_db;
            leaveGroup_db = new DataBaseConnection();
            leaveGroup_db.connect();

            String leaveGroup_sql = "DELETE FROM groupsmember WHERE userId = " + userId + " AND groupId = '" + groupId + "';";
            leaveGroup_db.execUpdate(leaveGroup_sql);

            leaveGroup_db.close();

            isGroupLeaved = true;

          } catch (Exception e)
          {
            isGroupLeaved = false;
          }

        return isGroupLeaved;
    }// </editor-fold>
}
