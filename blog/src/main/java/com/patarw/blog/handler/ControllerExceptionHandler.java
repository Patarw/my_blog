package com.patarw.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//拦截异常，并且返回错误页面
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //拦截Exception类异常
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e){
        logger.error("Request URL : {},Exception : {}",request.getRequestURL(),e);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}
