package com.nash.product.request.device;

import com.nash.product.request.BaseRequest;
import lombok.*;

/**
 * Description：
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/28 9:34 下午
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString
public class AddDeviceRequest extends BaseRequest {
    /**
     * id
     */
    private long id;
    /**
     * 服务器名称
     */
    private String hostName;
    /**
     * 服务器IP
     */
    private String hostIp;
    /**
     * 机房
     */
    private String roomName;
    /**
     * 机架
     */
    private String rackName;
    /**
     * 机柜
     */
    private String cabinetName;
    /**
     * 槽位
     */
    private String slotName;
    /**
     * 服务器归属账号
     */
    private String accountName;
    /**
     * 设备类型
     */
    private String deviceType;
    /**
     * 序列号
     */
    private String serial;
    /**
     * 设备状态
     */
    private String status;
    /**
     * 交换机
     */
    private String switchServer;
    /**
     * 磁盘数量
     */
    private Integer diskQuantity;
    /**
     * 页码
     */
    private int pageNum;
    /**
     * 页数
     */
    private int pageSize;
}
