package com.nash.product.response.chart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Description：
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021-08-11 15:16
 */
@NoArgsConstructor
@Getter
@Setter
public class ChartLineModel {
    /**
     * 名称
     */
    private String name;
    /**
     * data
     */
    private List<Long> data;
}
