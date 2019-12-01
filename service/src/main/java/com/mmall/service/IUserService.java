package com.mmall.service;


import com.mmall.bean.po.User;
import com.mmall.util.ServerResponse;

/**
 * Created by weiqiang
 */

public interface IUserService {

    ServerResponse<User> login(String username, String password);
    ServerResponse<String> register(User user);
    User getUser(String token);
    ServerResponse<User> getInformation(Integer userId);

}
