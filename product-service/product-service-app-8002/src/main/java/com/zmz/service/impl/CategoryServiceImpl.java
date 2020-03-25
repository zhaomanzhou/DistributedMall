package com.zmz.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zmz.entity.po.Category;
import com.zmz.exception.BusinessErrorEnum;
import com.zmz.mapper.CategoryMapper;
import com.zmz.response.error.BizException;
import com.zmz.response.error.BusinessException;
import com.zmz.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
@Component("iCategoryService")
@Slf4j
public class CategoryServiceImpl implements ICategoryService {


    @Resource
    private CategoryMapper categoryMapper;

    public void addCategory(String categoryName,Integer parentId) throws BusinessException, BizException {
        if(parentId == null || StringUtils.isBlank(categoryName)){
            throw new BizException("添加品类参数错误");
        }

        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);//这个分类是可用的

        int rowCount = categoryMapper.insert(category);
        if(rowCount <= 0){
            throw new BizException("添加品类失败");
        }

    }

    public void updateCategoryName(Integer categoryId,String categoryName) throws BizException {
        if(categoryId == null || StringUtils.isBlank(categoryName)){
            throw new BizException("更新品类参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount <= 0){
            throw new BizException("更新品类名字成功");
        }
    }

    public List<Category> getChildrenParallelCategory(Integer categoryId)  {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        return categoryList;
    }


    /**
     * 递归查询本节点的id及孩子节点的id
     * @param categoryId
     * @return
     */
    public List<Integer> selectCategoryAndChildrenById(Integer categoryId){
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);


        List<Integer> categoryIdList = Lists.newArrayList();
        if(categoryId != null){
            for(Category categoryItem : categorySet){
                categoryIdList.add(categoryItem.getId());
            }
        }
        return categoryIdList;
    }


    //递归算法,算出子节点
    private Set<Category> findChildCategory(Set<Category> categorySet ,Integer categoryId){
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(category != null){
            categorySet.add(category);
        }
        //查找子节点,递归算法一定要有一个退出的条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for(Category categoryItem : categoryList){
            findChildCategory(categorySet,categoryItem.getId());
        }
        return categorySet;
    }






}
