package com.mmall.control;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryControllerTest
{
    @Autowired
    private DataSource dataSource;


    @Test
    public void test01()
    {
        System.out.println(dataSource);
    }

}