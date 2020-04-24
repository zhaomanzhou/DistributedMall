package com.zmz.service;

import com.zmz.entity.po.Cart;
import com.zmz.entity.vo.CartVo;
import com.zmz.response.error.BizException;
import com.zmz.response.error.BusinessException;

import java.util.List;


public interface ICartService {
    CartVo add(Integer userId, Integer productId, Integer count) throws BusinessException, BizException;
    CartVo update(Integer userId, Integer productId, Integer count) throws BizException, BusinessException;
    CartVo deleteProduct(Integer userId, String productIds) throws BizException, BusinessException;
    CartVo list(Integer userId) throws BizException, BusinessException;
    CartVo selectOrUnSelect(Integer userId, Integer productId, Integer checked) throws BizException, BusinessException;
    Integer getCartProductCount(Integer userId);
    List<Cart> selectCheckedCartByUserId(Integer userId);
    void cleanCart(List<Cart> cartList);
}
