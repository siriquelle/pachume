/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.group;

import java.sql.ResultSet;
import java.util.ListIterator;
import java.util.Vector;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class DestroyGroup {
    //<editor-fold defaultstate="collapsed" desc="deleteGroup">
    public static boolean run(int userId, String groupId) {

        boolean isGroupDeleted = false;

        try
          {
            DataBaseConnection deleteGroup_db;
            deleteGroup_db = new DataBaseConnection();
            deleteGroup_db.connect();

            String groupCommentList_sql = "SELECT pachumeId FROM pachume WHERE groupId = '" + groupId + "'";
            Vector groupCommentList = new Vector();
            ResultSet groupCommentList_rs = deleteGroup_db.execSQL(groupCommentList_sql);
            while (groupCommentList_rs.next())
              {
                groupCommentList.add(groupCommentList_rs.getInt("pachumeId"));
              }

            String commentList = "";
            ListIterator thisList = groupCommentList.listIterator();

            while (thisList.hasNext())
              {
                commentList += thisList.next().toString() + ",";
              }

            String deleteGroups_sql = "DELETE FROM comment WHERE pachumeId IN (" + commentList + "-1000);";
            deleteGroup_db.execUpdate(deleteGroups_sql);

            String deleteGroup_sql = "DELETE FROM groups WHERE groupId = '" + groupId + "';";
            deleteGroup_db.execUpdate(deleteGroup_sql);

            String deleteGroupPachumes_sql = "DELETE FROM pachume WHERE groupId = '" + groupId + "';";
            deleteGroup_db.execUpdate(deleteGroupPachumes_sql);

            String deleteGroupMember_sql = "DELETE FROM groupsmember WHERE groupId = '" + groupId + "';";
            deleteGroup_db.execUpdate(deleteGroupMember_sql);

            deleteGroup_db.close();

            isGroupDeleted = true;

          } catch (Exception e)
          {
            isGroupDeleted = false;
          }

        return isGroupDeleted;
    }// </editor-fold>
}
