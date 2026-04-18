package in.ashokit.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import in.ashokit.dto.ContactRequest;


@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;


	public void sendEmail(ContactRequest request) {

		

		// send email
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("amgothk50@gmail.com");
		message.setSubject("New Contact Form Message from" + request.getName());
		message.setText(

				"Name: " + request.getName() + "\n" + "Email: " + request.getEmail() + "\n" + "Message: "
						+ request.getMessage() + "\n");
		mailSender.send(message);

		/*
		 * // Auto Replay sendAutoReply(request);
		 */

	}

	/*
	 * // 🔥 Auto-reply method public void sendAutoReply(ContactRequest request) {
	 * SimpleMailMessage reply = new SimpleMailMessage();
	 * 
	 * reply.setTo(request.getEmail()); reply.setSubject("Thanks for contacting!");
	 * 
	 * reply.setText("Hi " + request.getName() + ",\n\n" +
	 * "Thank you for contacting me. I have received your message and will get back to you soon.\n\n"
	 * + "Best Regards,\nRajkumar");
	 * 
	 * mailSender.send(reply); }
	 */
}
