package inno.utils.transformers;

import inno.model.User;
import inno.utils.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFormTransformer {
    @Autowired
    BCryptPasswordEncoder encoder;

    public User toUser(UserForm form) {
        User user = new User();
        user.setLogin(form.getLogin());
        user.setPassword(encoder.encode(form.getPassword()));
        return user;
    }

}
