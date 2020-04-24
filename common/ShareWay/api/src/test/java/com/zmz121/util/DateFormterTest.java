package com.zmz121.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import util.DateFormter;

import java.util.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DateFormterTest
{
    @Autowired
    private DateFormter formter;

    @Test
    public void test01()
    {
        System.out.println(formter.fomat(new Date()));
    }
}
