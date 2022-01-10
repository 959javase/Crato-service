package com.nash.product.weixinPay.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nash.product.annotation.PassToken;
import com.nash.product.weixinPay.WxAPIV3AesUtil;
import com.nash.product.weixinPay.domain.NotifyRequest;
import com.nash.product.weixinPay.domain.PayOrder;
import com.nash.product.weixinPay.domain.ResultEntity;
import com.nash.product.weixinPay.service.WeixinPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/weixinPay")
public class WeixinPayController {
    @Autowired
    private WeixinPayService weixinPayService;

    @PassToken
    @PostMapping(value = "/native")
    public ResultEntity nativePay(@RequestBody PayOrder order) {
        String codeUrl = null;
        try {
            codeUrl = weixinPayService.nativePay(order);

        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.error("系统异常，稍后再试");
        }
        if (codeUrl != null) {
            return ResultEntity.success(codeUrl);
        } else {
            return ResultEntity.error("参数错误");
        }
    }

    @PassToken
    @GetMapping("queryOrderState")
    public ResultEntity queryOrderState(String orderNo) {
        return ResultEntity.success(weixinPayService.querystateByNo(orderNo));
    }

    /***
     * 支付回调
     * @param request
     * @return
     */
    @PassToken
    @PostMapping(value = "/notify")
    public ResultEntity notify(@RequestBody NotifyRequest request) {
        if (request.getResource() != null && "TRANSACTION.SUCCESS".equals(request.getEvent_type())) {
            JSONObject object = null;
            System.out.println(JSON.toJSONString(request));
            //解密回调信息
            try {
                String decryptToString = WxAPIV3AesUtil.aesgcmDecrypt(request.getResource().getAssociated_data(), request.getResource().getNonce()
                        , request.getResource().getCiphertext());
                System.out.println(decryptToString);
                JSONObject data = JSON.parseObject(decryptToString);
                PayOrder order = new PayOrder();
                order.setOrderNo(data.getString("out_trade_no"));
                order.setTradeType(data.getString("trade_type"));
                order.setTradeState(data.getString("trade_state"));
                order.setBankType(data.getString("bank_type"));
                order.setTransactionId(data.getString("transaction_id"));
                order.setSuccessTime(data.getString("success_time"));

                int result = weixinPayService.updatePayOrder(order);
                if ("SUCCESS".equals(order.getTradeState())) {
                    return new ResultEntity("SUCCESS", "成功");
                } else {
                    return ResultEntity.error("unpaid");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResultEntity.error("system error");
            }
        } else {
            return ResultEntity.error("system error");
        }
    }
}
