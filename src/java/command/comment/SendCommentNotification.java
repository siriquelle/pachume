/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.comment;

import bean.userBean;
import command.notification.SendEmail;
import command.pachume.GetPachumeOwnersScreenName;
import command.user.GetScreenName;
import java.sql.ResultSet;
import java.util.ListIterator;
import java.util.Vector;
import model.DataBaseConnection;
import template.Email;
import template.CommentNotificationEmail;




/**
 *
 * @author Siriquelle
 */
public class SendCommentNotification {
    //<editor-fold defaultstate="collapsed" desc="sendCommentNotification">

    public static boolean run(int userId, int commentId, int pachumeId) {

        boolean isMailSent = false;
        String comment = "";
        String by = "";
        String to = "";
        Vector distributionList = new Vector();
        ListIterator iter = null;
        Email commentEmail = null;

        String screenName = "";

        try
          {
            DataBaseConnection db = new DataBaseConnection();
            db.connect();

            String sql = "SELECT comment FROM comment WHERE commentId = " + commentId;
            ResultSet rs = db.execSQL(sql);
            if (rs.next())
              {
                comment = rs.getString("comment");
              }
            sql = "SELECT screenName FROM user WHERE userId = " + userId;
            rs = db.execSQL(sql);
            if (rs.next())
              {
                by = rs.getString("screenName");
              }
            sql = "SELECT DISTINCT userId FROM comment WHERE pachumeId = " + pachumeId + " AND userId != " + userId + " ORDER BY userId";
            rs = db.execSQL(sql);
            while (rs.next())
              {
                distributionList.add(GetScreenName.run(rs.getInt("userId")).trim());
              }

            distributionList.add(GetPachumeOwnersScreenName.run(pachumeId).trim());

            iter = distributionList.listIterator();
            while (iter.hasNext())
              {
                to = iter.next().toString();
                userBean thisUser = new userBean(to);
                boolean isNotifable = thisUser.isNotifiable();

                screenName = GetScreenName.run(userId);
                userBean commentor = new userBean(screenName);

                if (!thisUser.getApiKey().equals(commentor.getApiKey()))
                  {
                    if (isNotifable)
                      {
                        commentEmail = new CommentNotificationEmail(to, by, comment, pachumeId);
                        SendEmail.run(thisUser.getEmail().trim(), commentEmail.getSubject(), commentEmail.getBody());
                      }
                  }
              }
            db.close();

            isMailSent = true;

          } catch (Exception e)
          {
            isMailSent = false;
          }

        return isMailSent;
    }// </editor-fold>
}
