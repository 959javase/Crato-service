package com.nash.product.weixinPay.service;

import com.nash.product.weixinPay.domain.PayOrder;
import com.nash.product.weixinPay.domain.PayRequest;

public interface WeixinPayService {
    public String nativePay(PayOrder order) throws Exception;
    public int payed(String orderNo);
    public int updatePayOrder(PayOrder order);
    public String querystateByNo(String orderNo);



}
