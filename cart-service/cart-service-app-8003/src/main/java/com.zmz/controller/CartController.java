package com.zmz.controller;


import com.zmz.common.Const;
import com.zmz.common.ContextConstant;
import com.zmz.common.RequestContext;
import com.zmz.common.ThreadLoalCache;
import com.zmz.entity.vo.CartVo;
import com.zmz.response.ServerResponse;
import com.zmz.response.error.BizException;
import com.zmz.response.error.BusinessException;
import com.zmz.service.ICartService;
import com.zmz.user.entity.po.User;
import com.zmz.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    private ICartService iCartService;


    @GetMapping("/list")
    public ServerResponse<CartVo> list() throws BizException, BusinessException {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);
        return ServerResponse.success(iCartService.list(user.getId()));
    }

    @PostMapping("/add")
    public ServerResponse<CartVo> add(Integer count, Integer productId) throws BizException, BusinessException {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);
        return ServerResponse.success(iCartService.add(user.getId(),productId,count));
    }



    @PutMapping("/update")
    public ServerResponse<CartVo> update(Integer count, Integer productId) throws BizException, BusinessException {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);
        return ServerResponse.success(iCartService.update(user.getId(),productId,count));
    }

    @DeleteMapping("/delete")
    public ServerResponse<CartVo> deleteProduct(String productIds) throws BizException, BusinessException {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);
        return ServerResponse.success(iCartService.deleteProduct(user.getId(),productIds));
    }



    @PutMapping("/select/all")
    public ServerResponse<CartVo> selectAll() throws BizException, BusinessException {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);
        return ServerResponse.success(iCartService.selectOrUnSelect(user.getId(),null, Const.Cart.CHECKED));
    }

    @PutMapping("/unselect/all")
    public ServerResponse<CartVo> unSelectAll() throws BizException, BusinessException {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);

        return ServerResponse.success(iCartService.selectOrUnSelect(user.getId(),null,Const.Cart.UN_CHECKED));
    }




    @PutMapping("/select/{productId}")
    public ServerResponse<CartVo> select(@PathVariable("productId") Integer productId) throws BizException, BusinessException {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);
        return ServerResponse.success(iCartService.selectOrUnSelect(user.getId(),productId,Const.Cart.CHECKED));
    }


    @PutMapping("/unselect/{productId}")
    public ServerResponse<CartVo> unSelect(@PathVariable("productId") Integer productId) throws BizException, BusinessException {
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);

        return ServerResponse.success(iCartService.selectOrUnSelect(user.getId(),productId,Const.Cart.UN_CHECKED));
    }


    @GetMapping("/productCount")
    public ServerResponse<Integer> getCartProductCount(){
        User user = (User) ThreadLoalCache.get(ContextConstant.USER);

        return ServerResponse.success(iCartService.getCartProductCount(user.getId()));
    }




    //全选
    //全反选

    //单独选
    //单独反选

    //查询当前用户的购物车里面的产品数量,如果一个产品有10个,那么数量就是10.




}
