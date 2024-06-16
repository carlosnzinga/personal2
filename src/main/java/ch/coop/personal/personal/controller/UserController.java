package ch.coop.personal.personal.controller;

import ch.coop.personal.personal.dao.model.User;
import ch.coop.personal.personal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String showUserProfile(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/admin/form")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/admin/create")
    public String createUser(@ModelAttribute User user, Model model) {
        userService.createUser(user);
        model.addAttribute("allUsers", userService.getAllUsers());
        return "userInfo";
    }

    @GetMapping("/admin/info")
    public String allUsersList(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "userInfo";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/user/admin/info";
    }

    @PostMapping("/admin/update")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        User userToUpdate = userService.getUserById(id).orElse(null);
        if (userToUpdate != null) {
            model.addAttribute("user", userToUpdate);
            return "update";
        } else {
            return "redirect:/user/admin/info";
        }
    }

    @PostMapping("/admin/search")
    public String searchUser(@RequestParam("query") String query, Model model) {
        List<User> searchResults = userService.searchUser(query);
        model.addAttribute("searchResults", searchResults);
        return "searchResults";
    }


}
