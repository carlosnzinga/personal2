package ch.coop.personal.personal.service.impl;

import ch.coop.personal.personal.dao.PersonalRepository;
import ch.coop.personal.personal.dao.model.Personal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalServiceImpl {

    @Autowired
    private PersonalRepository personalRepository;

    public void insertPersonal(Personal personal) {
        personalRepository.save(personal);
    }

    public List<Personal> getPersonals() {
        return personalRepository.findAll();
    }

    public void deletePersonal(Long id) {
        personalRepository.deleteById(id);
    }

    public Optional<Personal> getPersonalById(Long id) {
        return personalRepository.findById(id);
    }

    public List<Personal> searchPersonal(String query) {
        return personalRepository.findByNameContaining(query);
    }
}
