package com.zmz121.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.CommonReturnType;

@RestController
@RequestMapping("/api")
public class CommonController
{

    @RequestMapping("/unlogin")
    public CommonReturnType unlogin()
    {
        return CommonReturnType.errorWithCode(503, "need login");
    }
}
