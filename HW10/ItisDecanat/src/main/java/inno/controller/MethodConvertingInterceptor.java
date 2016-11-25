package inno.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

    public class MethodConvertingInterceptor extends HandlerInterceptorAdapter {


        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


            if (request.getMethod().equalsIgnoreCase("DELETE")) {
                System.out.println("preH" + request.getMethod());
                System.out.println(request.getMethod());
                HttpServletRequest hsr = wrapRequest(request);
                System.out.println(hsr.getMethod());
                return super.preHandle(hsr, response, handler);
            } else {
                return super.preHandle(request, response, handler);
            }
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            if (request.getMethod().equalsIgnoreCase("DELETE")) {
                System.out.println("postH" + request.getMethod());
                 System.out.println(request.getMethod());
                HttpServletRequest hsr = wrapRequest(request);
                System.out.println(hsr.getMethod());
                super.postHandle(hsr, response, handler, modelAndView);
            } else {
                super.postHandle(request, response, handler, modelAndView);
            }
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            if (request.getMethod().equals("DELETE")) {
                System.out.println("afterC" + request.getMethod());
                System.out.println(request.getMethod());
                HttpServletRequest hsr = wrapRequest(request);
                System.out.println(hsr.getMethod());
                super.afterCompletion(hsr, response, handler, ex);
            } else {
                super.afterCompletion(request, response, handler, ex);
            }
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
