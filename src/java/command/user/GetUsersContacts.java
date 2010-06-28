/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.user;

import java.sql.ResultSet;
import java.util.Vector;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class GetUsersContacts {
    //<editor-fold defaultstate="collapsed" desc="getUsersContacts">

    public static Vector run(int userId) {

        Vector userContacts = new Vector();

        try
          {
            DataBaseConnection userContacts_db = new DataBaseConnection();
            userContacts_db.connect();
            String userContacts_sql = "SELECT friendId FROM contact WHERE userId = " + userId + ";";
            ResultSet userContacts_rs = userContacts_db.execSQL(userContacts_sql);

            while (userContacts_rs.next())
              {
                userContacts.addElement(userContacts_rs.getInt("friendId"));
              }

            userContacts_db.close();

          } catch (Exception e)
          {
            userContacts.addElement(e.toString());
          }

        return userContacts;
    }// </editor-fold>
}
