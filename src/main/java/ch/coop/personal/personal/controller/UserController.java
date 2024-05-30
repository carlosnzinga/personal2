package ch.coop.personal.personal.controller;

import ch.coop.personal.personal.dao.model.Personal;
import ch.coop.personal.personal.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private PersonalService personalService;

    @PostMapping("/update")
    public String updateUser(@ModelAttribute Personal updatedPersonal) {
        personalService.updatePersonal(updatedPersonal);
        return "redirect:/userInfo"; // Redirect to the user list page
    }
}
