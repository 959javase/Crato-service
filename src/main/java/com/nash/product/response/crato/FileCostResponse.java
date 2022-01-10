package com.nash.product.response.crato;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Description：
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/29 4:44 下午
 */
@AllArgsConstructor
@Value
public class FileCostResponse {
    /**
     * 文件存储费用
     */
    private Float cost;
    /**
     * 文件大小
     */
    private int size;
}
