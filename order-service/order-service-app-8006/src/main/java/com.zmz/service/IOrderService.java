package com.zmz.service;

import com.github.pagehelper.PageInfo;

import com.zmz.entity.vo.OrderProductVo;
import com.zmz.entity.vo.OrderVo;
import com.zmz.response.error.BizException;
import com.zmz.response.error.BusinessException;

import java.util.Map;


public interface IOrderService {
    Map<String, String> pay(Long orderNo, Integer userId, String path) throws BizException;
    void aliCallback(Map<String,String> params) throws BizException;
    void queryOrderPayStatus(Integer userId, Long orderNo) throws BizException, BusinessException;
    OrderVo createOrder(Integer userId, Integer shippingId) throws BizException, BusinessException;
    void cancel(Integer userId, Long orderNo) throws BizException, BusinessException;
    OrderProductVo getOrderCartProduct(Integer userId) throws BizException, BusinessException;
    OrderVo getOrderDetail(Integer userId, Long orderNo) throws BizException;
    PageInfo getOrderList(Integer userId, int pageNum, int pageSize);



    //backend
    PageInfo manageList(int pageNum, int pageSize);
    OrderVo manageDetail(Long orderNo) throws BizException;
    PageInfo manageSearch(Long orderNo, int pageNum, int pageSize) throws BizException;
    String manageSendGoods(Long orderNo) throws BizException;


}
