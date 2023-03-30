package com.logic.cinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.logic.cinema.model.constant.Role.ADMIN;
import static com.logic.cinema.model.constant.Role.CONSUMER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/auth/success").authenticated()

                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login").permitAll()
                .defaultSuccessUrl("/auth/success")
                .failureUrl("/auth/failure").permitAll();

                /*.and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard").failureForwardUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()*/
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/css/**");
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
