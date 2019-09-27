package com.mmall.service;

import com.mmall.dao.bean.Product;
import com.mmall.dao.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    @Autowired
    private ProductMapper mapper;

    public Product getById(int id){
        return mapper.selectByPrimaryKey(id);
    }
}
