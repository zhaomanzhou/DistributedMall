package com.mmall.control;

import com.mmall.bean.po.User;
import com.mmall.bean.vo.CartVo;
import com.mmall.service.ICartService;
import com.mmall.service.IUserService;
import com.mmall.util.Const;
import com.mmall.util.ResponseCode;
import com.mmall.util.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by geely
 */

@Api(tags = "购物车相关的api", description = "/cart")
@RestController
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    private ICartService iCartService;

    @Autowired
    private IUserService iUserService;


    @RequestMapping("list")
    @ApiOperation("获取当前购物车商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token信息", required = true,dataType = "String"),
    })
    public ServerResponse<CartVo> list(String token){
        User user = iUserService.getUser(token);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.list(user.getId());
    }


    @ApiOperation("向当前购物车添加商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token信息", required = true,dataType = "String"),
            @ApiImplicitParam(name = "count", value = "商品数量", required = true,dataType = "String"),
            @ApiImplicitParam(name = "productId", value = "商品Id", required = true,dataType = "String"),
            
    })
    @RequestMapping("add")
    public ServerResponse<CartVo> add(String token, Integer count, Integer productId){
        User user = iUserService.getUser(token);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.add(user.getId(),productId,count);
    }



    @ApiOperation("更改当前购物车商品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token信息", required = true,dataType = "String"),
            @ApiImplicitParam(name = "count", value = "商品数量要修改的值", required = true,dataType = "String"),
            @ApiImplicitParam(name = "productId", value = "商品Id", required = true,dataType = "String"),

    })
    @RequestMapping("update")
    public ServerResponse<CartVo> update(String token,  Integer count, Integer productId){
        User user = iUserService.getUser(token);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.update(user.getId(),productId,count);
    }


    @ApiOperation("删除当前购物车商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token信息", required = true,dataType = "String"),
            @ApiImplicitParam(name = "productIds", value = "要删除的商品Id, 如果多个用逗号分隔", required = true,dataType = "String"),

    })
    @RequestMapping("delete_product.do")
    public ServerResponse<CartVo> deleteProduct(String token,String productIds){
        User user = iUserService.getUser(token);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.deleteProduct(user.getId(),productIds);
    }


    @ApiOperation("选择当前购物车所有商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token信息", required = true,dataType = "String"),

    })
    @RequestMapping("select_all")
    public ServerResponse<CartVo> selectAll(String token){
        User user = iUserService.getUser(token);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrUnSelect(user.getId(),null,Const.Cart.CHECKED);
    }


    @ApiOperation("取消选择当前购物车所有商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token信息", required = true,dataType = "String"),

    })
    @RequestMapping("un_select_all")
    public ServerResponse<CartVo> unSelectAll(String token){
        User user = iUserService.getUser(token);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrUnSelect(user.getId(),null,Const.Cart.UN_CHECKED);
    }


    @ApiOperation("将购物车商品变为选中状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token信息", required = true,dataType = "String"),
            @ApiImplicitParam(name = "productId", value = "商品ID", required = true,dataType = "Integer"),

    })
    @RequestMapping("select.do")
    public ServerResponse<CartVo> select(String token,Integer productId){
        User user = iUserService.getUser(token);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrUnSelect(user.getId(),productId,Const.Cart.CHECKED);
    }


    @ApiOperation("将购物车商品取消选中")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token信息", required = true,dataType = "String"),
            @ApiImplicitParam(name = "productId", value = "商品ID", required = true,dataType = "Integer"),

    })
    @RequestMapping("un_select")
    @ResponseBody
    public ServerResponse<CartVo> unSelect(String token,Integer productId){
        User user = iUserService.getUser(token);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.selectOrUnSelect(user.getId(),productId,Const.Cart.UN_CHECKED);
    }



    @ApiOperation("获取当前购物车商品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token信息", required = true,dataType = "String"),

    })
    @RequestMapping("get_cart_product_count.do")
    @ResponseBody
    public ServerResponse<Integer> getCartProductCount(String token){
        User user = iUserService.getUser(token);
        if(user ==null){
            return ServerResponse.createBySuccess(0);
        }
        return iCartService.getCartProductCount(user.getId());
    }




    //全选
    //全反选

    //单独选
    //单独反选

    //查询当前用户的购物车里面的产品数量,如果一个产品有10个,那么数量就是10.




}
