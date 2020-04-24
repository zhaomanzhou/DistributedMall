package com.zmz121.Controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmz121.UserService;
import com.zmz121.model.po.User;
import com.zmz121.util.HttpClientUtil;
import com.zmz121.util.Jwtutil;
import constvalue.WxConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import util.CommonReturnType;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WxLoginController
{
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;



    @PostMapping("/wxLogin")
    @ResponseBody
    public Map<String, String> wxLogin(@RequestParam("code") String code, @RequestParam("nickname") String nickName,
                                    @RequestParam("gender") Integer gender, @RequestParam("avatarUrl") String avatarUrl
                                    ) throws IOException
    {
        logger.info(code);
        Map<String, String> map = new HashMap<>();
        map.put("appid", WxConst.appid);
        map.put("secret", WxConst.appsecret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String wxResult = HttpClientUtil.doGet(WxConst.Code2SessionUrl, map);
        //{"session_key":"nnEQG35eCBroMaolPVgloQ==","openid":"onNvb4pB3A1Oletx8UbTK-B_LVk8"}
        logger.info(wxResult);


        //解析返回结果
        ObjectMapper om = new ObjectMapper();
        JsonNode jsonNode = om.readTree(wxResult);
        String session_key = jsonNode.get("session_key").asText();
        String openid = jsonNode.get("openid").asText();


        //检查是否为第一次登陆
        boolean firstLogin = userService.hasLogined(openid);
        if(!firstLogin)
        {
            User u = new User();
            u.setOpenId(openid);
            u.setNickname(nickName);
            u.setGender(Byte.parseByte(gender.toString()));
            u.setHeadPhoto(avatarUrl);
            userService.addUser(u);
        }


        Map<String, Object> tokenMap = new HashMap<>();
        map.put("nickname", nickName);
        map.put("openid", openid);
        String token = Jwtutil.generateToken(tokenMap);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("token", token);
        resultMap.put("user_id", userService.selectIdByOpenid(openid) + "");

        return resultMap;

    }

}
