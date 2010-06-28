/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

/**
 *
 * @author Siriquelle
 */
public class LoginBean {

    private String firstName = "";
    private String LastName = "";
    private String ScreenName = "";
    private String email = "";

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getScreenName() {
        return ScreenName;
    }

    public void setScreenName(String ScreenName) {
        this.ScreenName = ScreenName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    
}
