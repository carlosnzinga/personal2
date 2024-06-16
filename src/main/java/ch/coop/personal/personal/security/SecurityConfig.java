package ch.coop.personal.personal.security;


import ch.coop.personal.personal.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeRequests(authorizeRequests ->
						authorizeRequests
								.requestMatchers("/user/register", "/login","css/styles.css","/personal/form", "/personal/create", "/personal/info", "/personal/update", "/personal/delete", "/user/profile").permitAll()
								.requestMatchers("/user/profile").hasRole("USER")
								.requestMatchers("/admin/**").hasRole("ADMIN")
								.anyRequest().authenticated()
				)
				.formLogin(formLogin ->
						formLogin
								.loginPage("/login")
								.defaultSuccessUrl("/user/profile", true)
								.failureUrl("/login?error=true")
								.permitAll()
				)
				.logout(logout ->
						logout
								.permitAll()
				);
		return http.build();
	}
}
