/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import command.conversion.MakeCompatable;
import command.conversion.Md5Hex;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.http.*;
import java.util.Vector;

/**
 *
 * @author Siriquelle
 */
public class User {

    private int userId = -1;
    private int activationCode = -1;
    private boolean active = false;
    private String backgroundImageRepeat = "repeat";
    private boolean notifiable = false;
    private boolean privatized = false;
    private boolean loggedIn = false;
    private String screenName = null;
    private String firstName = null;
    private String lastName = null;
    private String userPassword = null;
    private String verifyPassword = null;
    private String email = null;
    private String role = null;
    private String backgroundColor = null;
    private String avatarLocation = null;
    private String pachumeImageLocation = null;
    private String contactImageLocation = null;
    private String backgroundImageLocation = null;
    private String location = null;
    private String feedLocation = null;
    private String college = null;
    private Vector contactList = null;

    public HttpSession saveUser(HttpServletRequest request) {

        HttpSession session = request.getSession();
        User thisUser = new User();

        String storeFirstName = null;
        if (request.getParameter("firstName") != null)
          {
            storeFirstName = request.getParameter("firstName");
            storeFirstName = MakeCompatable.run(storeFirstName);

            thisUser.setFirstName(storeFirstName);
          }

        String storeLastName = null;
        if (request.getParameter("lastName") != null)
          {
            storeLastName = request.getParameter("lastName");
            storeLastName = MakeCompatable.run(storeLastName);
            thisUser.setLastName(storeLastName);
          }

        String storeScreenName = null;
        if (request.getParameter("screenName") != null)
          {
            storeScreenName = request.getParameter("screenName");
            storeScreenName = MakeCompatable.run(storeScreenName);
            thisUser.setScreenName(storeScreenName);
          }

        String storeEmail = null;
        if (request.getParameter("email") != null)
          {
            storeEmail = request.getParameter("email");
            storeEmail = MakeCompatable.run(storeEmail);
            thisUser.setEmail(storeEmail);
          }

        String storeUserPassword = null;
        if (request.getParameter("userPassword") != null)
          {
            storeUserPassword = request.getParameter("userPassword");
            storeUserPassword = MakeCompatable.run(storeUserPassword);
            thisUser.setUserPassword(storeUserPassword);
          }

        String storeVerifyPassword = null;
        if (request.getParameter("verifyPassword") != null)
          {
            storeVerifyPassword = request.getParameter("verifyPassword");
            storeVerifyPassword = MakeCompatable.run(storeVerifyPassword);
            thisUser.setVerifyPassword(storeVerifyPassword);
          }


        String storeRole = "joePublic";
        thisUser.setRole(storeRole);

        String storeBackgroundColor = "#cc3355;";
        thisUser.setBackgroundColor(storeBackgroundColor);

        String nullGravatarImage = "http://www.gravatar.com/avatar/1";
        String gravatarURL = "http://www.gravatar.com/avatar/";
        String md5 = Md5Hex.run(storeEmail);
        gravatarURL += md5;

        String storeBackgroundImageLocation = "/media/images/bg.png";
        thisUser.setBackgroundImageLocation(storeBackgroundImageLocation);

        String defaultGravatar = "";
        String userGravatar = "";

        try
          {
            URL url = null;
            URLConnection con = null;
            BufferedReader br = null;

            url = new URL(nullGravatarImage);
            con = url.openConnection();
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            defaultGravatar = br.readLine();

            url = new URL(gravatarURL);
            con = url.openConnection();
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            userGravatar = br.readLine();

            br.close();

          } catch (Exception e)
          {
            System.out.print(e);
          }

        String storeAvatarLocation = "/media/images/me.png";
        String storePachumeImageLocation = "/media/images/mepachume.png";
        String storeContactImageLocation = "/media/images/mecontact.png";

        if (!defaultGravatar.equals(userGravatar))
          {
            storeAvatarLocation = gravatarURL += "?s=102";
            storePachumeImageLocation = gravatarURL += "?s=40";
            storeContactImageLocation = gravatarURL += "?s=27";
          }

        thisUser.setAvatarLocation(storeAvatarLocation);
        thisUser.setPachumeImageLocation(storePachumeImageLocation);
        thisUser.setContactImageLocation(storeContactImageLocation);

        String storeLocation = "";
        thisUser.setLocation(storeLocation);

        String storeFeedLocation = "";
        thisUser.setFeedLocation(storeFeedLocation);

        String storeCollege = "Tipperary Institute";
        thisUser.setCollege(storeCollege);

        session.setAttribute("thisUser", thisUser);

        return session;
    }

    public void setActivationCode(int activationCode) {
        this.activationCode = activationCode;
    }

    public void setAvatarLocation(String avatarLocation) {
        this.avatarLocation = avatarLocation;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setBackgroundImageLocation(String backgroundImageLocation) {
        this.backgroundImageLocation = backgroundImageLocation;
    }

    public void setBackgroundImageRepeat(String backgroundImageRepeat) {
        this.backgroundImageRepeat = backgroundImageRepeat;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFeedLocation(String feedLocation) {
        this.feedLocation = feedLocation;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setNotifiable(boolean notifiable) {
        this.notifiable = notifiable;
    }

    public void setPrivatized(boolean privatized) {
        this.privatized = privatized;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public void setContactList(Vector contactList) {
        this.contactList = contactList;
    }

    public void setContactImageLocation(String contactImageLocation) {
        this.contactImageLocation = contactImageLocation;
    }

    public void setPachumeImageLocation(String pachumeImageLocation) {
        this.pachumeImageLocation = pachumeImageLocation;
    }

    public int getActivationCode() {
        return activationCode;
    }

    public String getAvatarLocation() {
        return avatarLocation;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getBackgroundImageLocation() {
        return backgroundImageLocation;
    }

    public String getCollege() {
        return college;
    }

    public String getEmail() {
        return email;
    }

    public String getFeedLocation() {
        return feedLocation;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLocation() {
        return location;
    }

    public String getRole() {
        return role;
    }

    public String getScreenName() {
        return screenName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public boolean isActive() {
        return active;
    }

    public String isBackgroundImageRepeat() {
        return backgroundImageRepeat;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean isNotifiable() {
        return notifiable;
    }

    public boolean isPrivatized() {
        return privatized;
    }

    public Vector getContactList() {
        return contactList;
    }

    public String getContactImageLocation() {
        return contactImageLocation;
    }

    public String getPachumeImageLocation() {
        return pachumeImageLocation;
    }
}
