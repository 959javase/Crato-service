package com.nash.product.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Description：
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/28 2:10 下午
 */
@Slf4j
@Component
public class AliyunUtils {
    private static String accessKeyId = "";

    private static String accessSecret = "";

    private RedisUtils redisUtil;

    public String sendVerifySms(String phoneNumber) {
        String status = "";
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("");
        request.setVersion("2017-05-25");
        request.setAction("");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "Livactor");
        request.putQueryParameter("TemplateCode", "SMS_180047400");
        // 6位验证码
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        String code = str.toString();
        JsonObject inputParam = new JsonObject();
        inputParam.addProperty("code", code);
        request.putQueryParameter("TemplateParam", inputParam.toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info(JSON.toJSONString(response));
            // 返回值校验
            if (response.getHttpStatus() == 200) {
                JSONObject respData = JSON.parseObject(response.getData());
                if (respData.getString("Code").equals("OK")) {
                    status = "success";
                    // redis 设置验证码有效时间为1分钟
                    Boolean res = redisUtil.set("aliyun_redis_phone_verifier_" + phoneNumber, code, 300);
                    log.info(String.format("set verifier code effective time result, phone: %s, smscode: %s",
                            phoneNumber, res));
                }
                else {
                    status = respData.getString("Message");
                }
            }
            else {
                log.error(String.format("get aliyun send sms request failed, phone: %s, httpcode: %s", phoneNumber,
                        String.valueOf(response.getHttpStatus())));
                status = "请求失败";
            }
        }
        catch (ServerException e) {
            log.error(String.format("send phone verifier code exception: %s", e.getErrMsg()));
            e.printStackTrace();
        }
        catch (ClientException e) {
            log.error(String.format("send phone verifier code exception: %s", e.getErrMsg()));
            e.printStackTrace();
        }

        return status;
    }
}
