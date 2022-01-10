package com.nash.product.entity.crato;

import lombok.Data;

@Data
public class Order {
    private String user;
    private Double amount;
    private String orderNo;
    private String orderDesc;
}
