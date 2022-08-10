package com.tencent.wxcloudrun.config;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class JWTBase {
    @ExceptionHandler({JWTVerificationException.class})
    public ApiResponse handleException(Throwable e) {
        if (e instanceof SignatureVerificationException) {
            return ApiResponse.error("无效签名");
        } else if (e instanceof TokenExpiredException) {
            return ApiResponse.error("token过期");
        } else if (e instanceof AlgorithmMismatchException) {
            return ApiResponse.error("算法不一致");
        } else if (e instanceof Exception) {
            return ApiResponse.error("token无效");
        }
        return ApiResponse.error("jwt异常");
    }
}
