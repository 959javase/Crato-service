package com.nash.product.response.account;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;

/**
 * Description：登陆返回值
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/21 8:02 下午
 */
@AllArgsConstructor
@Value
public class SignInResponse {
    /**
     * 用户
     */
    @NotNull
    private long userId;
    /**
     * token
     */
    private String token;
}
