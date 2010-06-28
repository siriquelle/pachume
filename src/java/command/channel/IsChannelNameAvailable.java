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
public class IsChannelNameAvailable {
    //<editor-fold defaultstate="collapsed" desc="isChannelNameAvailable">
    public static boolean run(String channelName) {

        boolean isChannelNameAvailable = true;

        try
        {
            DataBaseConnection isChannelNameAvailable_db = new DataBaseConnection();
            isChannelNameAvailable_db.connect();

            String isChannelNameAvailable_sql = "SELECT channelName FROM channel WHERE channelName = '" + channelName + "';";
            ResultSet isChannelNameAvailable_rs = isChannelNameAvailable_db.execSQL(isChannelNameAvailable_sql);

            if (isChannelNameAvailable_rs.next())
            {
                isChannelNameAvailable = false;
            }

            isChannelNameAvailable_db.close();
        } catch (Exception e)
        {
            isChannelNameAvailable = false;
        }

        return isChannelNameAvailable;
    }// </editor-fold>
}
