package com.mmall.service;


import com.mmall.bean.po.Category;
import com.mmall.util.ServerResponse;
import java.util.List;

/**
 * Created by weiqiang
 */

public interface ICategoryService {

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
