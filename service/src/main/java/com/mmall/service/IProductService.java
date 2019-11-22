package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.bean.po.Product;
import com.mmall.bean.vo.ProductDetailVo;
import com.mmall.util.ServerResponse;

/**
 * Created by weiqiang
 */
public interface IProductService {

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);



}
