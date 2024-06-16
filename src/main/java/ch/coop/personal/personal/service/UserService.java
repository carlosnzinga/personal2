package ch.coop.personal.personal.service;

import ch.coop.personal.personal.dao.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
	List<User> getAllUsers();
	void createUser(User user);
	Optional<User> getUserById(Long id);
	void updateUser(User user);
	void deleteUser(Long id);
	List<User> searchUser(String query);
	User findByUsername(String username);
}
