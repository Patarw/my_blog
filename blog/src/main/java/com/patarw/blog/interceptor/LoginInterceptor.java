package com.patarw.blog.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//过滤器，防止其他人不登陆通过路径直接访问
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (request.getSession().getAttribute("user") == null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
