package com.nash.product.response.chart;

import com.nash.product.entity.indicator.HostIndicatorModel;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021-08-08 18:31
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CellResponse {
    /**
     * data
     */
    private Map<String, List<HostIndicatorModel>> data;
    /**
     * 总数
     */
    private int total;
    /**
     * 页码
     */
    private int pageNum;
    /**
     * 页数
     */
    private int pageSize;
}
