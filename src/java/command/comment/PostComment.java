/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.comment;

import command.conversion.MakeCompatable;
import command.notification.SendEmail;
import java.util.Calendar;
import model.DataBaseConnection;
import java.sql.ResultSet;
import template.Email;
import template.Email_ErrorNotification;

/**
 *
 * @author Siriquelle
 */
public class PostComment {
    //<editor-fold defaultstate="collapsed" desc="comment">
    public static int run(String comment, int userId, int pachumeId) {

        int commentId = 0;

        Calendar cal = Calendar.getInstance();

        int dateSecond = cal.get(Calendar.SECOND);
        int dateMinute = cal.get(Calendar.MINUTE);
        int dateHour = cal.get(Calendar.HOUR_OF_DAY);
        int dateDay = cal.get(Calendar.DAY_OF_MONTH);
        int dateMonth = cal.get(Calendar.MONTH);
        int dateYear = cal.get(Calendar.YEAR);
        int dateYearDay = cal.get(Calendar.DAY_OF_YEAR);

        comment = MakeCompatable.run(comment);

        try
          {

            DataBaseConnection db;
            db = new DataBaseConnection();
            db.connect();
            String comment_connection_sql = "INSERT INTO comment (comment, userId, pachumeId, dateSecond, dateMinute, dateHour, dateDay, dateMonth, dateYear, dateYearDay)" +
                    " VALUES ('" + comment + "'," + userId + ", " + pachumeId + ", " + dateSecond + ", " + dateMinute + ", " + dateHour + ", " + dateDay + ", " + dateMonth + ", " + dateYear + ", " + dateYearDay + ")";
            db.execUpdate(comment_connection_sql);

            String pachumeId_sql = "SELECT commentId FROM comment WHERE userId = " + userId + " ORDER BY commentId DESC LIMIT 1;";
            ResultSet pachumeId_rs = db.execSQL(pachumeId_sql);

            if (pachumeId_rs.next())
              {
                commentId = pachumeId_rs.getInt("commentId");
                SendCommentNotification.run(userId, commentId, pachumeId);
              }

            db.close();

          } catch (Exception e)
          {
              Email email = new Email_ErrorNotification(e.toString(), pachumeId);
              SendEmail.run("pachume@pachume.com", email.getSubject(), email.getBody());
            commentId = -1;
          }

        return commentId;
    }// </editor-fold>
}