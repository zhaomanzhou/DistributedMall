package com.mmall.control;


import com.mmall.util.JsonUtil;
import com.mmall.util.ServerResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ServerResponseTest
{

    @Test
    public void test01()
    {
        System.out.println(JsonUtil.obj2String(ServerResponse.createBySuccess()));
    }
}