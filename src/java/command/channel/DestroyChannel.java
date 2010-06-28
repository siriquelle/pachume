/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.channel;
import model.DataBaseConnection;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.ListIterator;
/**
 *
 * @author Siriquelle
 */
public class DestroyChannel {
    //<editor-fold defaultstate="collapsed" desc="deleteChannel">
    public static boolean run(int userId, int channelId) {

        boolean isChannelDeleted = false;

        try
        {
            DataBaseConnection deleteChannel_db;
            deleteChannel_db = new DataBaseConnection();
            deleteChannel_db.connect();

            String channelCommentList_sql = "SELECT pachumeId FROM pachume WHERE userId = " + userId + " AND channelId = " + channelId;
            Vector channelCommentList = new Vector();
            ResultSet channelCommentList_rs = deleteChannel_db.execSQL(channelCommentList_sql);
            while (channelCommentList_rs.next())
            {
                channelCommentList.add(channelCommentList_rs.getInt("pachumeId"));
            }

            String commentList = "";
            ListIterator thisList = channelCommentList.listIterator();

            while (thisList.hasNext())
            {
                commentList += thisList.next().toString() + ",";
            }
            
            String deleteComments_sql = "DELETE FROM comment WHERE pachumeId IN (" + commentList + "-1000);";
            deleteChannel_db.execUpdate(deleteComments_sql);
            
            String deleteChannel_sql = "DELETE FROM channel WHERE userId = " + userId + " AND channelId = " + channelId;
            deleteChannel_db.execUpdate(deleteChannel_sql);

            String deleteChannelPachumes_sql = "DELETE FROM pachume WHERE userId = " + userId + " AND channelId = " + channelId;
            deleteChannel_db.execUpdate(deleteChannelPachumes_sql);

            String deleteChannelMember_sql = "DELETE FROM channelmember WHERE userId = " + userId + " AND channelId = " + channelId;
            deleteChannel_db.execUpdate(deleteChannelMember_sql);

            deleteChannel_db.close();

            isChannelDeleted = true;

        } catch (Exception e)
        {
            isChannelDeleted = false;
        }

        return isChannelDeleted;
    }// </editor-fold>
}
