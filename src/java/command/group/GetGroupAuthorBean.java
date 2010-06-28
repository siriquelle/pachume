/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.group;

import bean.userBean;
import command.user.GetScreenName;
import java.sql.ResultSet;
import model.DataBaseConnection;


/**
 *
 * @author Siriquelle
 */
public class GetGroupAuthorBean {
    //<editor-fold defaultstate="collapsed" desc="getGroupAuthorBean">
    public static userBean run(String groupId) {
        userBean groupAuthorBean = null;
        DataBaseConnection db = null;
        String sql = null;
        ResultSet rs = null;
        int userId = 0;
        try
          {
            db = new DataBaseConnection();
            db.connect();
            sql = "SELECT userId FROM groups WHERE groupId = '" + groupId + "';";
            rs = db.execSQL(sql);
            if (rs.next())
              {
                groupAuthorBean = new userBean(GetScreenName.run(rs.getInt("userId")));
              }
          } catch (Exception e)
          {
            System.out.print(e);
          }

        return groupAuthorBean;
    }// </editor-fold>
}
