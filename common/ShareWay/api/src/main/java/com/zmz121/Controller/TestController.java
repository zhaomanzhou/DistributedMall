package com.zmz121.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController
{

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("/hello")
    public String hello(HttpServletRequest request)
    {
        String contextPath = request.getServletContext().getContextPath();
        String contextPath1 = request.getContextPath();
        log.info("path1: " + contextPath1);
        log.info(contextPath);
        return "hello";
    }

    @RequestMapping("/add")
    public void add(HttpServletRequest request)
    {
        Map<String, String[]> map = request.getParameterMap();
        System.out.println(map.toString());
    }
}
