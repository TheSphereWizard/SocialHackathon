import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

    private static String USER_NAME = "brian.horse@gmail.com";
    private static String PASSWORD =                                                                                   "2fast4u2";
    private static String RECIPIENT = "ayodejiodetola@gmail.com";

    public static void main(String[] args) {
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = {RECIPIENT};
        String subject = "Java send mail example";
        String body = "Welcome to JavaMail!";

        sendFromGMail(from, pass, to, subject, body);
        System.out.println("sent");
    }

    private static void sendFromGMail(String sender, String password, String[] recipients, String subject, String body) {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        String host = "smtp.gmail.com";
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", sender);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(sender));
            for( int i = 0; i < recipients.length; i++ ) {
                message.addRecipient(Message.RecipientType.TO,new InternetAddress(recipients[i]));
            }
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, sender, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}