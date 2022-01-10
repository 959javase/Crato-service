package com.nash.product.weixinPay.domain;

import lombok.Data;

import java.util.Map;

@Data
public class NotifyRequest {
    private String event_type;
    private ResourceEntity resource;
    private String summary;
}
