package inno.controller;

import inno.aop.CustomLoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class LoginExceptionAdvice{
    @ExceptionHandler(CustomLoginException.class)
    public String errorLogin(CustomLoginException ex) {
        System.out.println(ex.getMessage());
        String redir = (!ex.getMessage().equals("") ? "?redirectUrl=" + ex.getMessage() : "");
        return "redirect:/login" + redir;
    }
}