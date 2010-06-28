/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.group;

import java.sql.ResultSet;
import java.util.Vector;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class GetGroupMembers {
    //<editor-fold defaultstate="collapsed" desc="getGroupMembers">
    public static Vector run(String groupId) {

        Vector groupMembers = new Vector();

        try
          {
            String getGroupMembers_sql = "SELECT userId FROM groupsmember WHERE groupId = '" + groupId + "';";
            DataBaseConnection getGroupMembers_db = new DataBaseConnection();
            getGroupMembers_db.connect();
            ResultSet getGroupMembers_rs = getGroupMembers_db.execSQL(getGroupMembers_sql);

            while (getGroupMembers_rs.next())
              {
                groupMembers.add(getGroupMembers_rs.getString("userId"));
              }
            getGroupMembers_db.close();
          } catch (Exception e)
          {
            groupMembers.add(e.toString());
          }

        return groupMembers;
    }
    // </editor-fold>
}
