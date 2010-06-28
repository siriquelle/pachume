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
public class DoesGroupExist {
    //<editor-fold defaultstate="collapsed" desc="doesGroupExist">

    public static boolean run(String groupId) {
        boolean doesGroupExist = false;
        DataBaseConnection db = null;
        String sql = null;
        ResultSet rs = null;

        try
          {
            db = new DataBaseConnection();
            db.connect();
            sql = "SELECT groupId FROM groups where groupId = '" + groupId.trim() + "';";
            rs = db.execSQL(sql);
            if (rs.next())
              {
                doesGroupExist = true;
              }
            db.close();
          } catch (Exception e)
          {
            System.out.print(e);
          }

        return doesGroupExist;
    }// </editor-fold>
}
