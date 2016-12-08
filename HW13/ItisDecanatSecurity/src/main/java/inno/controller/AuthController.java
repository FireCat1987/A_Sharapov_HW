package inno.controller;


import inno.model.Users;
import inno.service.UsersService;
import inno.utils.form.UserForm;
import inno.utils.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping
public class AuthController {

    @Autowired
    UsersService usersService;
    @Autowired
    UserValidator userValidator;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) Boolean error, ModelMap map){
        if (error == Boolean.TRUE) {
            map.addAttribute("error", true);
        }
        map.addAttribute("users", new Users());
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(ModelMap map){
        map.addAttribute("userForm", new UserForm());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm") @Valid UserForm form, BindingResult result){
        userValidator.validate(form, result);
        if (result.hasErrors()) {
            return "registration";
        }
        usersService.saveUser(form);
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request){
        request.getSession(false).invalidate();
        return "redirect:/login";
    }

/*    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authorizeUser(@ModelAttribute @Valid Users user, BindingResult result, HttpServletRequest request){
        String ref = null;
        if(request.getHeader("referer").split("=").length > 1){
            ref = request.getHeader("referer").split("=")[1];
        }
        if (result.hasErrors()) {
            return "login";
        }
        System.out.println(user.getLogin());
        Users loginUser = usersRepository.findByLogin(user.getLogin());
        System.out.println(loginUser);
        if(loginUser != null && loginUser.getPassword().equals(user.getPassword())) {
            request.getSession(true).setAttribute("user", user);
            return "redirect:"+(ref == null ? "/students" : ref);
        }
        ObjectError error = new ObjectError("login","Не найден пользователь с таким login");
        result.addError(error);
        return "login";
    }*/
}
