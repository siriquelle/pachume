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
public class UpdateGroupBackgroundImageLocation {
    //<editor-fold defaultstate="collapsed" desc="updateGroupBackgroundImageLocation">

    public static boolean run(String groupId, String backgroundImageLocation) {

        boolean isBackgroundImageLocationUpdated = false;

        try
          {

            DataBaseConnection updateBackgroundImageLocation_db = new DataBaseConnection();
            updateBackgroundImageLocation_db.connect();

            String updateBackgroundImageLocation_sql = "UPDATE groups SET backgroundImageLocation = '" + backgroundImageLocation + "' WHERE groupId = '" + groupId + "';";

            updateBackgroundImageLocation_db.execUpdate(updateBackgroundImageLocation_sql);
            updateBackgroundImageLocation_db.close();

            isBackgroundImageLocationUpdated = true;

          } catch (Exception e)
          {
            isBackgroundImageLocationUpdated = false;
          }


        return isBackgroundImageLocationUpdated;
    }// </editor-fold>
}
