package com.softuni.pathfinder.config;

import com.softuni.pathfinder.repository.UserRepository;
import com.softuni.pathfinder.service.Impl.PathFinderUserDetailService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/", "/about","/users/login", "/users/register").permitAll()
                .requestMatchers("/users/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
        )
                .formLogin(loginConfigurer -> {
                    loginConfigurer
                            .loginPage("/users/login")
                            .defaultSuccessUrl("/", true);
                })
                .logout(logoutConfigurer -> {
                    logoutConfigurer
                            .logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
                            .deleteCookies("JSESSIONID")
                            .invalidateHttpSession(true)
                            .clearAuthentication(true)
                            .logoutSuccessUrl("/");
                })
                .csrf(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PathFinderUserDetailService pathFinderUserDetailService(UserRepository userRepository){
        return new PathFinderUserDetailService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
