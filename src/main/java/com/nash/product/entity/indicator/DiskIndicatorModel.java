package com.nash.product.entity.indicator;

import lombok.*;

/**
 * @Description：
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021-08-07 10:01
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DiskIndicatorModel {
    /**
     * id
     */
    private long id;
    /**
     * 磁盘名称
     */
    private String name;
    /**
     * 机器名称
     */
    private String hostName;
    /**
     * 机器ip
     */
    private String hostIp;
    /**
     * 磁盘全称
     */
    private String device;
    /**
     * 挂载点
     */
    private String mount;
    /**
     * 磁盘序列号
     */
    private String serialNum;
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
    private long diskWriteTime;
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
     * wio
     */
    private long weightIo;
    /**
     * 时间
     */
    private long dateTime;
}
