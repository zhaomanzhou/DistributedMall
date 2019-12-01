package com.mmall.control;

import com.github.pagehelper.PageInfo;
import com.mmall.bean.po.Shipping;
import com.mmall.bean.po.User;
import com.mmall.service.IShippingService;
import com.mmall.service.IUserService;
import com.mmall.util.ResponseCode;
import com.mmall.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by zhaomanzhou
 */

@Controller
@RequestMapping("/shipping/")
public class ShippingController {


    @Autowired
    private IShippingService iShippingService;
    @Autowired
    private IUserService iUserService;

//    @RequestMapping("add.do")
//    @ResponseBody
//    public ServerResponse add(String token, Shipping shipping){
//
//        User user = iUserService.getUser(token);
//
//        if(user ==null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
//        }
//        return iShippingService.add(user.getId(),shipping);
//    }
//
//
//    @RequestMapping("del.do")
//    @ResponseBody
//    public ServerResponse del(HttpSession session,Integer shippingId){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);
//        if(user ==null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
//        }
//        return iShippingService.del(user.getId(),shippingId);
//    }
//
//    @RequestMapping("update.do")
//    @ResponseBody
//    public ServerResponse update(HttpSession session,Shipping shipping){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);
//        if(user ==null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
//        }
//        return iShippingService.update(user.getId(),shipping);
//    }
//
//
//    @RequestMapping("select.do")
//    @ResponseBody
//    public ServerResponse<Shipping> select(HttpSession session,Integer shippingId){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);
//        if(user ==null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
//        }
//        return iShippingService.select(user.getId(),shippingId);
//    }
//
//
//    @RequestMapping("list.do")
//    @ResponseBody
//    public ServerResponse<PageInfo> list(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
//                                         @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
//                                         HttpSession session){
//        User user = (User)session.getAttribute(Const.CURRENT_USER);
//        if(user ==null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
//        }
//        return iShippingService.list(user.getId(),pageNum,pageSize);
//    }
//
//
//











}
