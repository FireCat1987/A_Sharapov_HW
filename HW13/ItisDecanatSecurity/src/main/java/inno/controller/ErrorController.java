package inno.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        ModelMap map = new ModelMap();
        map.addAttribute("stacktrace", ex.getStackTrace());
        map.addAttribute("message", ex.getMessage());
        map.addAttribute("errorclass", ex.getClass());
        System.out.println("Произошла ошибка класса: " + ex.getClass());
        System.out.println("message: " + ex.getMessage());
        return "error";
    }
}
