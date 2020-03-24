package com.zmz.service;


import com.zmz.entity.po.User;
import com.zmz.response.error.BusinessException;


public interface IUserService {

    String login(String username, String password) throws BusinessException;
    User register(User user) throws BusinessException;
    User getUserByToken(String token);
    User selectUserById(Integer userId) throws BusinessException;
    User updateInformation(User user) throws BusinessException;
    void resetPassword(String passwordOld,String passwordNew,User user) throws BusinessException;
    String selectQuestion(String username) throws BusinessException;

}
