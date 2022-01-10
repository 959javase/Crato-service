package com.nash.product.request.account;

import com.nash.product.request.BaseRequest;
import lombok.*;

/**
 * Description：登陆账号
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/21 7:57 下午
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString
public class SignInRequest extends BaseRequest {
    /**
     * 账号名称
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 验证码
     */
    private String captcha;
    private String phonecode;
    private String uuid;
}
