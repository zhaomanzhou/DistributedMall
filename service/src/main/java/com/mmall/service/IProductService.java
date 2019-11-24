package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.bean.po.Product;
import com.mmall.bean.vo.ProductDetailVo;
import com.mmall.util.ServerResponse;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
/**
 * Created by weiqiang
 */
public interface IProductService {

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);

    ServerResponse saveProductDetail(Product product, MultipartFile main_image,List<MultipartFile> sub_images, List<MultipartFile> details);

    ServerResponse deteleProduct(int  productId);

    ServerResponse<ProductDetailVo> updateProductDetail(Product product);
}
