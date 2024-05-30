package ch.coop.personal.personal.controller;


import ch.coop.personal.personal.dao.model.Personal;
import ch.coop.personal.personal.service.impl.PersonalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Validated
@Controller
public class PersonalController {

    @Autowired
    private PersonalServiceImpl personalServiceImpl;

    //ruft userForm auf
    @RequestMapping("/userForm")
    public String newPersonal(@ModelAttribute Personal personal) {
        return "userForm";
    }


    //ermöglicht das Erstellen von Benutzer, welche dann in meine Datenbank gespeichert werden und versendet oder ruft dann userInfo auf
    @RequestMapping("/createPerson")
    public String createPerson(@ModelAttribute Personal personal, Model model) {
        personalServiceImpl.insertPersonal(personal);
        model.addAttribute("allPersonals", personalServiceImpl.getPersonals());
        return "userInfo";
    }



    /**
     *
     * @param model
     * @return gibt mir alle gespeicherte Daten in meine Datenbank zurück
     */
    @RequestMapping("/userInfo")
    public String allPersonalList(Model model) {
        model.addAttribute("allPersonals", personalServiceImpl.getPersonals());
        return "userInfo";
    }

    @RequestMapping("/delete")
    public String deletePersonal(@RequestParam("id") Long id) {
        personalServiceImpl.deletePersonal(id);
        return "redirect:/userInfo";
    }

    @RequestMapping("/update")
    public String updatePersonal(@RequestParam("id") Long id, Model model) {
        Personal personalToUpdate = personalServiceImpl.getPersonalById(id).orElse(null);
        if (personalToUpdate != null) {
            // Here you might populate the model with the personalToUpdate data
            model.addAttribute("personal", personalToUpdate);
            return "update"; // Assuming "edit" is the name of your edit template
        } else {
            return "redirect:/userInfo"; // Redirect if personal information is not found
        }
    }

    /**
     * Search for personals based on a query string.
     *
     * @param query The search query.
     * @param model The model to add the search results.
     * @return The view to display the search results.
     */
    @RequestMapping("/search")
    public String searchPersonal(@RequestParam("query") String query, Model model) {
        List<Personal> searchResults = personalServiceImpl.searchPersonal(query);
        model.addAttribute("searchResults", searchResults);
        return "searchResults";
    }
}
