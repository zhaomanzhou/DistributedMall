package com.mmall.control;


import com.mmall.bean.po.User;
import com.mmall.common.Common;
import com.mmall.service.IAdminService;
import com.mmall.util.ServerResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by weiqiang
 */
@RestController
@RequestMapping("/manager/admin/")
public class AdminController {

    @Autowired
    private Common common;


    @Autowired
    private IAdminService iAdminService;

    @ApiOperation(value = "管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "passwd", value = "密码", required = true, dataType = "String"),
    })
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ServerResponse<User> login(@RequestParam("user") String username, @RequestParam("passwd") String password) {
        return iAdminService.login(username, password);
    }


    @ApiOperation(value = "管理员修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "old", value = "旧密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "new1", value = "新密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "new2", value = "新密码", required = true, dataType = "String"),
    })
    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    public ServerResponse<User> login(@RequestParam("old") String old, @RequestParam("new1") String new1, @RequestParam("new2") String new2, HttpServletRequest request) {
        return iAdminService.update(old, new1, new2, common.getid(request));
    }

}