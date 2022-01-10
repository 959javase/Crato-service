package com.nash.product.weixinPay.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/***
 * 详见https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_4_1.shtml
 */
@Data
public class PayRequest {
    private String appid;
    private String mchid;
    private String description;
    private String out_trade_no;
    private String time_expire;
    private String attach;
    private String notify_url;
    private String goods_tag;
    private Map<String,Object> amount;
    private Map<String,Object> detail;
    private Map<String,Object> scene_info;
    private Map<String,Object> settle_info;

    public PayRequest(){
    }
    public PayRequest(String order,Double amount,String description, String timeExpire){
        Map<String,Object> amountMap=new HashMap<String,Object>();
        amountMap.put("total", new Double(amount*100).intValue());
        amountMap.put("currency","CNY");
        this.out_trade_no=order;
        this.amount=amountMap;
        this.description=description;
        this.time_expire=timeExpire;
    }

}
