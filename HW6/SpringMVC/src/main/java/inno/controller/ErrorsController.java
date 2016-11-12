package inno.controller;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class ErrorsController {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String errorHandler() {
        return "errors/error";
    }

}
