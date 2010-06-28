/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.user.settings;

import model.DataBaseConnection;
import org.safehaus.uuid.UUIDGenerator;

/**
 *
 * @author Siriquelle
 */
public class SetUsersApiKey {
    //<editor-fold defaultstate="collapsed" desc="setUsersApiKey">

    public static boolean run(int userId) {

        boolean apiKeySet = false;

        try
          {
            UUIDGenerator joe = UUIDGenerator.getInstance();
            String apiKey = joe.generateRandomBasedUUID().toString();

            DataBaseConnection db = new DataBaseConnection();
            db.connect();

            String sql = "UPDATE user SET apiKey = '" + apiKey + "' WHERE userId = " + userId;

            db.execUpdate(sql);
            db.close();

            apiKeySet = true;
          } catch (Exception e)
          {
            System.out.print(e.toString());
            apiKeySet = false;
          }

        return apiKeySet;

    }// </editor-fold>
}
