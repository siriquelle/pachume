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
public class AlterBackgroundImageRepeat {
    //<editor-fold defaultstate="collapsed" desc="updateBackgroundImageRepeat">

    public static boolean run(int userId, String backgroundImageRepeat) {

        boolean successfull = false;

        try
          {
            DataBaseConnection db = new DataBaseConnection();
            db.connect();

            String sql = "UPDATE user SET backgroundImageRepeat = '" + backgroundImageRepeat + "' WHERE userId = " + userId;

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
