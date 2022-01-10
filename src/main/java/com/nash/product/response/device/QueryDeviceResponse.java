package com.nash.product.response.device;

import com.nash.product.entity.device.DeviceManageModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @Description：
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021-08-09 15:15
 */
@NoArgsConstructor
@Getter
@Setter
public class QueryDeviceResponse {
    /**
     * 设备列表
     */
    private List<DeviceManageModel> list;
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
