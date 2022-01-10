package com.nash.product.response.chart;

import com.nash.product.entity.indicator.ChartDataModel;
import lombok.*;

import java.util.List;

/**
 * @Description：
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021-08-07 11:41
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ChartResponse {
    /**
     * 账号名称
     */
    private String accountName;
    /**
     * 机器名称
     */
    private String hostName;
    /**
     * 机器IP
     */
    private String hostIp;
    /**
     * 日期列表
     */
    private List<String> dateTime;
    /**
     * 数据
     */
    private List<ChartDataModel> series;
    public ChartResponse(){}

}
