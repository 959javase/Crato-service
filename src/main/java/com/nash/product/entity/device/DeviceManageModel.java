package com.nash.product.entity.device;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DeviceManageModel {
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
     * 挂载交换机名称
     */
    private String switchServer;
    /**
     * 磁盘数量
     */
    private Integer diskQuantity;
    /**
     * 机器状态
     */
    private String status;


    /**
     *
     * @param hostName
     * @param hostIp
     * @param roomName
     * @param rackName
     * @param cabinetName
     * @param accountName
     * @param deviceType
     */
    public DeviceManageModel(long id,String hostName, String hostIp, String roomName, String rackName, String cabinetName, String slotName, String accountName, String deviceType, String serial, String switchServer, Integer diskQuantity, String status) {
        this.id=id;
        this.hostName = hostName;
        this.hostIp = hostIp;
        this.roomName = roomName;
        this.rackName = rackName;
        this.cabinetName = cabinetName;
        this.accountName = accountName;
        this.deviceType = deviceType;
        this.slotName = slotName;
        this.serial = serial;
        this.switchServer = switchServer;
        this.diskQuantity = diskQuantity;
        this.status = status;
    }
    public DeviceManageModel(String hostName, String hostIp, String roomName, String rackName, String cabinetName, String slotName, String accountName, String deviceType, String serial, String switchServer, Integer diskQuantity, String status) {
        this.hostName = hostName;
        this.hostIp = hostIp;
        this.roomName = roomName;
        this.rackName = rackName;
        this.cabinetName = cabinetName;
        this.accountName = accountName;
        this.deviceType = deviceType;
        this.slotName = slotName;
        this.serial = serial;
        this.switchServer = switchServer;
        this.diskQuantity = diskQuantity;
        this.status = status;
    }
}
