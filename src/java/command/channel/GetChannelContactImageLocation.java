/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.channel;

import model.DataBaseConnection;
import java.sql.ResultSet;

/**
 *
 * @author Siriquelle
 */
public class GetChannelContactImageLocation {
    //<editor-fold defaultstate="collapsed" desc="getChannelContactImageLocation">
    public static String run(int channelId) {

        String contactImageLocation = "contactImageLocation";

        try
        {
            String getContactImageLocation_sql = "SELECT contactImageLocation FROM channel WHERE channelId = " + channelId + ";";
            DataBaseConnection getContactImageLocation_db = new DataBaseConnection();
            getContactImageLocation_db.connect();
            ResultSet getContactImageLocation_rs = getContactImageLocation_db.execSQL(getContactImageLocation_sql);

            if (getContactImageLocation_rs.next())
            {
                contactImageLocation = getContactImageLocation_rs.getString(contactImageLocation);
            }
            getContactImageLocation_db.close();
        } catch (Exception e)
        {
            contactImageLocation = e.toString();
        }

        return contactImageLocation;
    }
// </editor-fold>
}
