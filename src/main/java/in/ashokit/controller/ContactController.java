package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.dto.ContactRequest;
import in.ashokit.entity.Contact;
import in.ashokit.service.EmailService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/contact")
public class ContactController {

	@Autowired
	private EmailService emailService;

	@PostMapping
	public String sendMessage(@RequestBody ContactRequest request) {
		emailService.sendEmail(request);
		return "Message sent successfully";
	}
	
	@GetMapping("/messages")
	public List<Contact> getAllMessages() {
	    return emailService.getAllMessages();
	}
}
