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
public class SpamThisComment {
    //<editor-fold defaultstate="collapsed" desc="spamComment">

    public static boolean run(int userId, String commentId) {

        boolean isPachumeSpammed = false;

        try
          {
            DataBaseConnection spamComment_db = new DataBaseConnection();
            spamComment_db.connect();

            String spamComment_sql = "UPDATE comment SET spamCount = spamCount + 1 WHERE commentId = " + commentId;

            spamComment_db.execUpdate(spamComment_sql);

            String spamComment_sql1 = "INSERT INTO commentspam (userId, commentId)" +
                    " VALUES (" + userId + "," + commentId + ")";

            spamComment_db.execUpdate(spamComment_sql1);
            spamComment_db.close();

            isPachumeSpammed = true;

          } catch (Exception e)
          {
            isPachumeSpammed = false;
          }

        return isPachumeSpammed;
    }// </editor-fold>
}
