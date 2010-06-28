/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.user;

import java.sql.ResultSet;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class GetContactImageLocation {
    //<editor-fold defaultstate="collapsed" desc="getContactImageLocation">

    public static String run(int userId) {

        String contactImageLocation = " ";

        try
          {
            DataBaseConnection getContactImageLocation_db = new DataBaseConnection();
            getContactImageLocation_db.connect();
            String getContactImageLocation_sql = "SELECT contactImageLocation FROM user WHERE userId = " + userId + ";";
            ResultSet getContactImageLocation_rs = getContactImageLocation_db.execSQL(getContactImageLocation_sql);

            if (getContactImageLocation_rs.next())
              {
                contactImageLocation = getContactImageLocation_rs.getString("contactImageLocation");
              }
            getContactImageLocation_db.close();
          } catch (Exception e)
          {
            contactImageLocation = e.getMessage();
          }
        return contactImageLocation;
    }// </editor-fold>
}
