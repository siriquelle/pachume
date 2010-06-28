/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.group;

import model.DataBaseConnection;
import org.safehaus.uuid.UUIDGenerator;

/**
 *
 * @author Siriquelle
 */
public class ResetGroupId {
    //<editor-fold defaultstate="collapsed" desc="resetGroupId">

    public static boolean run(String groupId) {

        boolean isBackgroundColorUpdated = false;
        DataBaseConnection db = null;
        String sql = null;
        UUIDGenerator joe = UUIDGenerator.getInstance();
        String uuid = joe.generateRandomBasedUUID().toString();
        String newGroupId = uuid;

        try
          {
            db = new DataBaseConnection();
            db.connect();

            sql = "UPDATE groups SET groupId = '" + newGroupId + "' WHERE groupId = '" + groupId + "';";
            db.execUpdate(sql);

            sql = "UPDATE groupsmember SET groupId = '" + newGroupId + "' WHERE groupId = '" + groupId + "';";
            db.execUpdate(sql);

            sql = "UPDATE pachume SET groupId = '" + newGroupId + "' WHERE groupId = '" + groupId + "';";
            db.execUpdate(sql);

            db.close();

            isBackgroundColorUpdated = true;

          } catch (Exception e)
          {
            isBackgroundColorUpdated = false;
          }

        return isBackgroundColorUpdated;
    }// </editor-fold>
}
