package com.mmall.control;

import com.mmall.service.ICategoryService;
import com.mmall.util.ServerResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by weiqiang
 */
@RestController
@RequestMapping("/category/")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    //根据父分类获取分类


    @ApiOperation(value = "获取分类" )
    @ApiImplicitParam(name = "categoryId", value = "分类id默认为0",  dataType = "int")
    @GetMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(@RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
        return iCategoryService.getChildrenParallelCategory(categoryId);
    }


}
