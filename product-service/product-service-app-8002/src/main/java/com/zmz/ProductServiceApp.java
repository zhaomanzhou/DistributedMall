package com.zmz;

import com.github.pagehelper.PageInfo;
import com.zmz.entity.po.Product;
import com.zmz.entity.vo.ProductListVo;
import com.zmz.service.IProductService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @author zhaomanzhou
 */

@SpringBootApplication
@EnableDubbo
@MapperScan("com.zmz.mapper")
public class ProductServiceApp implements CommandLineRunner
{
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private IProductService productService;


    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        List<Product> products = productService.selectList();
        for(Product product: products)
        {
            redisTemplate.opsForValue().set("product_stock", product.getStock());
        }
    }
}
