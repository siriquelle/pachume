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
public class GetGroupId {
    //<editor-fold defaultstate="collapsed" desc="getGroupId">
    public static String run(String groupName) {

        String groupId = "";
        try
          {
            String getGroupId_sql = "SELECT groupId FROM groups WHERE groupName = '" + groupName + "';";
            DataBaseConnection getGroupId_db = new DataBaseConnection();
            getGroupId_db.connect();
            ResultSet getGroupId_rs = getGroupId_db.execSQL(getGroupId_sql);

            if (getGroupId_rs.next())
              {
                groupId = getGroupId_rs.getString("groupId");
              }
            getGroupId_db.close();
          } catch (Exception e)
          {
            groupId = e.toString();
          }
        return groupId;
    }
// </editor-fold>
}
