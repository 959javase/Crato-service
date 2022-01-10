package com.nash.product.request;

import lombok.*;

/**
 * Description：
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/28 1:19 下午
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString
public class BaseRequest {
    /**
     * 语言
     */
    private String lang;
    /**
     * 登陆账号名称
     */
    private String token;
}
