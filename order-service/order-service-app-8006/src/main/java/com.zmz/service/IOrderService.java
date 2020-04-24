package com.zmz.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.vo.OrderVo;
import com.zmz.entity.vo.OrderProductVo;
import com.zmz.entity.vo.OrderVo;
import com.zmz.response.error.BizException;
import com.zmz.response.error.BusinessException;

import java.util.Map;


public interface IOrderService {
    ServerResponse pay(Long orderNo, Integer userId, String path) throws BizException;
    ServerResponse aliCallback(Map<String,String> params);
    ServerResponse queryOrderPayStatus(Integer userId,Long orderNo);
    OrderVo createOrder(Integer userId, Integer shippingId) throws BizException, BusinessException;
    void cancel(Integer userId, Long orderNo) throws BizException, BusinessException;
    OrderProductVo getOrderCartProduct(Integer userId) throws BizException, BusinessException;
    OrderVo getOrderDetail(Integer userId, Long orderNo) throws BizException;
    PageInfo getOrderList(Integer userId, int pageNum, int pageSize);



    //backend
    ServerResponse<PageInfo> manageList(int pageNum,int pageSize);
    ServerResponse<OrderVo> manageDetail(Long orderNo);
    ServerResponse<PageInfo> manageSearch(Long orderNo,int pageNum,int pageSize);
    ServerResponse<String> manageSendGoods(Long orderNo);


}
