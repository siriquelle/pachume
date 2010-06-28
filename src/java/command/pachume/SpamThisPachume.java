/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.pachume;

import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class SpamThisPachume {
    //<editor-fold defaultstate="collapsed" desc="spamPachume">

    public static boolean run(int userId, String pachumeId) {

        boolean isPachumeSpammed = false;

        try
          {
            DataBaseConnection spamPachume_db = new DataBaseConnection();
            spamPachume_db.connect();

            String spamPachume_sql = "UPDATE pachume SET spamCount = spamCount + 1 WHERE pachumeId = " + pachumeId;

            spamPachume_db.execUpdate(spamPachume_sql);

            String spamPachume_sql1 = "INSERT INTO spam (userId, pachumeId)" +
                    " VALUES (" + userId + "," + pachumeId + ")";

            spamPachume_db.execUpdate(spamPachume_sql1);
            spamPachume_db.close();

            isPachumeSpammed = true;

          } catch (Exception e)
          {
            isPachumeSpammed = false;
          }

        return isPachumeSpammed;
    }// </editor-fold>
}
