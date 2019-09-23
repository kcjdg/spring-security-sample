package com.elpsy.spring.security.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("colfinancial").password(encoder().encode("colfinancialPass")).roles("ADMIN")
                .and()
                .withUser("user1").password(encoder().encode("user1")).roles("USER")
                .and()
                .withUser("user2").password(encoder().encode("user2")).roles("USER");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
             .authorizeRequests()
                .antMatchers("/api").hasAnyRole()
                .antMatchers(HttpMethod.POST, "/api/stocks").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/stocks/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/stocks/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/api/stocks/**").hasRole("ADMIN")
                .anyRequest().authenticated()
             .and()
             .httpBasic();
    }

}
