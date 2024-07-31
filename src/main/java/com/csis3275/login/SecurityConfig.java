package com.csis3275.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/login-page").permitAll()
                        .requestMatchers("/signup-page").permitAll()
                        .requestMatchers("/search").permitAll()
                        .requestMatchers("/results").permitAll()
                        .requestMatchers("/AboutUs").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/loggedUsers").permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/**/*.*")).permitAll()
                        .requestMatchers("/**").permitAll()
                );


        return http.build();
    }


}
