package com.zmz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.zmz.entity.po.Shipping;
import com.zmz.mapper.ShippingMapper;
import com.zmz.response.error.BizException;
import com.zmz.service.IShippingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by geely
 */
@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {


    @Resource
    private ShippingMapper shippingMapper;

    public Integer add(Integer userId, Shipping shipping) throws BizException {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.insert(shipping);
        if(rowCount > 0){
            Map result = Maps.newHashMap();
            result.put("shippingId",shipping.getId());
            return shipping.getId();
        }
        throw new BizException("新建地址失败");
    }

    public void del(Integer userId, Integer shippingId) throws BizException {
        int resultCount = shippingMapper.deleteByShippingIdUserId(userId,shippingId);
        if(resultCount > 0){
            return ;
        }
        throw new BizException("删除地址失败");
    }


    public void update(Integer userId, Shipping shipping) throws BizException {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.updateByShipping(shipping);
        if(rowCount > 0){
            return ;
        }
        throw new BizException("更新地址失败");
    }

    public Shipping select(Integer userId, Integer shippingId) throws BizException {
        Shipping shipping = shippingMapper.selectByShippingIdUserId(userId,shippingId);
        if(shipping == null){
            throw new BizException("无法查询到该地址");
        }
        return shipping;
    }


    public PageInfo list(Integer userId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return pageInfo;
    }







}
