package ch.coop.personal.personal.security;

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
        SpringApplication.run(ch.coop.personal.personal.controller.SimpleSecurityApp.class, args);
    }


    @Controller
    public class WebController {

        @GetMapping("/login")
        public String loginPage() {
            return "login";
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
