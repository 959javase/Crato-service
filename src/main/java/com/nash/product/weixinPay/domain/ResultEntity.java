package com.nash.product.weixinPay.domain;

import lombok.Data;

@Data
public class ResultEntity {
    private String code;
    private String message;
    public static ResultEntity success(){
        return new ResultEntity("200","SUCCESS");
    }
    public static ResultEntity success(String message){
        return new ResultEntity("200",message);
    }
    public static ResultEntity error(String message){
        return new ResultEntity("500",message);
    }
    public ResultEntity(String code,String message){
        this.code=code;
        this.message=message;
    }
}
