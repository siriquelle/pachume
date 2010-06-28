/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import command.user.GetUsersContacts;
import command.user.GetUserId;

import java.util.Vector;


import model.DataBaseConnection;
import java.sql.ResultSet;

/**
 *
 * @author Siriquelle
 */
public class userBean {

    private int userId = -1;
    private String backgroundImageRepeat = "repeat";
    private boolean active = false;
    private boolean privatized = false;
    private boolean loggedIn = false;
    private String screenName = null;
    private String firstName = null;
    private String lastName = null;
    private String role = null;
    private String backgroundColor = null;
    private String avatarLocation = null;
    private String backgroundImageLocation = null;
    private String location = null;
    private String feedLocation = null;
    private Vector contactList = null;
    private String pachumeImageLocation = null;
    private String contactImageLocation = null;
    private String apiKey = null;
    private String email = null;
    private boolean notifiable = false;
    
    public userBean(String user) {
        try
        {
            this.userId = GetUserId.run(user);
            contactList = GetUsersContacts.run(this.userId);

            DataBaseConnection userBean_db = new DataBaseConnection();
            userBean_db.connect();
            String userBean_sql = "SELECT * FROM user WHERE userId = " + this.userId + ";";
            ResultSet userBean_rs = userBean_db.execSQL(userBean_sql);
            if (userBean_rs.next())
            {

                this.privatized = userBean_rs.getBoolean("privatized");
                this.loggedIn = userBean_rs.getBoolean("loggedIn");
                this.active = userBean_rs.getBoolean("active");
                this.backgroundImageRepeat = userBean_rs.getString("backgroundImageRepeat");
                this.screenName = userBean_rs.getString("screenName");
                this.firstName = userBean_rs.getString("firstName");
                this.lastName = userBean_rs.getString("lastName");
                this.role = userBean_rs.getString("role");
                this.backgroundColor = userBean_rs.getString("backgroundColor");
                this.avatarLocation = userBean_rs.getString("avatarLocation");
                this.backgroundImageLocation = userBean_rs.getString("backgroundImageLocation");
                this.location = userBean_rs.getString("location");
                this.feedLocation = userBean_rs.getString("feedLocation");
                this.pachumeImageLocation = userBean_rs.getString("pachumeImageLocation");
                this.contactImageLocation = userBean_rs.getString("contactImageLocation");
                this.apiKey = userBean_rs.getString("apiKey");
                this.email = userBean_rs.getString("email");
                this.notifiable = userBean_rs.getBoolean("notifiable");
            }
            userBean_db.close();

        } catch (Exception e)
        {
            System.out.print(e);
        }
    }

    public String getAvatarLocation() {
        return this.avatarLocation;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public String getBackgroundImageLocation() {
        return this.backgroundImageLocation;
    }

    public Vector getContactList() {
        return this.contactList;
    }

    public String getFeedLocation() {
        return this.feedLocation;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getLocation() {

        if ("".equals(this.location))
        {
            this.location = "location unknown";
        }

        return this.location;
    }

    public String getRole() {
        return this.role;
    }

    public String getScreenName() {
        return this.screenName;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getPachumeImageLocation() {
        return this.pachumeImageLocation;
    }

    public String getContactImageLocation() {
        return this.contactImageLocation;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getEmail() {
        return email;
    }
    

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public boolean isPrivatized() {
        return this.privatized;
    }

    public String isBackgroundImageRepeat() {
        return this.backgroundImageRepeat;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean isNotifiable() {
        return notifiable;
    }
    
}
