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
public class IsCommentSpamable {
    //<editor-fold defaultstate="collapsed" desc="isCommentSpamable">
    public static boolean run(int userId, int commentId) {

        boolean isCommentSpamable = true;

        try
          {
            DataBaseConnection isCommentSpamable_db = new DataBaseConnection();

            isCommentSpamable_db.connect();
            String isCommentSpamable_sql = "SELECT * FROM commentspam WHERE userId = " + userId + " AND commentId = " + commentId + ";";
            ResultSet isCommentSpamable_rs = isCommentSpamable_db.execSQL(isCommentSpamable_sql);

            if (isCommentSpamable_rs.next())
              {
                isCommentSpamable = false;
              }

            isCommentSpamable_db.close();

          } catch (Exception e)
          {
            System.out.println(e.getMessage());
          }

        return isCommentSpamable;
    }// </editor-fold>
}
