package com.mmall.control;

import com.github.pagehelper.PageInfo;
import com.mmall.bean.po.Product;
import com.mmall.bean.vo.ProductDetailVo;
import com.mmall.common.Common;
import com.mmall.service.IProductService;
import com.mmall.util.ServerResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

/**
 * Created by weiqiang
 */

@RestController
@RequestMapping("/manager/product/")
@ApiOperation("后台商品管理模块")
public class ProductController {

    @Autowired
    private Common common;
    @Autowired
    private IProductService iProductService;

    @ApiOperation(value = "获取文章详细信息")
    @ApiImplicitParam(name = "productId", value = "商品id", required = true, paramType = "path", dataType = "int")
    @GetMapping("detil.do/{productId}")
    public ServerResponse<ProductDetailVo> detailRESTful(@PathVariable Integer productId) {
        return iProductService.getProductDetail(productId);
    }

    @ApiOperation(value = "根据条件获取内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "关键字", dataType = "String"),
            @ApiImplicitParam(name = "categoryId", value = "分类的id", dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "分类的id，默认为1", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页数据的数量,默认为10", dataType = "int"),
            @ApiImplicitParam(name = "orderBy", value = "排序,price_desc或者price_asc", dataType = "String")
    })
    @GetMapping("list.do")
    public ServerResponse<PageInfo> list(@RequestParam(value = "keyword", required = false) String keyword,
                                         @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "orderBy", defaultValue = "") String orderBy) {
        return iProductService.getProductByKeywordCategory(keyword, categoryId, pageNum, pageSize, orderBy);
    }


    @ApiOperation(value = "添加商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "category_id", value = "分类id,对应mmall_category表的主键", dataType = "int"),
            @ApiImplicitParam(name = "name", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "subtitle", value = "商品副标题", dataType = "String"),
            @ApiImplicitParam(name = "price", value = "价格,单位-元保留两位小数", dataType = "double"),
            @ApiImplicitParam(name = "stock", value = "库存数量", dataType = "int")
    })
    @PostMapping("uodate.do")
    public ServerResponse add(@RequestParam("category_id") int category_id,
                              @RequestParam("name") String name,
                              @RequestParam("subtitle") String subtitle,
                              @ApiParam(value = "主图", required = true) @RequestParam(value = "main_image") MultipartFile main_image,
                              @ApiParam(value = "小图1") @RequestParam(value = "sub_images_1", required = false) MultipartFile sub_images_1,
                              @ApiParam(value = "小图2") @RequestParam(value = "sub_images_2", required = false) MultipartFile sub_images_2,
                              @ApiParam(value = "小图3") @RequestParam(value = "sub_images_3", required = false) MultipartFile sub_images_3,
                              @ApiParam(value = "大图1", required = true) @RequestParam(value = "detail_1") MultipartFile detail_1,
                              @ApiParam(value = "大图2") @RequestParam(value = "detail_2", required = false) MultipartFile detail_2,
                              @ApiParam(value = "大图3") @RequestParam(value = "detail_3", required = false) MultipartFile detail_3,
                              @ApiParam(value = "大图4") @RequestParam(value = "detail_4", required = false) MultipartFile detail_4,
                              @RequestParam("price") BigDecimal price,
                              @RequestParam("stock") int stock) {
//        小图的汇总，可以为空
        List<MultipartFile> sub_images = new ArrayList<>();
            sub_images.add(sub_images_1);
            sub_images.add(sub_images_2);
            sub_images.add(sub_images_3);
//        大图的汇总，detail_1必不为空
        List<MultipartFile> details = new ArrayList<>();
        details.add(detail_1);
            details.add(detail_2);
            details.add(detail_3);
            details.add(detail_4);
//        数据的实体化
        Product product = new Product();
        product.setCategoryId(category_id);
        product.setName(name);
        product.setPrice(price);
        product.setStatus(1);
        product.setStock(stock);
        product.setSubtitle(subtitle);
        return iProductService.saveProductDetail(product, main_image, sub_images, details);
    }


    @ApiOperation(value = "修改商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "product_id", value = "商品id", required = true,dataType = "int"),
            @ApiImplicitParam(name = "category_id", value = "分类id,对应mmall_category表的主键", dataType = "int"),
            @ApiImplicitParam(name = "name", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "subtitle", value = "商品副标题", dataType = "String"),
            @ApiImplicitParam(name = "price", value = "价格,单位-元保留两位小数", dataType = "double"),
            @ApiImplicitParam(name = "status", value = "商品状态.1-在售 2-下架", dataType = "int"),
            @ApiImplicitParam(name = "stock", value = "库存数量", dataType = "int")
    })
    @PutMapping("uodate.do")
    public ServerResponse<ProductDetailVo> update(@RequestParam(value = "product_id") int product_id,
                                                  @RequestParam(value = "category_id", required = false,defaultValue = "-1") int category_id,
                                                  @RequestParam(value = "name", required = false) String name,
                                                  @RequestParam(value = "subtitle", required = false) String subtitle,
                                                  @RequestParam(value = "price", required = false,defaultValue = "-1") BigDecimal price,
                                                  @RequestParam(value = "status", required = false,defaultValue = "-1") int status,
                                                  @RequestParam(value = "stock", required = false,defaultValue = "-1") int stock) {
        Product product = new Product();
        product.setId(product_id);
        product.setCategoryId(category_id==-1?null:category_id);
        product.setName(name);
        product.setPrice(price.compareTo(new BigDecimal(-1))==0?null:price);
        product.setStock(stock==-1?null:stock);
        product.setStatus(status==-1?null:status);
        product.setSubtitle(subtitle);
        return iProductService.updateProductDetail(product);
    }

    @ApiOperation(value = "删除商品")
    @ApiImplicitParam(name = "productId", value = "商品id", required = true, dataType = "int")
    @DeleteMapping("delete.do")
    public ServerResponse<ProductDetailVo> upddate(@RequestParam("productId") Integer productId) {

        return null;
    }
}
