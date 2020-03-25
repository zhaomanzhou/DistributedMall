package com.zmz.service;

import com.zmz.entity.vo.CartVo;
import com.zmz.response.error.BusinessException;

/**
 * Created by geely
 */
public interface ICartService {
    CartVo add(Integer userId, Integer productId, Integer count) throws BusinessException;
    CartVo update(Integer userId, Integer productId, Integer count);
    CartVo deleteProduct(Integer userId, String productIds);
    CartVo list(Integer userId);
    CartVo selectOrUnSelect(Integer userId, Integer productId, Integer checked);
    Integer getCartProductCount(Integer userId);
}
