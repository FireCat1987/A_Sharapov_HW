package inno.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*@WebFilter(value = "/students*//*")*/
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login";
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if (request.getRequestURI().equals("/students")) {
            chain.doFilter(request, response);
        }
        if (request.getServletPath().equals("/logout") && loggedIn) {
            session.invalidate();
            response.sendRedirect(loginURI);
        } else {
            if (loggedIn || loginRequest) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(loginURI+"?redirectUrl=" + request.getRequestURI());
            }
        }
    }

    @Override
    public void destroy() {

    }
}
