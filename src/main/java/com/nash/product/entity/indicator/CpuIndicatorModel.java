package com.nash.product.entity.indicator;

import lombok.*;

/**
 * @Description：
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021-08-06 11:28
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CpuIndicatorModel {
    /**
     * id
     */
    private long id;
    /**
     * 账号名称
     */
    private String userName;
    /**
     * 机器名称
     */
    private String hostName;
    /**
     * 机器ip
     */
    private String hostIp;
    /**
     * 用户态cpu时间
     */
    private Float cpuUser;
    /**
     * 系统占用cpu时间
     */
    private Float cpuSys;
    /**
     * cpu空闲时间
     */
    private Float cpuIdle;
    /**
     * cpu ioawait
     */
    private Float cpuIoawait;
    /**
     * 硬件中断次数
     */
    private Float cpuIrq;
    /**
     * 软中断次数
     */
    private Float cpuSofirq;
}
