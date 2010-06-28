/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.group;

import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class UpdateGroupBackgroundColor {
    //<editor-fold defaultstate="collapsed" desc="updateGroupBackgroundColor">

    public static boolean run(String groupName, String backgroundColor) {

        boolean isBackgroundColorUpdated = false;

        try
          {

            DataBaseConnection updateBackgroundColor_db = new DataBaseConnection();
            updateBackgroundColor_db.connect();

            String updateBackgroundColor_sql = "UPDATE groups SET backgroundColor = '" + backgroundColor + "' WHERE groupName = '" + groupName + "';";

            updateBackgroundColor_db.execUpdate(updateBackgroundColor_sql);
            updateBackgroundColor_db.close();

            isBackgroundColorUpdated = true;

          } catch (Exception e)
          {
            isBackgroundColorUpdated = false;
          }

        return isBackgroundColorUpdated;
    }// </editor-fold>
}
