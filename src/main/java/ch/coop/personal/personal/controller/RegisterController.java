package ch.coop.personal.personal.controller;

import ch.coop.personal.personal.dao.UserRepository;
import ch.coop.personal.personal.dao.model.User;
import ch.coop.personal.personal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.ValidationException;

@Controller
@RequestMapping("/user")
public class RegisterController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String showRegistrationForm() {
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("name") String name, @RequestParam("role") String role, Model model) {
		if (!isValidPassword(password)) {
			model.addAttribute("error", "Passwort muss mindestens 8 Zeichen lang sein und Groß- und Kleinbuchstaben, Zahlen und Sonderzeichen enthalten.");
			return "register";
		}

		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setName(name);
		user.setRole(role);
		userRepository.save(user);
		return "redirect:/login";
	}

	private boolean isValidPassword(String password) {
		return password.length() >= 8 &&
				password.matches(".*[A-Z].*") &&
				password.matches(".*[a-z].*") &&
				password.matches(".*\\d.*") &&
				password.matches(".*[!@#$%^&*()].*");
	}
}
