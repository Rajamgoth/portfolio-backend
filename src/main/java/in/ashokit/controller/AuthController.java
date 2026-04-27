package in.ashokit.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.dto.LoginRequest;
import in.ashokit.entity.Admin;
import in.ashokit.repo.AdminRepository;
import in.ashokit.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {

		Optional<Admin> adminOpt = adminRepo.findByEmail(request.getEmail());

		if (adminOpt.isPresent()) {
			Admin admin = adminOpt.get();

			if (admin.getPassword().equals(request.getPassword())) {

				String token = jwtUtil.generateToken(admin.getEmail());

				return ResponseEntity.ok(Map.of("token", token));
			}
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	}
}
