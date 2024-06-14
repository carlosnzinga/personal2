package ch.coop.personal.personal.service;



import ch.coop.personal.personal.dao.model.Personal;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PersonalService {


    Optional<Personal> getPersonalById(long id);

    public void insertPersonal(Personal personal);

    public void deletePersonal(Long id);

    public List<Personal> getPersonals();

    public void updatePersonal(Personal personal);



}
