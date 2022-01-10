package com.nash.product.entity.indicator;

import lombok.*;

/**
 * @Description：
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021-08-07 10:08
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NetIndicatorModel {
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
     * 网络接收流量
     */
    private int netTrafficRev;
    /**
     * 网络发送流量
     */
    private int netTrafficSent;
    /**
     * 网络接收丢包
     */
    private int netDropRev;
    /**
     * 网络发送丢包
     */
    private int netDropSent;
    /**
     * 网络接收错误
     */
    private int netErrorRev;
    /**
     * 网络发送错误
     */
    private int netErrorSent;
}
