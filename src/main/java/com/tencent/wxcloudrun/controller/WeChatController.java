package com.tencent.wxcloudrun.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class WeChatController {
    private String appid = "wx5f144075465f35d4";
    private String secret = "a7833d103b703b0c15420ed79aac3457";


    /**
     * 获取access_token
     *
     * @return
     */
    @GetMapping("getWtoken")
    public String getAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type={grant_type}&appid={appid}&secret={secret}";
        RestTemplate restTemplate = new RestTemplate();
        HashMap map = new HashMap();
        map.put("grant_type","client_credential");
        map.put("appid","wx5f144075465f35d4");
        map.put("secret","a7833d103b703b0c15420ed79aac3457");
        ResponseEntity forEntity = restTemplate.getForEntity(url, String.class, map);
        JSONObject jsonObject = JSONObject.parseObject(forEntity.getBody().toString());
        String access_token = jsonObject.getString("access_token");
        return access_token;
    }

    @GetMapping("getPhone")
    public String getUserPhone(String path) {
        RestTemplate restTemplate = new RestTemplate();
        String accessToken = getAccessToken();
        String url = "https://api.weixin.qq.com/wxa/getwxacode?access_token=" + accessToken + "&path={path}";
        HashMap map = new HashMap();
        map.put("path" , path);
        ResponseEntity<String> phoneInfo = restTemplate.postForEntity(url, map, String.class);


        return phoneInfo.getBody();

    }


}
