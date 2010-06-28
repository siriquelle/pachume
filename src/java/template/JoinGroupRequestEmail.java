/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package template;

import bean.userBean;
import command.group.GetGroupName;
import command.user.GetScreenNameFromEmail;



/**
 *
 * @author Siriquelle
 */
public class JoinGroupRequestEmail implements Email {

    private String lba = Email.lb1;
    private String lbb = Email.lb2;
    private String styles = Email.style;
//    
    private String subject = "[pachume] ";
    private String body = "";
    private String groupName = "";
    private String screenName = "";

    public JoinGroupRequestEmail(userBean thisUser, String recipientEmail, String groupId) {

        screenName = GetScreenNameFromEmail.run(recipientEmail.trim());
        userBean mailRecipient = new userBean(screenName.trim());
        groupName = GetGroupName.run(groupId);

        subject += thisUser.getScreenName() + " invites you to join the private channel '" + groupName + "'";
        body += styles;
        body += "<div class=\"style\">";
        body += "Hello " + mailRecipient.getFirstName() + ",";
        body += lbb;
        body += thisUser.getScreenName() + " has created the pivate group called '" + groupName + "',";
        body += lba;
        body += "and all communication in this group is completly private,";
        body += lbb;
        body += "If you would like to join '" + groupName + "', click on the following link:";
        body += lbb;
        body += "................";
        body += lbb;
        body += "<a href=\"http://pachume.com/JoinGroup?groupId=\"" + groupId.trim() + "\">http://pachume.com/JoinGroup?groupId=" + groupId.trim() + "</a>";
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
