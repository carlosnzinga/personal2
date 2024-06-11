package ch.coop.personal.personal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {

		// TODO if session != null then return "home" else login
		return "login";
	}
}
