package inno.security;

import inno.model.Users;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static Users getCurrentUser() {
        return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}