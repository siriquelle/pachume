/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.user;

import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class StopFollowingUser {
    //<editor-fold defaultstate="collapsed" desc="removeFriend">
    public static boolean run(int userId, int friendId) {

        boolean isDeFriend = false;

        try
          {
            DataBaseConnection deFriend_db;
            deFriend_db = new DataBaseConnection();
            deFriend_db.connect();

            String deFriend_sql = "DELETE FROM contact WHERE userId = " + userId + " AND friendId = " + friendId;
            deFriend_db.execUpdate(deFriend_sql);

            deFriend_db.close();

            isDeFriend = true;

          } catch (Exception e)
          {
            isDeFriend = false;
          }

        return isDeFriend;
    }// </editor-fold>
}
