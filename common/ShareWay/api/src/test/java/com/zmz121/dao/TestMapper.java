package com.zmz121.dao;


import com.zmz121.model.po.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMapper
{
    @Autowired
    private UserMapper userMapper;


    @Test
    public void test01()
    {
        Assert.assertNotNull(userMapper);
    }


    @Test
    public void test02()
    {
        Assert.assertNotNull(userMapper);
        User u = new User();
        u.setName("Tom");
        u.setTel("13772036095");
        System.out.println(userMapper);
        //userMapper.insert(u);
        userMapper.selectByPrimaryKey(1L);
    }
}
