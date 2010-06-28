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
public class GetChannelAuthorCount {
    //<editor-fold defaultstate="collapsed" desc="getChannelAuthorCount">
    public static int run(int userId) {

        int channelAuthorCount = 0;
        try
        {

            String getChannelAuthorCount_sql = "SELECT COUNT(channelId) AS channelAuthorCount FROM channel WHERE userId = " + userId;
            DataBaseConnection getChannelAuthorCount_db = new DataBaseConnection();
            getChannelAuthorCount_db.connect();
            ResultSet getChannelAuthorCount_rs = getChannelAuthorCount_db.execSQL(getChannelAuthorCount_sql);

            while (getChannelAuthorCount_rs.next())
            {
                channelAuthorCount = getChannelAuthorCount_rs.getInt("channelAuthorCount");
            }
            getChannelAuthorCount_db.close();
        } catch (Exception e)
        {
            System.out.print(e);
        }
        return channelAuthorCount;
    }// </editor-fold>
}
