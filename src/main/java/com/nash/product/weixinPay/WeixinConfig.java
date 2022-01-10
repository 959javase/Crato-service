package com.nash.product.weixinPay;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@ConfigurationProperties(prefix = "wechat")
@Component
public class WeixinConfig {
    private  String privateKey;
    private  String mchId;
    private  String mchSerialNo;
    private  String apiV3Key;
    private  String appId;
    private  String notifyUrl;


}
