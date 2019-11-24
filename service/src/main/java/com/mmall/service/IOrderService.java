package com.mmall.service;


import com.github.pagehelper.PageInfo;
import com.mmall.bean.po.OrderItem;
import com.mmall.bean.vo.OrderVo;
import com.mmall.util.ServerResponse;

/**
 * Created by weiqiang
 */

public interface IOrderService {
    ServerResponse<PageInfo> manageList(int pageNum, int pageSize);

    ServerResponse<OrderItem> manageDetail(Long orderNo);

    ServerResponse<PageInfo> manageSearch(Long orderNo, int pageNum, int pageSize);

    ServerResponse<String> manageSendGoods(Long orderNo);
}
