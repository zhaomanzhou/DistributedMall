package com.mmall.control.portal;

import com.github.pagehelper.PageInfo;
import com.mmall.bean.vo.ProductDetailVo;
import com.mmall.service.IProductService;
import com.mmall.util.ServerResponse;
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



    @GetMapping("detail.do")
    public ServerResponse<ProductDetailVo> detail(Integer productId){
        return iProductService.getProductDetail(productId);
    }

    @GetMapping( "/{productId}")
    public ServerResponse<ProductDetailVo> detailRESTful(@PathVariable Integer productId){
        return iProductService.getProductDetail(productId);
    }


    @GetMapping("list.do")
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword",required = false)String keyword,
                                         @RequestParam(value = "categoryId",required = false)Integer categoryId,
                                         @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                         @RequestParam(value = "orderBy",defaultValue = "") String orderBy){
        return iProductService.getProductByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);
    }

}
