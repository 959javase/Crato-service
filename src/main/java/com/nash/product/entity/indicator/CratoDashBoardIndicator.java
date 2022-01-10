package com.nash.product.entity.indicator;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * Description：crato大盘指标
 * Author: zhujian from nashcloud
 * Date: Created in 2021/8/1 8:32 下午
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CratoDashBoardIndicator {
    /**
     * id
     */
    private long id;
    /**
     * 账号名称
     */
    private String name;
    /**
     * 上传大小
     */
    private int fileSize;
    /**
     * 下载大小
     */
    private int downSize;
    /**
     * qps
     */
    private int qps;
    /**
     * 开销
     */
    private int cost;
    /**
     * 存储空间
     */
    private int pinSize;
    /**
     * 使用空间
     */
    private int usedSize;
    /**
     * 时间粒度
     */
    private String granularity;
    /**
     * 时间点，取结束时间
     */
    private long dateTime;
    private String period;

    public CratoDashBoardIndicator(String name, int fileSize, int downSize, int qps, int cost, int pinSize, int usedSize, String granularity, long dateTime) {
        this.name = name;
        this.fileSize = fileSize;
        this.downSize = downSize;
        this.qps = qps;
        this.cost = cost;
        this.pinSize = pinSize;
        this.usedSize = usedSize;
        this.granularity = granularity;
        this.dateTime = dateTime;
    }
}
