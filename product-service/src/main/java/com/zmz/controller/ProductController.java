package com.zmz.controller;

import com.github.pagehelper.PageInfo;

import com.zmz.entity.vo.ProductDetailVo;
import com.zmz.response.ServerResponse;
import com.zmz.response.error.BizException;
import com.zmz.response.error.BusinessException;
import com.zmz.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@Api("商品相关API")
@RestController
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;


    @ApiOperation(value = "根据id查询商品信息" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品id", required = true,dataType = "Integer"),
    })
    @GetMapping("/detail/{id}")
    public ServerResponse<ProductDetailVo> detail(@PathVariable("id") Integer productId) throws BizException, BusinessException {
        return ServerResponse.success(iProductService.getProductDetail(productId));
    }


    @ApiOperation(value = "根据条件查询商品列表" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "查询关键词", required = false,dataType = "Integer"),
            @ApiImplicitParam(name = "categoryId", value = "商品类别id", required = false,dataType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "分多少页返回", required = false,dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页列表数量", required = false,dataType = "Integer"),
            @ApiImplicitParam(name = "orderBy", value = "以什么方式排序", required = false,dataType = "Integer"),
    })

    @GetMapping("/list")
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword",required = false)String keyword,
                                         @RequestParam(value = "categoryId",required = false)Integer categoryId,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                         @RequestParam(value = "orderBy",defaultValue = "") String orderBy) throws BusinessException {
        PageInfo pageInfo = iProductService.getProductByKeywordCategory(keyword, categoryId, pageNum, pageSize, orderBy);
        return ServerResponse.success(pageInfo);
    }


}
