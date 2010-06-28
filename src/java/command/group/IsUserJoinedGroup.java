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
public class IsUserJoinedGroup {
    //<editor-fold defaultstate="collapsed" desc="isUserJoinedGroup">

    public static boolean run(String groupId, HttpSession session) {

        boolean isUserJoinedGroup = false;

        try
          {

            if (groupId.equals("00000000-0000-0000-0000-000000000000"))
              {
                isUserJoinedGroup = true;

              } else
              {
                User thisUser = (User) session.getAttribute("thisUser");
                int userId = thisUser.getUserId();

                DataBaseConnection isUserJoinedGroup_db = new DataBaseConnection();
                isUserJoinedGroup_db.connect();

                String isUserJoinedGroup_sql = "SELECT userId, groupId FROM groupsmember WHERE userId = " + userId + " && groupId = '" + groupId + "';";
                ResultSet isUsersFriend_rs = isUserJoinedGroup_db.execSQL(isUserJoinedGroup_sql);

                if (isUsersFriend_rs.next())
                  {
                    isUserJoinedGroup = true;
                  }
                isUserJoinedGroup_db.close();
              }
          } catch (Exception e)
          {
            isUserJoinedGroup = false;
          }
        return isUserJoinedGroup;
    }// </editor-fold>
}
