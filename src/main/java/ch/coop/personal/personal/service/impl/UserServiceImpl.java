package ch.coop.personal.personal.service.impl;

import ch.coop.personal.personal.dao.UserRepository;
import ch.coop.personal.personal.dao.model.User;
import ch.coop.personal.personal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final String pepper = "staticPepperValue";  // Ideally, this should be stored securely

	private final String legitPepper = System.getenv("pepper");

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void createUser(User user) {
		String saltedPassword = user.getPassword() + pepper;
		String hashedPassword = passwordEncoder.encode(saltedPassword);
		user.setPassword(hashedPassword);
		userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void updateUser(User user) {
		String saltedPassword = user.getPassword() + pepper;
		String hashedPassword = passwordEncoder.encode(saltedPassword);
		user.setPassword(hashedPassword);
		userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> searchUser(String query) {
		return userRepository.findByNameContaining(query);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
