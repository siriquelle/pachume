/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.pachume;

import java.sql.ResultSet;
import java.util.Vector;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class GetPachumeTags {
    //<editor-fold defaultstate="collapsed" desc="GetPachumeTags">

    public static Vector run(int pachumeId) {

        Vector pachumeTags = new Vector();

        try
          {
            DataBaseConnection tag_db = new DataBaseConnection();
            tag_db.connect();
            String tag_sql = "SELECT tag FROM tag WHERE pachumeId = " + pachumeId + " ORDER BY pachumeId DESC;";
            ResultSet tag_rs = tag_db.execSQL(tag_sql);

            while (tag_rs.next())
              {
                pachumeTags.addElement(tag_rs.getString("tag"));
              }

            tag_db.close();

          } catch (Exception e)
          {
            pachumeTags.addElement(e.toString());
          }

        return pachumeTags;
    }// </editor-fold>
}
