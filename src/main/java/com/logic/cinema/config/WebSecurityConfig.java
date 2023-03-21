package com.logic.cinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.logic.cinema.config.constant.SecurityConstant.*;
import static com.logic.cinema.model.Role.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, API_PATH_TO_MOVIES.getName()).hasAnyRole(CONSUMER.name(), MODERATOR.name())
                .mvcMatchers(HttpMethod.POST, API_PATH_TO_TICKETS.getName()).hasAnyRole(CONSUMER.name(), CASHIER.name())
                .mvcMatchers(HttpMethod.POST, API_PATH_TO_MOVIES.getName()).hasRole(MODERATOR.name())
                .mvcMatchers(HttpMethod.PUT, API_PATH_TO_MOVIES.getName()).hasRole(MODERATOR.name())
                .mvcMatchers(HttpMethod.GET, API_PATH_TO_TICKETS.getName()).hasAnyRole(CONSUMER.name(), CASHIER.name())
                .mvcMatchers(HttpMethod.DELETE, API_PATH_TO_TICKETS.getName()).hasRole(SUPPORT_L1.name())
                .mvcMatchers(HttpMethod.POST, API_PATH_TO_HALLS.getName()).hasRole(SUPPORT_L2.name())

                //Admin
                .mvcMatchers(HttpMethod.GET, ALL_API_PATH.getName()).hasRole(ADMIN.name())
                .mvcMatchers(HttpMethod.POST, ALL_API_PATH.getName()).hasRole(ADMIN.name())
                .mvcMatchers(HttpMethod.PUT, ALL_API_PATH.getName()).hasRole(ADMIN.name())
                .mvcMatchers(HttpMethod.DELETE, ALL_API_PATH.getName()).hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .httpBasic();

    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("consumer")
                        .password(passwordEncoder().encode("qwerty"))
                        .roles(CONSUMER.name())
                        .build(),
                User.builder()
                        .username("moderator")
                        .password(passwordEncoder().encode("qwerty"))
                        .roles(MODERATOR.name())
                        .build(),
                User.builder()
                        .username("cashier")
                        .password(passwordEncoder().encode("qwerty"))
                        .roles(CASHIER.name())
                        .build(),
                User.builder()
                        .username("support_l1")
                        .password(passwordEncoder().encode("qwerty"))
                        .roles(SUPPORT_L1.name())
                        .build(),
                User.builder()
                        .username("support_l2")
                        .password(passwordEncoder().encode("qwerty"))
                        .roles(SUPPORT_L2.name())
                        .build(),
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("qwerty"))
                        .roles(ADMIN.name())
                        .build());

    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
