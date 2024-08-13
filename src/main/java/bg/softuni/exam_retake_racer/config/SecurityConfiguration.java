package bg.softuni.exam_retake_racer.config;

import bg.softuni.exam_retake_racer.model.enums.Role;
import bg.softuni.exam_retake_racer.repository.UserRepository;
import bg.softuni.exam_retake_racer.service.impl.RacerUserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(httpSecurityCsrfConfigurer -> {
                    HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
                    repository.setSessionAttributeName("_csrf");
                    httpSecurityCsrfConfigurer.csrfTokenRepository(repository);
                })
                .authorizeHttpRequests(
                        // Define which URLs are visible by which users
                        authorizeRequests -> authorizeRequests
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                // Allow anyone to see the home page, registration page and login form
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/users/login", "/users/register").permitAll()
                                .requestMatchers("/users/profile", "/users/edit").authenticated()
                                .requestMatchers("/races", "/races/add").permitAll()
                                .requestMatchers("/tracks", "/tracks/add").permitAll()
                                .requestMatchers("/organizers", "/organizers/add").permitAll()
                                .requestMatchers("/users").hasRole(Role.ADMIN.name())
                                // All other requests are authenticated
                                .anyRequest().authenticated()
                ).formLogin(
                        formLogin -> formLogin
                                // redirect here when we access something which is not allowed
                                // also this is the page where we perform login
                                .loginPage("/users/login")
                                //The names of the input fields
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/")
                                .failureForwardUrl("/users/login-error")
                ).logout(
                        logout -> logout
                                // the URL where we should POST something in order to perform the logout
                                .logoutUrl("/users/logout")
                                // where to go when logged out?
                                .logoutSuccessUrl("/")
                                // invalidate the HTTP session
                                .invalidateHttpSession(true)
                )
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new RacerUserDetailsServiceImpl(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }
}
