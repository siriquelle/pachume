/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.pachume;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Vector;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class DestroyPachume {
    //<editor-fold defaultstate="collapsed" desc="deletePachume">
    public static boolean run(int userId, int pachumeId) {

        boolean isPachumeDeleted = false;

        try
          {
            DataBaseConnection delete_pachume_db;
            delete_pachume_db = new DataBaseConnection();
            delete_pachume_db.connect();

            String delete_pachume_sql = "DELETE FROM pachume WHERE userId = " + userId + " AND pachumeId = " + pachumeId;
            delete_pachume_db.execUpdate(delete_pachume_sql);

            String delete_pachume_tags_sql = "DELETE FROM tag WHERE pachumeId = " + pachumeId;
            delete_pachume_db.execUpdate(delete_pachume_tags_sql);

            String delete_pachume_spam_sql = "DELETE FROM spam WHERE pachumeId = " + pachumeId;
            delete_pachume_db.execUpdate(delete_pachume_spam_sql);

            String comment_spam_list_sql = "SELECT commentId FROM comment WHERE pachumeId = " + pachumeId;
            ResultSet comment_spam_list_rs = delete_pachume_db.execSQL(comment_spam_list_sql);

            Vector comment_spam_list = new Vector();
            while (comment_spam_list_rs.next())
              {
                comment_spam_list.add(comment_spam_list_rs.getInt("commentId"));
              }
            String contactList = "";
            Iterator iter = comment_spam_list.iterator();
            while (iter.hasNext())
              {
                contactList += iter.next().toString() + ",";
              }

            String delete_pachume_comment_spam_sql = "DELETE FROM commentspam WHERE commentId IN (" + contactList + "-1);";
            delete_pachume_db.execUpdate(delete_pachume_comment_spam_sql);

            String delete_pachume_comments_sql = "DELETE FROM comment WHERE pachumeId = " + pachumeId;
            delete_pachume_db.execUpdate(delete_pachume_comments_sql);


            delete_pachume_db.close();

            isPachumeDeleted = true;

          } catch (Exception e)
          {
            isPachumeDeleted = false;
          }

        return isPachumeDeleted;
    }// </editor-fold>
}
