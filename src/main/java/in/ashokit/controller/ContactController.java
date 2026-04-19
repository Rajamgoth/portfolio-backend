package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.dto.ContactRequest;
import in.ashokit.entity.Contact;
import in.ashokit.repo.ContactRepository;
import in.ashokit.service.EmailService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/contact")
public class ContactController {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ContactRepository contactRepository;

	@PostMapping
	public String sendMessage(@RequestBody ContactRequest request) {
		emailService.sendEmail(request);
		return "Message sent successfully";
	}
	
	@GetMapping("/messages")
	public List<Contact> getAllMessages() {
	    return emailService.getAllMessages();
	}
	
	@DeleteMapping("/messages/{id}")
	public ResponseEntity<String> deleteMessage(@PathVariable Long id) {
	    contactRepository.deleteById(id);
	    return ResponseEntity.ok("Deleted successfully");
	}
}
