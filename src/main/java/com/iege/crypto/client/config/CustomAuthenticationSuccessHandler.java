package com.iege.crypto.client.config;

import com.iege.crypto.client.entity.SecUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SecUserDetails secUserDetails = (SecUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor(secUserDetails.getUsername(), secUserDetails.getPassword()));
        response.sendRedirect("/monitoring");
    }
}
