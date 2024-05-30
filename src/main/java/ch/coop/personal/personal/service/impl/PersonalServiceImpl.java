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



    //gib alle gespeichert Daten zurück
    public List<Personal> getPersonals() {
        //repository.findAll(): Diese Methode ruft alle Datensätze aus der Datenbanktabelle "Personal" ab und gibt sie als Liste von Personalobjekten zurück.
        return repository.findAll().stream()//.stream(): Die stream()-Methode wandelt die Liste der abgerufenen Personalobjekte in einen Stream um. Dies ermöglicht die Verwendung von Stream-Operationen, um die Daten weiter zu verarbeiten.
                .sorted(Comparator.comparing(Personal::getId))//.sorted(Comparator.comparing(Personal::getId)): Mit sorted() wird der Stream der Personalobjekte nach der ID der Personalobjekte sortiert. Comparator.comparing(Personal::getId) ist eine Funktion, die einen Vergleichsoperator für die ID des Personalobjekts bereitstellt und dies als Sortierkriterium verwendet.
                .collect(Collectors.toList());//.collect(Collectors.toList()): Die collect()-Methode sammelt die sortierten Personalobjekte aus dem Stream zurück in eine Liste und gibt diese Liste zurück. Dies bedeutet, dass die Funktion eine Liste von Personalobjekten zurückgibt, die nach der ID sortiert ist.
    }

    //Speichert neu Benutzereingabe in meine Datenbank
    @Override
    public void insertPersonal(Personal personal) {
        repository.save(personal);
    }


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
