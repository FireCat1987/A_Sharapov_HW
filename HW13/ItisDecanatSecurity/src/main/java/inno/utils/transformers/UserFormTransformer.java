package inno.utils.transformers;

import inno.model.Users;
import inno.utils.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFormTransformer {
    @Autowired
    BCryptPasswordEncoder encoder;

    public Users toUser(UserForm form) {
        Users user = new Users();
        user.setLogin(form.getLogin());
        user.setPassword(encoder.encode(form.getPassword()));
        return user;
    }

}
