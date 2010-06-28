/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.user.settings;

import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class AlterNotifiable {
    //<editor-fold defaultstate="collapsed" desc="updateNotifiable">

    public static boolean run(int userId, boolean notifiable) {

        boolean successfull = false;

        try
          {
            DataBaseConnection db = new DataBaseConnection();
            db.connect();

            String sql = "UPDATE user SET notifiable = " + notifiable + " WHERE userId = " + userId;

            db.execUpdate(sql);
            db.close();

            successfull = true;

          } catch (Exception e)
          {
            successfull = false;
          }

        return successfull;
    }// </editor-fold>
}
