package com.logic.cinema.config;

import com.logic.cinema.model.Role;
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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/movies").hasRole(Role.CONSUMER.name())
                .antMatchers(HttpMethod.POST,"/api/tickets").hasAnyRole(Role.CONSUMER.name(), Role.CASHIER.name())
                .antMatchers(HttpMethod.POST, "/api/movies").hasRole(Role.MODERATOR.name())
                .antMatchers(HttpMethod.PUT, "/api/movies").hasRole(Role.MODERATOR.name())
                .antMatchers(HttpMethod.GET, "/api/tickets").hasRole(Role.CASHIER.name())
                .antMatchers(HttpMethod.DELETE, "/api/tickets").hasRole(Role.SUPPORT_L1.name())
                .antMatchers(HttpMethod.POST, "/api/halls").hasRole(Role.SUPPORT_L2.name())
                .antMatchers(HttpMethod.GET, "/api/**").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/**").hasRole(Role.ADMIN.name())
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
                        .roles(Role.CONSUMER.name())
                        .build(),
                User.builder()
                        .username("moderator")
                        .password(passwordEncoder().encode("qwerty"))
                        .roles(Role.MODERATOR.name())
                        .build(),
                User.builder()
                        .username("cashier")
                        .password(passwordEncoder().encode("qwerty"))
                        .roles(Role.CASHIER.name())
                        .build(),
                User.builder()
                        .username("support_l1")
                        .password(passwordEncoder().encode("qwerty"))
                        .roles(Role.SUPPORT_L1.name())
                        .build(),
                User.builder()
                        .username("support_l2")
                        .password(passwordEncoder().encode("qwerty"))
                        .roles(Role.SUPPORT_L2.name())
                        .build(),
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("qwerty"))
                        .roles(Role.ADMIN.name())
                        .build());

    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
