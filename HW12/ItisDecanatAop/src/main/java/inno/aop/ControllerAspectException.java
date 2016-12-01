package inno.aop;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ControllerAspectException {
    public void authentificateSession() throws LoginException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        HttpSession session = attr.getRequest().getSession(true);
        String loginURI = request.getContextPath() + "/login";
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        if (request.getServletPath().equals("/logout") && loggedIn) {
            session.invalidate();
            throw new LoginException("1");
        } else {
            if (!(loggedIn || loginRequest)) {
                throw new LoginException("2");
            }
        }
    }


}

class LoginException extends RuntimeException {
    LoginException(String message) {
        super(message);
    }
}

