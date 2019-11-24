package com.mmall.control;


import com.mmall.bean.po.User;
import com.mmall.common.Common;
import com.mmall.service.IUserService;

import com.mmall.util.JsonUtil;
import com.mmall.util.JwtToken;
import com.mmall.util.ServerResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by weiqiang
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private Common common;

    @Autowired
    private StringRedisTemplate redis;

    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "登录" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true,dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码",required = true,dataType = "String"),
    })

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password) {
        return iUserService.login(username, password);
    }


}