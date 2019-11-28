package com.mmall.control;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController
{

    @RequestMapping("/query")
    public String queryServer()
    {
        String tomcat_server_id = System.getenv("TOMCAT_SERVER_ID");
        return tomcat_server_id;
    }
}
