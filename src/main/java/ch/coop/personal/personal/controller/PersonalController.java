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
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@Validated
@Controller
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalServiceImpl personalServiceImpl;

    @RequestMapping("/form")
    public String newPersonal(@ModelAttribute Personal personal) {
        return "userForm";
    }

    @PostMapping("/create")
    public String createPerson(@ModelAttribute Personal personal, Model model) {
        personalServiceImpl.insertPersonal(personal);
        model.addAttribute("allPersonals", personalServiceImpl.getPersonals());
        return "userInfo";
    }

    @RequestMapping("/info")
    public String allPersonalList(Model model) {
        model.addAttribute("allPersonals", personalServiceImpl.getPersonals());
        return "userInfo";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/delete")
    public String deletePersonal(@RequestParam("id") Long id) {
        personalServiceImpl.deletePersonal(id);
        return "redirect:/personal/info";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/update")
    public String updatePersonal(@RequestParam("id") Long id, Model model) {
        Personal personalToUpdate = personalServiceImpl.getPersonalById(id).orElse(null);
        if (personalToUpdate != null) {
            model.addAttribute("personal", personalToUpdate);
            return "update";
        } else {
            return "redirect:/personal/info";
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public String saveUpdatedPersonal(@ModelAttribute Personal personal) {
        personalServiceImpl.updatePersonal(personal);
        return "redirect:/personal/info";
    }

    @RequestMapping("/search")
    public String searchPersonal(@RequestParam("query") String query, Model model) {
        List<Personal> searchResults = personalServiceImpl.searchPersonal(query);
        model.addAttribute("searchResults", searchResults);
        return "searchResults";
    }
}
