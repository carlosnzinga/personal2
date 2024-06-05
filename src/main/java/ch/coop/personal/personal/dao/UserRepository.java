package ch.coop.personal.personal.dao;

import ch.coop.personal.personal.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByNameContaining(String query);  // Define the method here
	User findByUsername(String username);
}
