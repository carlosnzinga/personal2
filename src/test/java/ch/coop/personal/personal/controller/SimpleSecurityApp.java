package ch.coop.personal.personal.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class SimpleSecurityApp {

    public static void main(String[] args) {
        SpringApplication.run(SimpleSecurityApp.class, args);
    }


    @Controller
    public class WebController {

        @GetMapping("/login")
        public String loginPage() {
            return "login"; // This corresponds to the name of your HTML file (without the .html extension)
        }
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withDefaultPasswordEncoder()
                        .username("cajo3")
                        .password("1234")
                        .roles("USER")
                        .build()
        );
    }
}
