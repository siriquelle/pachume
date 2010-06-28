/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.comment;

import model.DataBaseConnection;
import java.sql.ResultSet;

/**
 *
 * @author Siriquelle
 */
public class CountComments {
    //<editor-fold defaultstate="collapsed" desc="countComments">

    public static int run(int pachumeId) {

        int commentCount = 0;
        try
          {

            DataBaseConnection countComments_db = new DataBaseConnection();
            countComments_db.connect();
            String countComments_sql = "SELECT COUNT(*) AS commentCount FROM comment WHERE pachumeId = " + pachumeId;
            ResultSet countComments_rs = countComments_db.execSQL(countComments_sql);

            if (countComments_rs.next())
              {
                commentCount = countComments_rs.getInt("commentCount");
              }
            countComments_db.close();
          } catch (Exception e)
          {
            System.out.print(e);
          }

        return commentCount;
    }// </editor-fold>
}
