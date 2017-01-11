package ru.letnes.switter.security;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.letnes.switter.entity.User;

public class SecurityUtils {

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
