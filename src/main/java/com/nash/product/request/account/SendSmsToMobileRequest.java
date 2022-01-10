package com.nash.product.request.account;

import com.nash.product.request.BaseRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Description：发送手机验证码
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/21 8:26 下午
 */
@NoArgsConstructor
@Data
public class SendSmsToMobileRequest extends BaseRequest {
    /**
     * 手机号
     */
    @NotBlank
    private String mobile;
}
