package utilities;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class MonitoringMail
{
	public static void sendMail( String from,String username, String password, String[] to, String subject, String messageBody, String attachmentPath, String attachmentName) throws MessagingException, AddressException
	//public void sendMail(String mailServer, String from, String[] to, String subject, String messageBody) throws MessagingException, AddressException
	{
		final String Userame = username;
		final String Password = password;
				Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); //TLS
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");



		Session session = Session.getInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(Userame, Password);
					}
				});


			try {

				Message message = new MimeMessage(session);
				message.addHeader("X-Priority", "1");
				message.setFrom(new InternetAddress(from));

			//X-Priority values are generally numbers like 1 (for highest priority), 3 (normal) and 5 (lowest).



			InternetAddress[] addressTo = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++)
				addressTo[i] = new InternetAddress(to[i]);
			message.setRecipients(Message.RecipientType .TO, addressTo);
			message.setSubject(subject);


			BodyPart body = new MimeBodyPart();

			// body.setText(messageBody);
			body.setContent(messageBody,"text/html");

			//BodyPart attachment = new MimeBodyPart();
			//DataSource source = new FileDataSource(attachmentPath);
			// attachment.setDataHandler(new DataHandler(source));
			//attachment.setFileName(attachmentName);
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(body);
			// multipart.addBodyPart(attachment);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Sucessfully Sent mail to All Users");

		}
		catch (MessagingException mex)
		{
			mex.printStackTrace();
		}
	}

}
