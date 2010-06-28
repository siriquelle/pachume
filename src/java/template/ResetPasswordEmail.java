/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template;


import bean.userBean;
import command.user.GetScreenNameFromEmail;

/**
 *
 * @author Siriquelle
 */
public class ResetPasswordEmail implements Email {

    private String lba = Email.lb1;
    private String lbb = Email.lb2;
    private String styles = Email.style;
//    
    private String subject = "[pachume] ";
    private String body = "";

    private String screenName = "";

    public ResetPasswordEmail(String usersEmail) {

        screenName = GetScreenNameFromEmail.run(usersEmail.trim());
        userBean mailRecipient = new userBean(screenName.trim());

        subject += "password reset request";
        body += styles;
        body += "<div class=\"style\">";
        body += "Hello " + mailRecipient.getFirstName() + ",";
        body += lbb;
        body += "A request has been made to reset the password attributed to your account on <a href=\"http://pachume.com\" />http://pachume.com</a>";
        body += lba;
        body += "all you have to do to join is click on the following link to go reset your password,";
        body += lbb;
        body += "................";
        body += lbb;
        body += "<a href=\"http://pachume.com/passwordResetUUID=\"" + mailRecipient.getApiKey().trim() + "\">http://pachume.com/passwordResetUUID=\"" + mailRecipient.getApiKey().trim() + "</a>";
        body += lbb;
        body += "................";
        body += lbb;
        body += "If you have any problems signing in, please send an email";
        body += "to <a href=\"mailto:help@pachume.com\">help@pachume.com</a> and we will get right on it,";
        body += lbb;
        body += "See you there,";
        body += lbb;
        body += "James Hogan,";
        body += lbb;
        body += "(The lazy guy,)";
        body += lbb;
        body += "<a href=\"http://pachume.com\" >http://pachume.com</a>";
        body += lbb;
        body += lbb;
        body += "</div>";

    }

    public String getSubject() {
        return this.subject;
    }

    public String getBody() {
        return this.body;
    }
}
