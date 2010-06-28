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
public class GetScreenName {
    //<editor-fold defaultstate="collapsed" desc="getScreenName">

    public static String run(int userId) {

        String screenName = " ";

        try
          {
            DataBaseConnection getScreenName_db = new DataBaseConnection();
            getScreenName_db.connect();
            String getScreenName_sql = "SELECT screenName FROM user WHERE userId = " + userId + ";";
            ResultSet getScreenName_rs = getScreenName_db.execSQL(getScreenName_sql);

            if (getScreenName_rs.next())
              {
                screenName += getScreenName_rs.getString("screenName");
              }
            getScreenName_db.close();
          } catch (Exception e)
          {
            screenName = e.getMessage();
          }

        return screenName;
    }// </editor-fold>
}
