package com.zmz.service;

import com.github.pagehelper.PageInfo;
import com.zmz.entity.po.Shipping;
import com.zmz.response.error.BizException;


public interface IShippingService {

    Integer add(Integer userId, Shipping shipping) throws BizException;
    void del(Integer userId, Integer shippingId) throws BizException;
    void update(Integer userId, Shipping shipping) throws BizException;
    Shipping select(Integer userId, Integer shippingId) throws BizException;
    PageInfo list(Integer userId, int pageNum, int pageSize);

}
