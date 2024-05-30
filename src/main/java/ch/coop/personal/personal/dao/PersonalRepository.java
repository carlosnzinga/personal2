package ch.coop.personal.personal.dao;
import ch.coop.personal.personal.dao.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface PersonalRepository extends JpaRepository<Personal, Long> {
    List<Personal>  findByNameContaining(String query);
}
