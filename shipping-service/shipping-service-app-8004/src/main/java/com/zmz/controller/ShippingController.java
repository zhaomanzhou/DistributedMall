package com.zmz.controller;

import com.github.pagehelper.PageInfo;
import com.zmz.common.ContextConstant;
import com.zmz.common.RequestContext;
import com.zmz.common.ResponseCode;
import com.zmz.common.ThreadLoalCache;
import com.zmz.entity.po.Shipping;
import com.zmz.response.ServerResponse;
import com.zmz.response.error.BizException;
import com.zmz.service.IShippingService;
import com.zmz.user.entity.po.User;
import com.zmz.user.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/shipping/")
public class ShippingController {


    @Autowired
    private IShippingService iShippingService;

    @Reference
    private IUserService userService;

    @RequestMapping("/test")
    public void test(String token)
    {
        User uuu = userService.getUserByToken(token);
        System.out.println(uuu);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ServerResponse add(Shipping shipping) throws BizException {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);
        if(user ==null){
            return ServerResponse.error(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        iShippingService.add(user.getId(),shipping);
        return ServerResponse.success();
    }


    @RequestMapping("/del")
    @ResponseBody
    public ServerResponse del(Integer shippingId) throws BizException {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);
        if(user ==null){
            return ServerResponse.error(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        iShippingService.del(user.getId(),shippingId);
        return ServerResponse.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public ServerResponse update(Shipping shipping) throws BizException {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);
        if(user ==null){
            return ServerResponse.error(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        iShippingService.update(user.getId(),shipping);
        return ServerResponse.success();
    }


    @RequestMapping("/select")
    @ResponseBody
    public ServerResponse<Shipping> select(Integer id) throws BizException {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);

        if(user ==null){
            return ServerResponse.error(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        Shipping select = iShippingService.select(user.getId(), id);
        return ServerResponse.success(select);

    }


    @RequestMapping("/list")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        User user = (User) ThreadLoalCache.get(ContextConstant.USER);

        if(user ==null){
            return ServerResponse.error(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        PageInfo list = iShippingService.list(user.getId(), pageNum, pageSize);
        return ServerResponse.success(list);
    }














}
