package com.tencent.wxcloudrun.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 *      1.getToken生成JWT token
 *      2.verify验证JWT合法性
 *      3.getTokenInfo 获取JWT加密数据
 */

@Data
public class JWTUtils {
    private static final String SIGN = "ADwdlkmdDMKS1";

    /**
     * 生成token
     * @param map 存放的数据
     * @return
     */
    public static String getToken(Map<String ,String> map){
        Calendar instance = Calendar.getInstance();
        //设置token默认2小时过期
        instance.add(Calendar.HOUR,2);
        JWTCreator.Builder builder = JWT.create();
        //数据
        map.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * 验证token的合法性
     * @param token
     */
    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    /**
     * 获取token数据
     * @param token
     * @return
     */
    public static Map<String,String> getTokenInfo(String token){
        HashMap<String, String> map = new HashMap<>();
        JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token).getClaims().forEach((k,v)->{
            map.put(k,v.asString());
        });
        return map;
    }

}
