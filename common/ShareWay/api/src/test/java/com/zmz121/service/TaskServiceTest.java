package com.zmz121.service;

import com.zmz121.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskServiceTest
{

    @Autowired
    private TaskService taskService;

    @Test
    public void queryAllSimple()
    {
        System.out.println(taskService.queryAllSimple());
        System.out.println(taskService.queryAllSimple().size());
    }
}
