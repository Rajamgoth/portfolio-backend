package in.ashokit.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import in.ashokit.dto.ContactRequest;
import in.ashokit.entity.Contact;
import in.ashokit.repo.ContactRepository;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ContactRepository contactRepository;

	public void sendEmail(ContactRequest request) {

		Contact contact = new Contact();
		contact.setName(request.getName());
		contact.setEmail(request.getEmail());
		contact.setMessage(request.getMessage());
		contact.setCreatedAt(LocalDateTime.now());

		Contact saved = contactRepository.save(contact);
		System.out.println("Saved ID: " + saved.getId());

		System.out.println("Email sent to admin");

		// send email
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("amgothk50@gmail.com");
		message.setSubject("New Contact Form Message from" + request.getName());
		message.setText(

				"Name: " + request.getName() + "\n" + "Email: " + request.getEmail() + "\n" + "Message: "
						+ request.getMessage() + "\n");
		mailSender.send(message);

		// Auto Replay
		sendAutoReply(request);

	}

	// 🔥 Auto-reply method
	public void sendAutoReply(ContactRequest request) {
		SimpleMailMessage autoReply = new SimpleMailMessage();

		autoReply.setTo(request.getEmail());
		autoReply.setSubject("Thanks for contacting!");

		autoReply.setText("Hi " + request.getName() + ",\n\n"
				+ "Thank you for contacting me. I have received your message and will get back to you soon.\n\n"
				+ "Best Regards,\nRajkumar");

		mailSender.send(autoReply);
	}
	
	public Page<Contact> getMessages(Pageable pageable) {
	    return contactRepository.findAll(pageable);
	}

}
