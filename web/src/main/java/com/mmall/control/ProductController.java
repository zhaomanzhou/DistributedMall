package com.mmall.control;

import com.github.pagehelper.PageInfo;
import com.mmall.bean.vo.ProductDetailVo;
import com.mmall.service.IProductService;
import com.mmall.util.ServerResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by weiqiang
 */

@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;


    @ApiOperation(value = "获取文章详细信息" )
    @ApiImplicitParam(name = "productId", value = "商品id", required = true, dataType = "int")
    @RequestMapping("detail.do")
    public ServerResponse<ProductDetailVo> detail(Integer productId){
        return iProductService.getProductDetail(productId);
    }


    @ApiOperation(value = "获取文章详细信息" )
    @ApiImplicitParam(name = "productId", value = "商品id", required = true,paramType = "path", dataType = "int")
    @RequestMapping( "/{productId}")
    public ServerResponse<ProductDetailVo> detailRESTful(@PathVariable Integer productId){
        return iProductService.getProductDetail(productId);
    }

    @ApiOperation(value = "根据条件获取内容" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String"),
            @ApiImplicitParam(name = "categoryId", value = "分类的id", dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "分类的id，默认为1", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据的数量", dataType = "int"),
            @ApiImplicitParam(name = "orderBy", value = "排序,price_desc或者price_asc", dataType = "String")
    })
    @PostMapping("list.do")
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword",required = false)String keyword,
                                         @RequestParam(value = "categoryId",required = false)Integer categoryId,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                         @RequestParam(value = "orderBy",defaultValue = "") String orderBy){
        return iProductService.getProductByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);
    }

}
