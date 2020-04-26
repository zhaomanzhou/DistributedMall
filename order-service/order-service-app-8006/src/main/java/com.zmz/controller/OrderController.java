package com.zmz.controller;


import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.google.common.collect.Maps;

import com.zmz.common.Const;
import com.zmz.common.ContextConstant;
import com.zmz.common.ResponseCode;
import com.zmz.common.ThreadLoalCache;
import com.zmz.response.ServerResponse;
import com.zmz.response.error.BizException;
import com.zmz.response.error.BusinessException;
import com.zmz.service.IOrderService;
import com.zmz.user.entity.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;



@Controller
@RequestMapping("/order/")
@Slf4j
public class OrderController {


    @Autowired
    private IOrderService iOrderService;


    @RequestMapping("/create")
    @ResponseBody
    public ServerResponse create(Integer shippingId) throws BizException, BusinessException
    {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);
        return ServerResponse.success(iOrderService.createOrder(user.getId(),shippingId));
    }


    @RequestMapping("/cancel")
    @ResponseBody
    public ServerResponse cancel(Long orderNo) throws BizException, BusinessException
    {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);


        iOrderService.cancel(user.getId(),orderNo);
        return ServerResponse.success();
    }


    @RequestMapping("/get_order_cart_product")
    @ResponseBody
    public ServerResponse getOrderCartProduct() throws BizException, BusinessException
    {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);


        return ServerResponse.success(iOrderService.getOrderCartProduct(user.getId()));
    }



    @RequestMapping("/detail")
    @ResponseBody
    public ServerResponse detail(Long orderNo) throws BizException
    {

        User user = (User) ThreadLoalCache.get(ContextConstant.USER);

        return ServerResponse.success(iOrderService.getOrderDetail(user.getId(),orderNo));
    }

    @RequestMapping("/list")
    @ResponseBody
    public ServerResponse list( @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){

        User user = (User) ThreadLoalCache.get(ContextConstant.USER);

        return ServerResponse.success(iOrderService.getOrderList(user.getId(),pageNum,pageSize));
    }
























    @RequestMapping("/pay")
    @ResponseBody
    public ServerResponse pay( Long orderNo, HttpServletRequest request) throws BizException
    {

        User user = (User) ThreadLoalCache.get(ContextConstant.USER);

        if(user ==null){
            return ServerResponse.error(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        String path = request.getSession().getServletContext().getRealPath("upload");
        return ServerResponse.success(iOrderService.pay(orderNo,user.getId(),path));
    }

    @RequestMapping("/alipay_callback")
    @ResponseBody
    public Object alipayCallback(HttpServletRequest request)
    {
        Map<String,String> params = Maps.newHashMap();

        Map requestParams = request.getParameterMap();
        for(Iterator iter = requestParams.keySet().iterator();iter.hasNext();){
            String name = (String)iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for(int i = 0 ; i <values.length;i++){

                valueStr = (i == values.length -1)?valueStr + values[i]:valueStr + values[i]+",";
            }
            params.put(name,valueStr);
        }
        log.info("支付宝回调,sign:{},trade_status:{},参数:{}",params.get("sign"),params.get("trade_status"),params.toString());

        //非常重要,验证回调的正确性,是不是支付宝发的.并且呢还要避免重复通知.

        params.remove("sign_type");
        try {
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType());

            if(!alipayRSACheckedV2){
                throw new BizException("非法请求,验证不通过,再恶意请求我就报警找网警了");
            }
        } catch (AlipayApiException | BizException e) {
            log.error("支付宝验证回调异常",e);
        }

        //todo 验证各种数据


        try
        {
            iOrderService.aliCallback(params);
        } catch (BizException e)
        {
            log.warn("{}", e);
            return Const.AlipayCallback.RESPONSE_FAILED;
        }

        return Const.AlipayCallback.RESPONSE_SUCCESS;
    }


    @RequestMapping("query_order_pay_status.do")
    @ResponseBody
    public ServerResponse<Boolean> queryOrderPayStatus(HttpServletRequest httpServletRequest, Long orderNo){
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);

        try
        {
            iOrderService.queryOrderPayStatus(user.getId(),orderNo);
        } catch (BizException | BusinessException e)
        {
            return ServerResponse.error();
        }

        return ServerResponse.success(false);
    }



















}
