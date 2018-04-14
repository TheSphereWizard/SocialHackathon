import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.search.FlagTerm;

public class Email {
	public static final String username = "testemailtestemailtestem@gmail.com";
	public static final String password = "TestEmail";
	public static final String host = "pop.gmail.com";// change accordingly
	public static final String mailStoreType = "pop3s";
	public static void main(String[] args) {
	   sendFromGMail("testing"+System.currentTimeMillis(), "testing testing"+System.currentTimeMillis());
//	   check(host,mailStoreType);
//	   deleteEmail(host, mailStoreType);
   }
   public static ArrayList<String[]> check() {
	   ArrayList<String[]> returnvalue = new ArrayList<String[]>();
      try {
	      Properties properties = new Properties();
	      Session emailSession = Session.getDefaultInstance(properties);
	      Store store = emailSession.getStore("pop3s");
	      store.connect(host, username, password);
	      Folder emailFolder = store.getFolder("INBOX");
	      emailFolder.open(Folder.READ_ONLY);
	      Message[] Messages = emailFolder.getMessages();
	      System.out.println(emailFolder.getMessages().length);
	      for (int i =  0; i<Messages.length; i++) {
	         Message message = Messages[i];
//	         System.out.println("---------------------------------");
//	         System.out.println("Email Number " + (i + 1));
	         System.out.println("Subject: " + message.getSubject());
//	         System.out.println("From: " + message.getFrom()[0]);
	         System.out.println("Text: " + message.getContent().toString());
	         returnvalue.add(new String[]{message.getSubject(),message.getContent().toString()});
	      }
	      System.out.println(emailFolder.getMessages().length);
	      emailFolder.close(false);
	      store.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return returnvalue;
   }
   
   public static void deleteEmail(String host, String storeType){
	   try {
		      Properties properties = new Properties();
		      properties.put("mail.pop3.host", host);
		      properties.put("mail.pop3.port", "995");
		      properties.put("mail.pop3.starttls.enable", "true");
		      Session emailSession = Session.getDefaultInstance(properties);
		      Store store = emailSession.getStore("pop3s");
		      store.connect(host, username, password);
		
		      Folder emailFolder = store.getFolder("INBOX");
		      emailFolder.open(Folder.READ_WRITE);
//		      Message[] Messages = emailFolder.getMessages();
//		      for (int i =  0; i<Messages.length; i++) {
//		         Message message = Messages[i];
//		         System.out.println("---------------------------------");
//		         System.out.println("Email Number " + (i + 1));
//		         System.out.println("Subject: " + message.getSubject());
//		         System.out.println("From: " + message.getFrom()[0]);
//		         System.out.println("Text: " + message.getContent().toString());
//		      }
//		      if(emailFolder.getMessages().length>=1)
		      System.out.println(emailFolder.getMessages().length);
//		    	  emailFolder.getMessage(1).setFlag(Flags.Flag.DELETED, true);
		      emailFolder.close(true);
		      store.close();

	      } catch (Exception e) {
	         e.printStackTrace();
	      }
   }
   public static void sendFromGMail(String subject, String body) {
       String sender=username;
       String[] recipients = new String[]{username};
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