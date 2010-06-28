/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template;

import bean.userBean;
import command.pachume.GetPachume;

/**
 *
 * @author Siriquelle
 */
public class Email_ErrorNotification implements Email {

    private String lba = Email.lb1;
    private String lbb = Email.lb2;
    private String styles = Email.style;
//    
    private String subject = "[pachume] Error On: ";
    private String body = "";
    userBean toUser = null;

    public Email_ErrorNotification(String error, int pachumeId) {
        
        subject += GetPachume.run(pachumeId);

        body += styles;
        body += "<div class=\"style\">";
        body += "Hi James,";
        body += lbb;
        body += "An error has occured:";
        body += lbb;
        body += "................";
        body += lbb;
        body += "'" + error + "'";
        body += lbb;
        body += "................";
        body += lbb;
        body += "The following link will take you to the pachume:";
        body += lbb;
        body += "................";
        body += lbb;
        body += "<a href=\"http://pachume.com/comment/" + pachumeId + "\" >http://pachume.com/comment/" + pachumeId + "</a>";
        body += lbb;
        body += "................";
        body += lbb;
        body += "If you have any problems signing in, please send an email";
        body += lba;
        body += "to <a href=\"mailto:help@pachume.com\">help@pachume.com</a> and we will get right on it.";
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
        return subject;
    }

    public String getBody() {
        return body;
    }
}
