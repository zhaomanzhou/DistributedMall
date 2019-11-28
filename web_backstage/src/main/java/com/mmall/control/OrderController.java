package com.mmall.control;

import com.github.pagehelper.PageInfo;
import com.mmall.bean.vo.OrderVo;
import com.mmall.service.IOrderService;
import com.mmall.service.IUserService;
import com.mmall.util.ServerResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by geely
 */

@RestController
@RequestMapping("/manage/order")
public class OrderController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrderService iOrderService;


    @ApiOperation(value = "获取订单编号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页数默认为1", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小默认为10", dataType = "int")

    })
    @GetMapping("list.do")
    public ServerResponse<PageInfo> orderList(HttpServletRequest httpServletRequest, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
            //填充我们增加产品的业务逻辑
            return iOrderService.manageList(pageNum,pageSize);
    }

    @ApiOperation(value = "获取订单详情")
    @ApiImplicitParam(name = "orderNo", value = "订单编号", dataType = "long")
    @GetMapping("detail.do")
    public ServerResponse orderDetail(HttpServletRequest httpServletRequest,@RequestParam("orderNo") Long orderNo){
            return iOrderService.manageDetail(orderNo);
    }

    @ApiOperation(value = "订单发货")
    @ApiImplicitParam(name = "orderNo", value = "订单编号", dataType = "long")
    @PostMapping("send_goods.do")
    public ServerResponse<String> orderSendGoods(HttpServletRequest httpServletRequest, @RequestParam("orderNo") Long orderNo){
            return iOrderService.manageSendGoods(orderNo);
    }
}
