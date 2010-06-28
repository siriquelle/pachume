/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.user;

import command.conversion.ConvertToSHA1;
import command.conversion.MakeCompatable;
import java.io.UnsupportedEncodingException;
import model.DataBaseConnection;

/**
 *
 * @author Siriquelle
 */
public class MakeUser {
   //<editor-fold defaultstate="collapsed" desc="createUser">
    public static boolean run(String firstName, String lastName, String screenName, String email, String userPassword, String role, String backgroundColor, String backgroundImageLocation, String avatarLocation, String feedLocation, String location, String college, boolean active, String pachumeImageLocation, String contactImageLocation, boolean notifiable) {

        boolean userCreated = false;

        firstName = MakeCompatable.run(firstName);
        lastName = MakeCompatable.run(lastName);
        screenName = MakeCompatable.run(screenName);
        try
          {
            userPassword = ConvertToSHA1.run(userPassword);
          } catch (Exception w)
          {
            System.out.print(w);
            return false;
          }
        try
          {
            DataBaseConnection db;
            db = new DataBaseConnection();

            db.connect();
            String sql = "INSERT INTO user(firstName, lastName, screenName, email, userPassword, role, backgroundColor, backgroundImageLocation, avatarLocation, feedLocation, location, college, active, backgroundImageRepeat, pachumeImageLocation, contactImageLocation, notifiable)" +
                    " VALUES ('" + firstName + "','" + lastName + "','" + screenName + "','" + email + "','" + userPassword + "' ,'" + role + "' ,'" + backgroundColor + "' ,'" + backgroundImageLocation + "' ,'" + avatarLocation + "','" + feedLocation + "','" + location + "' ,'" + college + "', " + active + " ,'repeat','" + pachumeImageLocation + "','" + contactImageLocation + "', " + notifiable + ");";
            db.execUpdate(sql);
            db.close();
            userCreated = true;
          } catch (Exception e)
          {
            System.out.print(e.toString());
            userCreated = false;
          }

        return userCreated;

    }// </editor-fold>
}
