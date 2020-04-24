package com.zmz121.service;

import com.zmz121.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest
{

    @Autowired
    private UserService userService;

    @Test
    public void test01()
    {
        boolean ww = userService.hasLogined("ww");
        System.out.println(ww);
    }
}
