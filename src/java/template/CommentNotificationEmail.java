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
public class CommentNotificationEmail implements Email {

    private String lba = Email.lb1;
    private String lbb = Email.lb2;
    private String styles = Email.style;
//    
    private String subject = "[pachume] Comment On: ";
    private String body = "";
    private String pachume = "";
    userBean toUser = null;

    public CommentNotificationEmail(String to, String by, String comment, int pachumeId) {

        pachume = GetPachume.run(pachumeId);
        toUser = new userBean(to);
        subject += pachume;

        body += styles;
        body += "<div class=\"style\">";
        body += "Hi " + toUser.getFirstName() + ",";
        body += lbb;
        body += by + " has left a comment on '" + pachume + "' saying:";

        body += lbb;
        body += "................";
        body += lbb;
        body += "'" + comment + "'";
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
