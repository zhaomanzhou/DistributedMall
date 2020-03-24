package com.zmz.service;


import com.zmz.entity.po.Category;
import com.zmz.response.error.BizException;
import com.zmz.response.error.BusinessException;

import java.util.List;

/**
 * Created by geely
 */
public interface ICategoryService {
    void addCategory(String categoryName, Integer parentId) throws BusinessException, BizException;
    void updateCategoryName(Integer categoryId, String categoryName) throws BizException;
    List<Category> getChildrenParallelCategory(Integer categoryId) ;
    List<Integer> selectCategoryAndChildrenById(Integer categoryId);

}
