package com.nash.product.entity.indicator;

import lombok.*;

/**
 * @Description：
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021-08-07 10:14
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MemIndicatorModel {
    /**
     * id
     */
    private long id;
    /**
     * 账号名称
     */
    private String accountName;
    /**
     * 机器名称
     */
    private String hostName;
    /**
     * 机器ip
     */
    private String hostIp;
    /**
     * 内存swap总量
     */
    private Float memSwapTotal;
    /**
     * 使用量
     */
    private Float memSwapUsed;
    /**
     * 空闲量
     */
    private Float memSwapFree;
    /**
     * 占比
     */
    private Float memSwapPercent;
    /**
     * 总量
     */
    private Float memVtotal;
    /**
     * 使用量
     */
    private Float memvUsed;
    /**
     * 空闲量
     */
    private Float memVfree;
    /**
     * 占比
     */
    private Float memVpercent;
}
