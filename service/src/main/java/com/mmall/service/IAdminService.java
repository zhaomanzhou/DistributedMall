package com.mmall.service;


import com.mmall.bean.po.User;
import com.mmall.util.ServerResponse;

/**
 * Created by weiqiang
 */

public interface IAdminService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<User> update(String old, String new1,String new2,int userId);

}
