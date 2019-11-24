package com.mmall.service.imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmall.bean.po.Order;
import com.mmall.bean.po.OrderItem;
import com.mmall.bean.vo.OrderVo;
import com.mmall.mapper.OrderItemMapper;
import com.mmall.mapper.OrderMapper;
import com.mmall.service.IOrderService;
import com.mmall.util.Const;
import com.mmall.util.DateTimeUtil;
import com.mmall.util.ServerResponse;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by weiqiang
 */

@Service("iOrderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;


    public ServerResponse<PageInfo> manageList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.selectAllOrder();
        PageInfo pageResult = new PageInfo(orderList);
        return ServerResponse.createBySuccess(pageResult);
    }


    public ServerResponse<OrderItem> manageDetail(Long orderNo) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (order != null) {
            OrderItem orderItemList = orderItemMapper.getByOrderNo(orderNo);
            return ServerResponse.createBySuccess(orderItemList);
        }
        return ServerResponse.createByErrorMessage("订单不存在");
    }


    public ServerResponse<PageInfo> manageSearch(Long orderNo, int pageNum, int pageSize) {
//        PageHelper.startPage(pageNum, pageSize);
//        Order order = orderMapper.selectByOrderNo(orderNo);
//        if (order != null) {
//            List<OrderItem> orderItemList = orderItemMapper.getByOrderNo(orderNo);
//            OrderVo orderVo = assembleOrderVo(order, orderItemList);
//
//            PageInfo pageResult = new PageInfo(Lists.newArrayList(order));
//            pageResult.setList(Lists.newArrayList(orderVo));
//            return ServerResponse.createBySuccess(pageResult);
//        }
        return ServerResponse.createByErrorMessage("订单不存在");
    }


    public ServerResponse<String> manageSendGoods(Long orderNo) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (order != null) {
            if (order.getStatus() == Const.OrderStatusEnum.PAID.getCode()) {
                order.setStatus(Const.OrderStatusEnum.SHIPPED.getCode());
                order.setSendTime(new Date());
                orderMapper.updateByPrimaryKeySelective(order);
                return ServerResponse.createBySuccess("发货成功");
            }
        }
        return ServerResponse.createByErrorMessage("订单不存在");
    }

}
