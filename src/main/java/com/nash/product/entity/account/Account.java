package com.nash.product.entity.account;

import com.nash.product.entity.crato.BusinessType;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Description：账号模型
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/27 7:32 下午
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Account implements Serializable {
    /**
     * id
     */
    private long id;
    /**
     * 账号名称
     */
    private String name;
    /**
     * 账号密码
     */
    private String password;
    /**
     * 账号电话
     */
    private String mobile;
    /**
     * 账号类型，主账号、子账号
     */
    private String type;
    /**
     * 账号归属
     */
    private String belong;
    /**
     * 账号ak
     */
    private String accessKey;
    /**
     * 账号sk
     */
    private String secretKey;
    /**
     * 账号余额
     */
    private long balance = 0;
    /**
     * 日期
     */
    private Long dateTime;
    private Double unused;
    private String serviceType;
    private Date expiredTime;

    private BusinessType bizCrato;
    public Account(String name, String password, String mobile, String type, String belong, String accessKey, String secretKey, long balance) {
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.type = type;
        this.belong = belong;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.balance = balance;
        this.dateTime=new Date().getTime();
    }
}
