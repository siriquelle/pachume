/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command.verification;

import command.conversion.MakeCompatable;
import command.user.GetUsersContacts;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DataBaseConnection;

import model.User;


/**
 *
 * @author Siriquelle
 */
public class VerifyLogin {
    //<editor-fold defaultstate="collapsed" desc="validateLogin">
    public static String run(String screenName, String userPassword, HttpServletRequest request, HttpServletResponse response, String saveSession) {
    
        screenName = MakeCompatable.run(screenName);
        userPassword = MakeCompatable.run(userPassword);
        
        String goTo = "/login.jsp?";
        try
          {
            //check screenName
            DataBaseConnection db = new DataBaseConnection();
            db.connect();

            String sql = "SELECT screenName FROM user WHERE screenName = '" + screenName + "';";
            ResultSet rs = db.execSQL(sql);

            //if screenName correct
            if (rs.next())
              {
                String checkPassword_sql = "SELECT userPassword FROM user WHERE userPassword = '" + userPassword + "';";
                rs = db.execSQL(checkPassword_sql);

                //if password correct
                if (rs.next())
                  {
                    //load thisUser
                    String loadUser_sql = "SELECT * FROM user WHERE screenName = '" + screenName + "' AND userPassword = '" + userPassword + "';";
                    rs = db.execSQL(loadUser_sql);

                    //save thisUser
                    if (rs.next())
                      {
                        HttpSession session = request.getSession();
                        User thisUser = new User();

                        thisUser.setActivationCode(rs.getInt("activationCode"));
                        thisUser.setActive(rs.getBoolean("active"));
                        thisUser.setAvatarLocation(rs.getString("avatarLocation"));
                        thisUser.setPachumeImageLocation(rs.getString("pachumeImageLocation"));
                        thisUser.setContactImageLocation(rs.getString("contactImageLocation"));

                        thisUser.setBackgroundColor(rs.getString("backgroundColor"));
                        thisUser.setBackgroundImageLocation(rs.getString("backgroundImageLocation"));
                        thisUser.setBackgroundImageRepeat(rs.getString("backgroundImageRepeat"));
                        thisUser.setCollege(rs.getString("college"));
                        thisUser.setEmail(rs.getString("email"));
                        thisUser.setFeedLocation(rs.getString("feedLocation"));
                        thisUser.setFirstName(rs.getString("firstName"));
                        thisUser.setLastName(rs.getString("lastName"));
                        thisUser.setLocation("");
                        thisUser.setNotifiable(rs.getBoolean("notifiable"));
                        thisUser.setPrivatized(rs.getBoolean("privatized"));
                        thisUser.setRole(rs.getString("role"));
                        thisUser.setScreenName(rs.getString("screenName"));
                        thisUser.setUserId(rs.getInt("userId"));
                        thisUser.setUserPassword(rs.getString("userPassword"));
                        thisUser.setContactList(GetUsersContacts.run(rs.getInt("userId")));
                        thisUser.setLoggedIn(true);


                        if (saveSession.equals("true"))
                          {

                            RemoveAnyCookies.run(request, response);
                            session.setMaxInactiveInterval(86400);
                            Cookie username = new Cookie("usr" + request.getSession().getId(), screenName);
                            Cookie password = new Cookie("pas" + request.getSession().getId(), userPassword);
                            username.setMaxAge(604800);
                            password.setMaxAge(604800);
                            response.addCookie(username);
                            response.addCookie(password);
                          }
                        session.setAttribute("thisUser", thisUser);
                        goTo = "/";
                      }
                  } else
                  {
                    goTo += "message=invalid username/password combination";
                  }
              } else
              {
                goTo += "message=invalid username/password combination";
              }
            db.close();
          } catch (Exception e)
          {
            System.out.print(e);
          }

        return goTo;
    }// </editor-fold>
}
