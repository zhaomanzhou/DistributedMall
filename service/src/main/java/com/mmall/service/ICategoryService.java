package com.mmall.service;


import com.mmall.bean.po.Category;
import com.mmall.util.ServerResponse;
import java.util.List;

/**
 * Created by weiqiang
 */

public interface ICategoryService {
//    ServerResponse addCategory(String categoryName, Integer parentId);
//    ServerResponse updateCategoryName(Integer categoryId,String categoryName);
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
