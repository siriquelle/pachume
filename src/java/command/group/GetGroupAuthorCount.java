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
public class GetGroupAuthorCount {
    //<editor-fold defaultstate="collapsed" desc="getGroupAuthorCount">
    public static int run(int userId) {

        int GroupAuthorCount = 0;
        try
          {

            String getGroupAuthorCount_sql = "SELECT COUNT(groupId) AS groupAuthorCount FROM groups WHERE userId = " + userId + ";";
            DataBaseConnection getGroupAuthorCount_db = new DataBaseConnection();
            getGroupAuthorCount_db.connect();
            ResultSet getGroupAuthorCount_rs = getGroupAuthorCount_db.execSQL(getGroupAuthorCount_sql);

            while (getGroupAuthorCount_rs.next())
              {
                GroupAuthorCount = getGroupAuthorCount_rs.getInt("groupAuthorCount");
              }
            getGroupAuthorCount_db.close();
          } catch (Exception e)
          {
            System.out.print(e);
          }
        return GroupAuthorCount;
    }// </editor-fold>
}
