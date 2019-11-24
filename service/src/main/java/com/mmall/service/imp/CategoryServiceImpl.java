package com.mmall.service.imp;

import com.mmall.bean.po.Category;
import com.mmall.mapper.CategoryMapper;
import com.mmall.service.ICategoryService;
import com.mmall.util.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by weiqiang
 */

@Service("iCategoryService")
@Slf4j
@Transactional(rollbackFor = RuntimeException.class)
public class CategoryServiceImpl implements ICategoryService {


    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            log.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }


    /**
     * 递归查询本节点的id及孩子节点的id
     */
    @Override
    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {
        Set<Category> categorySet = new HashSet<>();
        findChildCategory(categorySet, categoryId);


        List<Integer> categoryIdList = new ArrayList<>();
        if (categoryId != null) {
            for (Category categoryItem : categorySet) {
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    @Override
    public ServerResponse<List<Integer>> deleteCategory(Integer categoryId) {
        Set<Category> categorySet = new HashSet<>();
        findChildCategory(categorySet, categoryId);
        for (Category category : categorySet) {
            if (categoryMapper.deleteByPrimaryKey(category.getId()) <= 0)
                return ServerResponse.createByErrorMessage("删除分类失败");
        }
        return ServerResponse.createBySuccessMessage("删除分类成功");
    }

    @Override
    public ServerResponse<List<Integer>> updateCategory(Category category) {
        int ok = categoryMapper.updateByPrimaryKeySelective(category);
        if (ok > 0)
            return ServerResponse.createBySuccessMessage("更新分类成功");
        return ServerResponse.createByErrorMessage("跟新分类失败");
    }

    //递归算法,算出子节点
    private void findChildCategory(Set<Category> categorySet, Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for (Category categoryItem : categoryList) {
            findChildCategory(categorySet, categoryItem.getId());
        }
    }

}