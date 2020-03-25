package com.zmz.service;

import com.github.pagehelper.PageInfo;
import com.zmz.entity.po.Product;
import com.zmz.entity.vo.ProductDetailVo;
import com.zmz.response.error.BizException;
import com.zmz.response.error.BusinessException;


public interface IProductService {

    void saveOrUpdateProduct(Product product) throws BizException;

    void setSaleStatus(Integer productId, Integer status) throws BusinessException, BizException;

    ProductDetailVo manageProductDetail(Integer productId) throws BusinessException, BizException;

    PageInfo getProductList(int pageNum, int pageSize);

    PageInfo searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    ProductDetailVo getProductDetail(Integer productId) throws BusinessException, BizException;

    PageInfo getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy) throws BusinessException;



}
