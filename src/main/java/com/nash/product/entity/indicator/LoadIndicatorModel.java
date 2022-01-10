package com.nash.product.entity.indicator;

import lombok.*;

/**
 * @Description：
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021-08-07 10:21
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LoadIndicatorModel {
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
     * load1
     */
    private Float load1;
    /**
     * load5
     */
    private Float load5;
    /**
     * load15
     */
    private Float load15;
    /**
     * process总量
     */
    private Float loadProcessTotal;
    /**
     * process运行
     */
    private Float loadProcessRun;
}
