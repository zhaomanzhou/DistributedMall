package com.mmall.service.imp;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;
import com.mmall.bean.po.Admin;
import com.mmall.bean.po.User;
import com.mmall.mapper.AdminMapper;
import com.mmall.service.IAdminService;
import com.mmall.service.IUserService;
import com.mmall.util.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by weiqiang
 */

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private StringRedisTemplate redis;

    @Override
    public ServerResponse login(String username, String password) {
        Admin admin = adminMapper.selectByUser(username);

        if (admin == null) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);

        if (!admin.getPasswd().equals(md5Password)) {
            return ServerResponse.createByErrorMessage("密码错误");
        }

        admin.setPasswd(StringUtils.EMPTY);
        String json = JsonUtil.obj2String(admin);
        String token = null;
        try {
            token = JwtToken.createToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<>();
        map.put("mes", "登陆成功");
        map.put("Token", token);
        redis.opsForValue().set(token, json, 30, TimeUnit.MINUTES);
        return ServerResponse.createBySuccess(map);
    }

    @Override
    public ServerResponse<User> update(String old, String new1, String new2, int userId) {
        if (userId == 0)
            return ServerResponse.createByErrorMessage("未登录");
        Admin admin = adminMapper.selectByPrimaryKey(userId);
        if (StringUtils.isBlank(admin.getPasswd()) || !admin.getPasswd().equals(MD5Util.MD5EncodeUtf8(old)))
            return ServerResponse.createByErrorMessage("原密码错误");
        if (StringUtils.isBlank(new1) || StringUtils.isBlank(new2) || !new1.equals(new2))
            return ServerResponse.createByErrorMessage("两次密码不一致");
        admin.setPasswd(MD5Util.MD5EncodeUtf8(new1));
        int ok = adminMapper.updateByPrimaryKeySelective(admin);
        if (ok > 0)
            return ServerResponse.createBySuccessMessage("密码修改成功");
        return ServerResponse.createByErrorMessage("密码修改失败");
    }
}
