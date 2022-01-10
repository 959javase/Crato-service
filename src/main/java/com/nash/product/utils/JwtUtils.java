package com.nash.product.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.nash.product.constants.CommonConstant;
import com.nash.product.constants.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Description：
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/19 6:07 下午
 */
@Slf4j
@Component
public class JwtUtils {
    /**
     * jwt过期时间
     */
    @Value("${jwt.expiration}")
    private int expiration;

    /**
     * jwt过期时间
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 加密前缀
     */
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     签发对象：这个用户的Id
     签发时间：现在
     有效时间：30分钟
     载荷内容：暂时设计为：这个人的名字，这个人的昵称
     加密密钥：这个人的id加上一串字符串
     */
    public String createToken(Long userId, String userName) {

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, expiration);
        Date expiresDate = nowTime.getTime();

        return JWT.create().withAudience(userId.toString())   //签发对象
                .withIssuedAt(new Date())    //发行时间
                .withExpiresAt(expiresDate)  //有效时间
                .withSubject(CommonConstant.SUBJECT)
                .withClaim(CommonConstant.USER_NAME, userName)
                .sign(Algorithm.HMAC256(tokenHead + userId));   //加密
    }

    /**
     * 检验合法性，其中secret参数就应该传入的是用户的id
     * @param token
     * @throws RuntimeException
     */
    public void verifyToken(String token, String secret) throws RuntimeException {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(tokenHead + secret)).build();
            verifier.verify(token);
        } catch (Exception e) {
            e.printStackTrace();
            //效验失败
            //这里抛出的异常是我自定义的一个异常，你也可以写成别的
            throw new RuntimeException(ErrorCodeEnum.getDescription(ErrorCodeEnum.TOKEN_INVALID, getClaimByName(token, CommonConstant.LANG).asString()));
        }
    }

    /**
     * 获取签发对象
     */
    public String getAudience(String token) throws RuntimeException {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            //这里是token解析失败
            throw new RuntimeException(ErrorCodeEnum.getDescription(ErrorCodeEnum.TOKEN_EXCEPTION, getClaimByName(token, CommonConstant.LANG).asString()));
        }
        return audience;
    }

    /**
     * 通过载荷名字获取载荷的值
     */
    public Claim getClaimByName(String token, String name){
        return JWT.decode(token).getClaim(name);
    }
}
