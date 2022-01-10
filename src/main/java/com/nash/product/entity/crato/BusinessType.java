package com.nash.product.entity.crato;

import lombok.*;

import java.util.Date;

/**
 * Description：crato业务类型
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/28 5:43 下午
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BusinessType {
    /**
     * id
     */
    private long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 业务类型，固定存储空间or按次付费
     */
    private String product;
    /**
     * 服务方式
     */
    private String serviceType;
    /**
     * 存储空间
     */
    private Double fixedSpace;
    /**
     * 使用的空间，单位：G
     */
    private Double used;
    /**
     * 存储次数
     */
    private Integer opNum;
    /**
     * 创建日期
     */
    private Date dateTime;
    private Date expiredTime;
    private Double unused;

    public BusinessType(String name, String product, String serviceType, Double space, Double used, Integer opNum,Date expiredTime) {
        this.name = name;
        this.product = product;
        this.serviceType = serviceType;
        this.fixedSpace = space;
        this.used = used;
        this.opNum = opNum;
        this.expiredTime=expiredTime;
    }
    public BusinessType(String name, Double space, Double used) {
        this.name = name;
        this.fixedSpace = space;
        this.used = used;
    }
}
