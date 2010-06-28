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
public class FollowUser {
    //<editor-fold defaultstate="collapsed" desc="beFriend">
    public static boolean run(int userId, int friendId) {
        boolean isBeFriended = false;

        try
          {
            DataBaseConnection beFriend_db = new DataBaseConnection();
            beFriend_db.connect();

            String beFriend_sql1 = "INSERT INTO contact (userId, friendId)" +
                    " VALUES (" + userId + ", " + friendId + ");";

            beFriend_db.execUpdate(beFriend_sql1);
            beFriend_db.close();

            isBeFriended = true;

          } catch (Exception e)
          {
            isBeFriended = false;
            String joe = e.toString();
            System.out.print(joe);
          }

        return isBeFriended;
    }// </editor-fold>
}
