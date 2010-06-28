/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package command.notification;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Siriquelle
 */
public class SendEmail {
    public static boolean run(String addressedTo, String subject, String content) {

        String fromAddress = "pachume <pachume@pachume.com>";
        String recipients = addressedTo.trim();
        String contentType = "text/html";
        //remote: String smtpHost = "mail.pachume.com";
        //local:
        String smtpHost = "localhost";
        int smtpPort = 25;
        String username = "pachume+pachume.com";
        String password = "smithfield26";

        try
        {
            Properties props = System.getProperties();

            Session session = Session.getDefaultInstance(props);

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients, false));

            message.setSubject(subject);
            message.setContent(content, contentType);
            message.setSentDate(new Date());

            Transport transport = session.getTransport("smtp");
            transport.connect(smtpHost, smtpPort, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException messagingException)
        {
            System.out.print(messagingException);
        } catch (Exception e)
        {
            System.out.print(e);
        }

        return true;
    }
}
