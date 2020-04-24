package com.zmz121.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;



public class JwtutilTest
{


    @Test
    public void generateToken()
    {
        Map<String , Object> map = new HashMap<>();
        map.put("k1", "v1");
        System.out.println(Jwtutil.generateToken(map, (long)1000*60*2, "nwafu"));
    }

    @Test
    public void testValidate() throws InterruptedException
    {
        Map<String , Object> map = new HashMap<>();
        map.put("k1", "v1");
        String token = Jwtutil.generateToken(map, (long) 1, "nwafu");
        Thread.sleep(100L);
        Claims body = Jwts.parser()
                .setSigningKey("nwafu")
                .parseClaimsJws(token)
                .getBody();
    }
}