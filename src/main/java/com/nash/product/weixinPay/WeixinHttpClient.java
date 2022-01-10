package com.nash.product.weixinPay;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.security.PrivateKey;
@Component
public class WeixinHttpClient {
    @Autowired
    private WeixinConfig accountConfig;
    public  String doPost(String data,String url) throws Exception {
        CloseableHttpClient httpClient=null;
        URIBuilder uriBuilder = new URIBuilder(url);
        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type","application/json; charset=utf-8");
        // 加载商户私钥（privateKey：私钥字符串）
        System.out.println(String.valueOf(this.getClass().getResourceAsStream("/source")));
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
                new ClassPathResource(accountConfig.getPrivateKey()).getInputStream());
        // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(accountConfig.getMchId(), new PrivateKeySigner(accountConfig.getMchSerialNo(), merchantPrivateKey)),accountConfig.getApiV3Key().getBytes("utf-8"));
        // 初始化httpClient
        httpClient= WechatPayHttpClientBuilder.create()
                .withMerchant(accountConfig.getMchId(), accountConfig.getMchSerialNo(), merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier)).build();

        httpPost.setEntity(new StringEntity(data));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity());
    }
}
