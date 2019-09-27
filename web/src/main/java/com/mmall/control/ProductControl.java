package com.mmall.control;

import com.mmall.dao.bean.Product;
import com.mmall.dao.mapper.ProductMapper;
import com.mmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductControl {
    @Autowired
    private ProductService service;


    @RequestMapping("product")
    public Product getById(){

        return service.getById(26);
    }

    @RequestMapping("a")
    public String s(){
        return "hello";
    }
}
