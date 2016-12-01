package inno.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ControllerAspect {
    public String authentificateSession(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        HttpSession session = attr.getRequest().getSession(true);
        String loginURI = request.getContextPath() + "/login";
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean loggedIn = session != null && session.getAttribute("user") != null;

        if (request.getRequestURI().equals("/students")) {
            joinPoint.proceed();
        }
        if (request.getServletPath().equals("/logout") && loggedIn) {
            session.invalidate();
            return "redirect:/login";
        } else {
            if (loggedIn || loginRequest) {
                Object result = joinPoint.proceed();
                return result.toString();
            } else {
                return "redirect:/login" + "?redirectUrl=" + request.getRequestURI();
            }
        }
    }
}
