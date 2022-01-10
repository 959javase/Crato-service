package com.nash.product.weixinPay.service.impl;

import com.alibaba.fastjson.JSON;
import com.nash.product.entity.crato.Order;
import com.nash.product.utils.DateUtils;
import com.nash.product.weixinPay.WeixinHttpClient;
import com.nash.product.weixinPay.domain.PayOrder;
import com.nash.product.weixinPay.domain.PayRequest;
import com.nash.product.weixinPay.WeixinConfig;
import com.nash.product.weixinPay.mapper.WeixinPayMapper;
import com.nash.product.weixinPay.service.WeixinPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class WeixinPayServiceImpl implements WeixinPayService {
    @Autowired
    private WeixinConfig accountConfig;
    @Autowired
    private WeixinHttpClient  weixinHttpClient;
    @Autowired
    private WeixinPayMapper weixinPayMapper;
    @Override
    public String nativePay(PayOrder order) throws Exception {
        PayRequest request= new PayRequest(order.getOrderNo(),order.getAmount(),order.getDescription(),order.getTimeExpire());
//        Date current=new Date();
//        if (order.getTimeExpire()==null){
//            request.setTime_expire(DateUtils.plusMinutes(current,5).toString());
//        }else{
//
//        }
        request.setAppid(accountConfig.getAppId());
        request.setMchid(accountConfig.getMchId());
        request.setNotify_url(accountConfig.getNotifyUrl());
        String jsonReq=JSON.toJSONString(request);
        System.out.println("request:   "+jsonReq);
        String res=weixinHttpClient.doPost(jsonReq,"https://api.mch.weixin.qq.com/v3/pay/transactions/native");
        System.out.println("result"+res);
        String codeUrl=JSON.parseObject(res).getString("code_url");
        if (codeUrl!=null){
            weixinPayMapper.insert(order);
        }
        return codeUrl;
    }

    @Override
    public int payed(String orderNo) {
        return weixinPayMapper.updateStatus(orderNo);
    }

    @Override
    public int updatePayOrder(PayOrder order) {
        return weixinPayMapper.updatePayOrder(order);
    }

    @Override
    public String querystateByNo(String orderNo) {
        return weixinPayMapper.querystateByNo(orderNo);
    }
}
