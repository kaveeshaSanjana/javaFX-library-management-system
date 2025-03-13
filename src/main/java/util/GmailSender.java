
package util;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class GmailSender {

    public static void sendEmail(String subject,String msg) {
        String senderEmail = "";
        String senderPassword = "";
        String recipientEmail = "";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
