package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SendEmailController {

    @Autowired
    private EmailService emailService;

    @Value("${spring.mail.key}")
    private String emailKey;


    @GetMapping("sendmail")
    public ApiResponse sendmail(@RequestParam("email") String email, @RequestParam("subject") String subject, @RequestParam("content") String content,@RequestParam("key") String key) {
        if (!key.equals(emailKey)){
            return ApiResponse.error("密钥错误");
        }

        int slug = 0;
        try {
            emailService.sendSimpleMail(email, subject, content);
            slug = 0;
        } catch (Exception e) {
            slug = -1;
            e.printStackTrace();
        }
        if (slug == 0) {
            return ApiResponse.ok("邮件发送成功");
        } else {
            return ApiResponse.error("邮件发送失败");
        }
    }
}
