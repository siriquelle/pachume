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
public class GetGroupName {
    //<editor-fold defaultstate="collapsed" desc="getGroupName">

    public static String run(String groupId) {
        String groupName = "";

        try
          {
            String getGroupName_sql = "SELECT groupName FROM groups WHERE groupId = '" + groupId + "';";
            DataBaseConnection getGroupName_db = new DataBaseConnection();
            getGroupName_db.connect();
            ResultSet getGroupName_rs = getGroupName_db.execSQL(getGroupName_sql);

            if (getGroupName_rs.next())
              {
                groupName = getGroupName_rs.getString("groupName");
              }
            getGroupName_db.close();
          } catch (Exception e)
          {
            groupName = e.toString();
          }

        return groupName;
    }
// </editor-fold>
}
