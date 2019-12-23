package com.mmall.control;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

public class UserControllerTest
{

    @Test
    public void login() throws Exception
    {
        UserController userController = new UserController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"));

    }

    @Test
    public void register()
    {
    }
}