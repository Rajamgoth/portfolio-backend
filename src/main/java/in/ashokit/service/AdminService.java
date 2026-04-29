package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Admin;
import in.ashokit.repo.AdminRepository;
import jakarta.annotation.PostConstruct;

@Service
public class AdminService {

	@Autowired
	private AdminRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void savedAdmin(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		repo.save(admin);
	}

	@PostConstruct
	public void init() {

		Admin admin = new Admin();
		admin.setEmail("admin@gmail.com");
		admin.setPassword("admin123"); // plain

		savedAdmin(admin); // 🔥 THIS CALLS ENCODER

		System.out.println("Admin saved with encrypted password");
	}
}
