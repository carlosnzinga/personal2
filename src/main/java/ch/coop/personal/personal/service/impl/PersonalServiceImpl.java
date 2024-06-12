package ch.coop.personal.personal.service.impl;

import ch.coop.personal.personal.dao.PersonalRepository;
import ch.coop.personal.personal.dao.model.Personal;
import ch.coop.personal.personal.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    private PersonalRepository repository;

    // Methode getPersonals bleibt wie bisher
    public List<Personal> getPersonals() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Personal::getId))
                .collect(Collectors.toList());
    }

    @Override
    public void insertPersonal(Personal personal) {
        repository.save(personal);
    }

    @Override
    public Optional<Personal> getPersonalById(long id) {
        return repository.findAll().stream()
                .filter(personal -> personal.getId() == id)
                .findAny();
    }

    @Override
    public void deletePersonal(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void updatePersonal(Personal personal) {
        repository.save(personal);
    }

    public List<Personal> searchPersonal(String query) {
        return repository.findByNameContaining(query);
    }

}
