package com.mmall.service.imp;


import com.mmall.bean.po.Category;
import com.mmall.mapper.CategoryMapper;
import com.mmall.service.ICartService;
import com.mmall.service.ICategoryService;
import com.mmall.util.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by weiqiang
 */
@Service("iCartService")
@Slf4j
public class CartServiceImpl implements ICartService {


    @Autowired
    private CategoryMapper categoryMapper;


}
