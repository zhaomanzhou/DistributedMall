package com.mmall.common;

import com.mmall.bean.po.User;
import com.mmall.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by weiqiang
 */
@Component
@Slf4j
public class Common {

    @Autowired
    private StringRedisTemplate redis;
    public  int getid(HttpServletRequest request) {
        String header = request.getHeader("Token");
        if (StringUtils.isBlank(header)) {
            return 0;
        }
        String token = redis.opsForValue().get(header);
        User user = JsonUtil.string2Obj(token, User.class);
        if (user == null) {
            return 0;
        }
        return user.getId();
    }

    public  User getUser(HttpServletRequest request) {
        String header = request.getHeader("Token");
        if (StringUtils.isBlank(header)) {
            return null;
        }
        String token = redis.opsForValue().get(header);
        return JsonUtil.string2Obj(token, User.class);
    }
}
