package com.nash.product.request.account;

import com.nash.product.request.BaseRequest;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * Description：Crato注册参数
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/21 7:42 下午
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString
public class SignUpRequest extends BaseRequest {
    /**
     * 账号名称
     */
    @NotBlank
    private String name;
    /**
     * 密码
     */
    @NotBlank
    private String password;
    /**
     * 确认密码
     */
    @NotBlank
    private String confirmPassword;
    /**
     * mobile
     */
    private String mobile;
    /**
     * 账号归属
     */
    private String belong;
    private String phonecode;
    private String uuid;
}
