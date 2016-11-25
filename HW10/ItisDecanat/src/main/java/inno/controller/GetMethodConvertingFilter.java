package inno.controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

public class GetMethodConvertingFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest hsr = (HttpServletRequest) servletRequest;
        System.out.println(hsr.getMethod());
        filterChain.doFilter(servletRequest, servletResponse);
        /*if (hsr.getMethod().equals("DELETE")) {
            HttpServletRequest hsr1 = wrapRequest((HttpServletRequest) servletRequest);
            System.out.println(hsr1.getMethod());
            filterChain.doFilter(hsr1, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }*/
    }


    @Override
    public void destroy() {
        // do nothing
    }

    private static HttpServletRequestWrapper wrapRequest(HttpServletRequest request) {
        return new HttpServletRequestWrapper(request) {
            @Override
            public String getMethod() {
                return "GET";
            }
        };
    }
}