package com.zmz121;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServlet;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FilleControllerTest
{
    @Autowired
    private HttpServlet servlet;

    @Test
    public void test01()
    {
        System.out.println(servlet.getServletContext().getContextPath());
    }
}
