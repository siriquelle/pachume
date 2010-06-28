/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.comment;

import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class RemoveComment {
    //<editor-fold defaultstate="collapsed" desc="deleteComment">

    public static boolean run(int userId, int commentId) {

        boolean isCommentDeleted = false;

        try
          {
            DataBaseConnection deleteComment_db;
            deleteComment_db = new DataBaseConnection();
            deleteComment_db.connect();

            String deleteComment_sql = "DELETE FROM comment WHERE userId = " + userId + " AND commentId = " + commentId;
            deleteComment_db.execUpdate(deleteComment_sql);

            String deleteCommentSpam_sql = "DELETE FROM commentspam WHERE commentId = " + commentId;
            deleteComment_db.execUpdate(deleteCommentSpam_sql);

            deleteComment_db.close();

            isCommentDeleted = true;

          } catch (Exception e)
          {
            isCommentDeleted = false;
          }

        return isCommentDeleted;
    }// </editor-fold>
}
