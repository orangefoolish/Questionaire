package com.example.classproject.Intercepter;

import com.alibaba.fastjson.JSONObject;
import com.example.classproject.Pojo.JWTobject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(JWTobject.vertify(request.getHeader("token"))){
            return true;
        }else{
            System.out.println("lj");
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            JSONObject json = new JSONObject();
            json.put("code", "401");
            json.put("msg", "登录信息出错，重新登陆！");
            pw.write(json.toJSONString());
            return false;
        }
    }
}
