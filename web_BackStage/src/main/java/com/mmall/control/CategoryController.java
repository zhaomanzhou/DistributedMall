package com.mmall.control;

import com.mmall.bean.po.Category;
import com.mmall.service.ICategoryService;
import com.mmall.util.ServerResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by weiqiang
 */
@RestController
@RequestMapping("/manager/category/")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    //根据父分类获取分类
    @ApiOperation(value = "获取分类")
    @ApiImplicitParam(name = "categoryId", value = "分类id默认为0", dataType = "int")
    @GetMapping("get_category.do")
    public ServerResponse getChildrenParallelCategory(@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        return iCategoryService.getChildrenParallelCategory(categoryId);
    }


    @ApiOperation(value = "删除分类")
    @ApiImplicitParam(name = "categoryId", value = "分类id", dataType = "int")
    @DeleteMapping("delete.do")
    public ServerResponse delete(@RequestParam(value = "categoryId") Integer categoryId) {
        return iCategoryService.deleteCategory(categoryId);
    }

    @ApiOperation(value = "修改分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "分类id", dataType = "int"),
            @ApiImplicitParam(name = "name", value = "分类名字", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "类别状态1-正常,2", dataType = "int")
    })
    @PutMapping("update.do")
    public ServerResponse update(@RequestParam(value = "categoryId") Integer categoryId,
                                 @RequestParam(value = "name",required = false) String name,
                                 @RequestParam(value = "status",required = false,defaultValue = "0") Integer status
                                 ) {
        Category category = new Category();
        category.setId(categoryId);
        category.setName(name);
        category.setStatus(status==0?null:status==1);
        return iCategoryService.updateCategory(category);
    }


}
