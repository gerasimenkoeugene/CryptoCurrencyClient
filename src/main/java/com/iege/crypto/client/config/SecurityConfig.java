package com.iege.crypto.client.config;

import com.iege.crypto.client.service.SecUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private
    CustomAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Autowired
    private SecUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/activation/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(customizeAuthenticationSuccessHandler)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configAuthBuilder(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService);
    }

}