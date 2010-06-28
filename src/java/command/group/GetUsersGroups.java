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
public class GetUsersGroups {
    //<editor-fold defaultstate="collapsed" desc="getUsersGroups">
    public static Vector run(int userId) {

        Vector usersGroups = new Vector();

        try
          {
            String getUsersGroups_sql = "SELECT groupId FROM groupsmember WHERE userId = " + userId + ";";
            DataBaseConnection getUsersGroups_db = new DataBaseConnection();
            getUsersGroups_db.connect();
            ResultSet getUsersGroups_rs = getUsersGroups_db.execSQL(getUsersGroups_sql);

            while (getUsersGroups_rs.next())
              {
                usersGroups.add(getUsersGroups_rs.getString("groupId"));
              }
            getUsersGroups_db.close();
          } catch (Exception e)
          {
            usersGroups.add(e.toString());
          }

        return usersGroups;
    }
    // </editor-fold>
}
