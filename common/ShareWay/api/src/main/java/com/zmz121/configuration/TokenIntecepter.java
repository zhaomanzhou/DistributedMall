package com.zmz121.configuration;

import com.zmz121.util.Jwtutil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenIntecepter implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String token = request.getHeader("token");
        if(!Jwtutil.isValidate(token))
        {
            request.getRequestDispatcher("/api/unlogin").forward(request, response);
            return false;
        }
        return true;
    }
}
