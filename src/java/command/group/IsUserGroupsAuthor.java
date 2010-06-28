/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.group;

import java.sql.ResultSet;
import javax.servlet.http.HttpSession;
import model.DataBaseConnection;
import model.User;

/**
 *
 * @author Siriquelle
 */
public class IsUserGroupsAuthor {
    //<editor-fold defaultstate="collapsed" desc="isUserGroupsAuthor">

    public static boolean run(String groupId, HttpSession session) {

        boolean isUserGroupsAuthor = false;

        try
          {

            User thisUser = (User) session.getAttribute("thisUser");
            int userId = thisUser.getUserId();

            DataBaseConnection isUserGroupsAuthor_db = new DataBaseConnection();
            isUserGroupsAuthor_db.connect();

            String isUserGroupsAuthor_sql = "SELECT userId, groupId FROM groups WHERE userId = " + userId + " AND groupId = '" + groupId + "';";
            ResultSet isUserGroupsAuthor_rs = isUserGroupsAuthor_db.execSQL(isUserGroupsAuthor_sql);

            if (isUserGroupsAuthor_rs.next())
              {
                isUserGroupsAuthor = true;
              }

            isUserGroupsAuthor_db.close();
          } catch (Exception e)
          {
            isUserGroupsAuthor = false;
          }
        return isUserGroupsAuthor;
    }// </editor-fold>
}
