package com.iege.crypto.client.errorhandling;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler({DisabledException.class, InternalAuthenticationServiceException.class})
    public String disabledUser() {
        return "/login?disabled";
    }
}
