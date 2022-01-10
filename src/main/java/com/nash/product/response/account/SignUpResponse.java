package com.nash.product.response.account;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Description：账号注册返回值
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/21 7:52 下午
 */
@RequiredArgsConstructor
@Getter
@Setter
@Value
public class SignUpResponse {
    /**
     * 用户ID
     */
    @NotNull
    private long userId;
}
