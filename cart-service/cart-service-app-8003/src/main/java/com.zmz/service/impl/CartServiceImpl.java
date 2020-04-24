package com.zmz.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import com.zmz.common.Const;
import com.zmz.common.ResponseCode;
import com.zmz.entity.po.Cart;
import com.zmz.entity.po.Product;
import com.zmz.entity.vo.CartProductVo;
import com.zmz.entity.vo.CartVo;
import com.zmz.entity.vo.ProductDetailVo;
import com.zmz.exception.BusinessErrorEnum;
import com.zmz.mapper.CartMapper;
import com.zmz.response.ServerResponse;
import com.zmz.response.error.BizException;
import com.zmz.response.error.BusinessException;
import com.zmz.service.ICartService;
import com.zmz.service.IProductService;
import com.zmz.util.BigDecimalUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


@Service("iCartService")
public class CartServiceImpl implements ICartService {

    @Resource
    private CartMapper cartMapper;

    @Reference
    private IProductService productService;


    /**
     * 购物车添加商品
     */
    public CartVo add(Integer userId, Integer productId, Integer count) throws BusinessException, BizException {
        if(productId == null || count == null){
            throw new BusinessException(BusinessErrorEnum.PARAMETER_EMPTY_ERROR);
        }


        Cart cart = cartMapper.selectCartByUserIdProductId(userId,productId);
        if(cart == null){
            //这个产品不在这个购物车里,需要新增一个这个产品的记录
            Cart cartItem = new Cart();
            cartItem.setQuantity(count);
            cartItem.setChecked(Const.Cart.CHECKED);
            cartItem.setProductId(productId);
            cartItem.setUserId(userId);
            cartMapper.insert(cartItem);
        }else{
            //这个产品已经在购物车里了.
            //如果产品已存在,数量相加
            count = cart.getQuantity() + count;
            cart.setQuantity(count);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
        return this.list(userId);
    }

    public CartVo update(Integer userId,Integer productId,Integer count) throws BizException, BusinessException {
        if(productId == null || count == null){
            throw new BizException("参数不能为空");
        }
        Cart cart = cartMapper.selectCartByUserIdProductId(userId,productId);
        if(cart != null){
            cart.setQuantity(count);
        }
        cartMapper.updateByPrimaryKey(cart);
        return this.list(userId);
    }

    public CartVo deleteProduct(Integer userId,String productIds) throws BizException, BusinessException {
        List<String> productList = Splitter.on(",").splitToList(productIds);
        if(CollectionUtils.isEmpty(productList)){
            throw new BizException("参数不能为空");
        }
        cartMapper.deleteByUserIdProductIds(userId,productList);
        return this.list(userId);
    }


    public CartVo list (Integer userId) throws BizException, BusinessException {
        CartVo cartVo = this.getCartVoLimit(userId);
        return cartVo;
    }



    public CartVo selectOrUnSelect (Integer userId,Integer productId,Integer checked) throws BizException, BusinessException {
        cartMapper.checkedOrUncheckedProduct(userId,productId,checked);
        return this.list(userId);
    }

    public Integer getCartProductCount(Integer userId){
        if(userId == null){
            return 0;
        }
        return cartMapper.selectCartProductCount(userId);
    }















    private CartVo getCartVoLimit(Integer userId) throws BizException, BusinessException {
        CartVo cartVo = new CartVo();
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);
        List<CartProductVo> cartProductVoList = Lists.newArrayList();

        BigDecimal cartTotalPrice = new BigDecimal("0");

        if(CollectionUtils.isNotEmpty(cartList)){
            for(Cart cartItem : cartList){
                CartProductVo cartProductVo = new CartProductVo();
                cartProductVo.setId(cartItem.getId());
                cartProductVo.setUserId(userId);
                cartProductVo.setProductId(cartItem.getProductId());

                ProductDetailVo product = productService.getProductDetail(cartItem.getProductId());
                if(product != null){
                    cartProductVo.setProductMainImage(product.getMainImage());
                    cartProductVo.setProductName(product.getName());
                    cartProductVo.setProductSubtitle(product.getSubtitle());
                    cartProductVo.setProductStatus(product.getStatus());
                    cartProductVo.setProductPrice(product.getPrice());
                    cartProductVo.setProductStock(product.getStock());
                    //判断库存
                    int buyLimitCount = 0;
                    if(product.getStock() >= cartItem.getQuantity()){
                        //库存充足的时候
                        buyLimitCount = cartItem.getQuantity();
                        cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_SUCCESS);
                    }else{
                        buyLimitCount = product.getStock();
                        cartProductVo.setLimitQuantity(Const.Cart.LIMIT_NUM_FAIL);
                        //购物车中更新有效库存
                        Cart cartForQuantity = new Cart();
                        cartForQuantity.setId(cartItem.getId());
                        cartForQuantity.setQuantity(buyLimitCount);
                        cartMapper.updateByPrimaryKeySelective(cartForQuantity);
                    }
                    cartProductVo.setQuantity(buyLimitCount);
                    //计算总价
                    cartProductVo.setProductTotalPrice(BigDecimalUtil.mul(product.getPrice().doubleValue(),cartProductVo.getQuantity()));
                    cartProductVo.setProductChecked(cartItem.getChecked());
                }

                if(cartItem.getChecked() == Const.Cart.CHECKED){
                    //如果已经勾选,增加到整个的购物车总价中
                    cartTotalPrice = BigDecimalUtil.add(cartTotalPrice.doubleValue(),cartProductVo.getProductTotalPrice().doubleValue());
                }
                cartProductVoList.add(cartProductVo);
            }
        }
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartProductVoList(cartProductVoList);
        cartVo.setAllChecked(this.getAllCheckedStatus(userId));

        return cartVo;
    }

    private boolean getAllCheckedStatus(Integer userId){
        if(userId == null){
            return false;
        }
        return cartMapper.selectCartProductCheckedStatusByUserId(userId) == 0;

    }














    @Override
    public List<Cart> selectCheckedCartByUserId(Integer userId)
    {
        if(userId == null){
            return null;
        }
        return cartMapper.selectCheckedCartByUserId(userId);
    }


    @Override
    public void cleanCart(List<Cart> cartList)
    {
        for(Cart cart : cartList){
            cartMapper.deleteByPrimaryKey(cart.getId());
        }
    }
}
