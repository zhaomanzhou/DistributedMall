package com.zmz121;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ValueTest
{
    @Value("${project.imageslocation}")
    private String location;


    @Test
    public void testValue()
    {
        System.out.println(location);
    }

}