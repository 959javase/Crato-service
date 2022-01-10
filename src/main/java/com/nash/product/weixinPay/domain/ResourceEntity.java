package com.nash.product.weixinPay.domain;

import lombok.Data;

/***
 * 详见 https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_4_5.shtml
 */
@Data
public class ResourceEntity {
    private String algorithm;
    private String ciphertext;
    private String associated_data;
    private String original_type;
    private String nonce;
}
