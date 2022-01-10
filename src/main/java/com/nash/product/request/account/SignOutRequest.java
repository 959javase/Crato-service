package com.nash.product.request.account;

import com.nash.product.request.BaseRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Description：登出账号
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/21 8:09 下午
 */
@NoArgsConstructor
@Data
public class SignOutRequest extends BaseRequest {
    @NotNull
    private String username;

    @NotNull
    private String token;
}
