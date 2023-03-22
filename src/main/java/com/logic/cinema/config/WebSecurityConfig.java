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

import static com.logic.cinema.config.constant.SecurityConstant.ALL_API_PATH;
import static com.logic.cinema.model.constant.Permission.ALL_READ;
import static com.logic.cinema.model.constant.Permission.ALL_WRITE;
import static com.logic.cinema.model.constant.Role.ADMIN;
import static com.logic.cinema.model.constant.Role.CONSUMER;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .csrf().disable()
                .authorizeRequests()
                /*.mvcMatchers("/dashboard").authenticated()*/

                .mvcMatchers(HttpMethod.GET, ALL_API_PATH.getName()).hasAuthority(ALL_READ.getPermission())
                .mvcMatchers(HttpMethod.POST, ALL_API_PATH.getName()).hasAuthority(ALL_WRITE.getPermission())
                .mvcMatchers(HttpMethod.PUT, ALL_API_PATH.getName()).hasAuthority(ALL_WRITE.getPermission())
                .mvcMatchers(HttpMethod.DELETE, ALL_API_PATH.getName()).hasAuthority(ALL_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

                /*.and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard").failureForwardUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()*/


    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("consumer")
                        .password(passwordEncoder().encode("qwerty"))
                        .authorities(CONSUMER.getAuthorities())
                        .build(),
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("qwerty"))
                        .authorities(ADMIN.getAuthorities())
                        .build());

    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
