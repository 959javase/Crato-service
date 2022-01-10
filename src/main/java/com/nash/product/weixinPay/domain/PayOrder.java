package com.nash.product.weixinPay.domain;

import lombok.Data;

import java.util.Date;
@Data
public class PayOrder {
    private String id;
    private String orderNo;
    private Double amount;
    private String status;
    private String user;
    private Date createTime;
    private String description;
    private String origin;
    private String timeExpire;
    private String successTime;
    private String tradeState;
    private String transactionId;
    private String bankType;
    private String tradeType;
}
