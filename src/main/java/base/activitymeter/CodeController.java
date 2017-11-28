package base.activitymeter;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RequestID")

public class CodeController {
	@PostMapping
	public void send(@RequestBody String mail) throws MessagingException {
		long min = 100000;
		long max = 999999;
		long id = -1;
		boolean unique = false;

		while (!unique) {
			id = (long) (Math.random() * (max - min)) + min;
			unique = !ActivityController.getActivityRepository().exists((long) id);
		}

		ActivityController.getActivityRepository().save(new Activity(id, "", "", ""));
		Session session = getSession();
		sendMail(session, mail, id);

	}

	private Session getSession() {
		String user = "activitymeter1@gmail.com";
		String password = "sweitg2b4";
		final Properties props = new Properties();

		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");

		return Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("mail.pop3.user"),
						props.getProperty("mail.pop3.password"));
			}
		});
	}

	public static void sendMail(Session session, String recipient, long id) throws MessagingException {
		Message msg = new MimeMessage(session);

		InternetAddress addressTo = new InternetAddress(recipient);
		msg.setRecipient(Message.RecipientType.TO, addressTo);

		msg.setSubject("ActivityMeter ID");
		msg.setContent("Hallo, der angeforderte Code lautet: " + id, "text/plain");
		Transport.send(msg);
	}

}
