package ch.coop.personal.personal.controller;


import ch.coop.personal.personal.dao.model.Personal;
import ch.coop.personal.personal.service.impl.PersonalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personal")
public class PersonalRestController {

    /*http://localhost:8080/api/personal/all
    http://localhost:8080/api/personal/get?id=1*/
    @Autowired
    private PersonalServiceImpl personalService;

    @GetMapping("/all")
    public List<Personal> getAllPersonals() {
        return personalService.getPersonals();
    }

    @GetMapping("/get")
    public Optional<Personal> getPersonalById(@RequestParam Long id) {
        return personalService.getPersonalById(id);
    }
}
