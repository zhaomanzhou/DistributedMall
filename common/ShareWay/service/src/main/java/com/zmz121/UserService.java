package com.zmz121;


import com.zmz121.dao.UserMapper;
import com.zmz121.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService
{
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据openid判断此人是否登陆过
     * @param openId
     * @return
     */
    public boolean hasLogined(String openId)
    {
        User user = userMapper.selectByOpenId(openId);
        if(user == null)
            return false;
        else
            return true;
    }

    @Transactional
    public void addUser(User u)
    {
        userMapper.insertSelective(u);
    }


    public Long selectIdByOpenid(String openId)
    {
        return userMapper.selectIdByOpenid(openId);
    }
}
