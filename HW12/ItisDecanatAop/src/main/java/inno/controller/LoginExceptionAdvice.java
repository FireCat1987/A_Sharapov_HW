package inno.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.LoginException;

@ControllerAdvice
public class LoginExceptionAdvice{

    @ExceptionHandler(LoginException.class)
    public String errorLogin(LoginException ex) {
        System.out.println(ex.getMessage());
        return "redirect:/login";
    }
}