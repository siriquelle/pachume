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
public class IsGroupNameAvailable {
    //<editor-fold defaultstate="collapsed" desc="isGroupNameAvailable">

    public static boolean run(String groupName) {

        boolean isGroupNameAvailable = true;

        try
          {
            DataBaseConnection isGroupNameAvailable_db = new DataBaseConnection();
            isGroupNameAvailable_db.connect();

            String isGroupNameAvailable_sql = "SELECT groupName FROM groups WHERE groupName = '" + groupName + "';";
            ResultSet isGroupNameAvailable_rs = isGroupNameAvailable_db.execSQL(isGroupNameAvailable_sql);

            if (isGroupNameAvailable_rs.next())
              {
                isGroupNameAvailable = false;
              }

            isGroupNameAvailable_db.close();
          } catch (Exception e)
          {
            isGroupNameAvailable = false;
          }

        return isGroupNameAvailable;
    }// </editor-fold>
}
