package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.config.JWTBase;
import com.tencent.wxcloudrun.config.JWTUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class JwtController extends JWTBase {


    @RequestMapping("/setToken")
    public ApiResponse setToken(String name, String id) {
        System.out.println(id);
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("id", id);
        String token = JWTUtils.getToken(map);
        return ApiResponse.ok(token);
    }

    //验证token是否合法
    @RequestMapping("/verify")
    public void verify(HttpServletRequest request) {
        String token = request.getHeader("token");
        JWTUtils.verify(token);
    }



    //利用token获取token的信息
    @RequestMapping("/getToken")
    public ApiResponse getToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<String, String> info = JWTUtils.getTokenInfo(token);
        return ApiResponse.ok(info);
    }
}
