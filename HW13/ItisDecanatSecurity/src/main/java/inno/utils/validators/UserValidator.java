package inno.utils.validators;

import inno.utils.form.UserForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserForm form = (UserForm) o;
        if (!form.getPassword().equals(form.getRepassword())) {
            errors.rejectValue("repassword", "", "Passwords are not equal to each other");
        }
    }
}
