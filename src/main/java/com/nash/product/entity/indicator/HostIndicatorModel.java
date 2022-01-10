package com.nash.product.entity.indicator;

import lombok.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @Description：
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021-08-07 10:31
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class HostIndicatorModel {
    /**
     * id
     */
    private long id;
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
    private Float cpuIowait;
    /**
     * 硬件中断次数
     */
    private Float cpuIrq;
    /**
     * 软中断次数
     */
    private Float cpuSofirq;
    /**
     * cpu利用率
     */
    private String cpuPercent;
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
    private Float memVused;
    /**
     * 空闲量
     */
    private Float memVfree;
    /**
     * 占比
     */
    private Float memVpercent;
    /**
     * 网络接收流量
     */
    private long netTrafficRev;
    /**
     * 网络发送流量
     */
    private long netTrafficSent;
    /**
     * 网络接收流量
     */
    private long netPackageRev;
    /**
     * 网络发送流量
     */
    private long netPackageSent;
    /**
     * 网络接收丢包
     */
    private long netDropRev;
    /**
     * 网络发送丢包
     */
    private long netDropSent;
    /**
     * 流入丢包率
     */
    private String netDropRevPercent;
    /**
     * 流出丢包率
     */
    private String netDropSentPercent;
    /**
    /**
     * 网络接收错误
     */
    private long netErrorRev;
    /**
     * 网络发送错误
     */
    private long netErrorSent;
    /**
     * 流入错误率
     */
    private String netErrorRevPercent;
    /**
     * 流出错误率
     */
    private String netErrorSentPercent;
    /**
     * 磁盘读次数
     */
    private long diskReadCount;
    /**
     * 磁盘写次数
     */
    private long diskWriteCount;
    /**
     * 磁盘读字节
     */
    private long diskReadBytes;
    /**
     * 磁盘写字节
     */
    private long diskWriteBytes;
    /**
     * 磁盘读时间
     */
    private long diskReadTime;
    /**
     * 磁盘写时间
     */
    private BigInteger diskWriteTime;
    /**
     * io时间
     */
    private long ioTime;
    /**
     * 磁盘总大小
     */
    private long diskTotal;
    /**
     * 磁盘使用大小
     */
    private long diskUsed;
    /**
     * 磁盘空闲大小
     */
    private long diskFree;
    /**
     * 磁盘使用率
     */
    private String diskPercent;
    /**
     * inode总量
     */
    private long inodeTotal;
    /**
     * inode使用量
     */
    private long inodeUsed;
    /**
     * inode空闲量
     */
    private long inodeFree;
    /**
     * 时间
     */
    private long dateTime;
    /**
     * 异常field
     */
    private String fields;
    private Integer isError;
}
