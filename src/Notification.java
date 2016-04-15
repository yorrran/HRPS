import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Notification class to notify guests if today is their check-in day as a
 * reminder.
 */
public class Notification {
	/**
	 * The email address of sender.
	 */
	private static final String user = "hrpsoodp@gmail.com";

	/**
	 * The email account password of the sender.
	 */
	private static final String password = "cz2002oodp";

	/**
	 * Sends an email to the the recipient email if Internet connection is
	 * available.
	 * 
	 * @param recipient
	 *            The email of recipient.
	 * @param subject
	 *            The title of the email.
	 * @param body
	 *            The body of the email.
	 */
	public static void Email(String recipient, String subject, String body) {
		Properties properties = System.getProperties();
		String host = "smtp.gmail.com";
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", user);
		properties.put("mail.smtp.password", password);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(session);

		System.out.println("Sending email...");

		try {
			message.setFrom(new InternetAddress(user));
			InternetAddress recipientEmail = new InternetAddress(recipient);
			message.addRecipient(Message.RecipientType.TO, recipientEmail);

			message.setSubject(subject);
			message.setText(body);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, user, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}

		System.out.println("Email sent!");
	}
}