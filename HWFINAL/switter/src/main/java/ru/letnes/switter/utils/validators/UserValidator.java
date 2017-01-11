package ru.letnes.switter.utils.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.letnes.switter.utils.form.UserForm;


public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserForm form = (UserForm) target;
        if (!form.getPassword().equals(form.getRepassword())) {
            errors.rejectValue("repassword", "", "Passwords are not equal to each other");
        }
    }
}
