/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.channel;

import model.DataBaseConnection;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Siriquelle
 */
public class GetChannelMembers {
//<editor-fold defaultstate="collapsed" desc="getChannelMembers">
    public static Vector run(int channelId) {

        Vector channelMembers = new Vector();

        try
          {
            String getChannelMembers_sql = "SELECT userId FROM channelmember WHERE channelId = " + channelId + ";";
            DataBaseConnection getChannelMembers_db = new DataBaseConnection();
            getChannelMembers_db.connect();
            ResultSet getChannelMembers_rs = getChannelMembers_db.execSQL(getChannelMembers_sql);

            while (getChannelMembers_rs.next())
              {
                channelMembers.add(getChannelMembers_rs.getString("userId"));
              }
            getChannelMembers_db.close();
          } catch (Exception e)
          {
            channelMembers.add(e.toString());
          }

        return channelMembers;
    }
    // </editor-fold>
}
