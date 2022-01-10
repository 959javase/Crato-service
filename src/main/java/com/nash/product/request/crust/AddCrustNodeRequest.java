package com.nash.product.request.crust;

import lombok.*;

/**
 * @Description：添加crust节点请求参数
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021-09-09 14:18
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString
public class AddCrustNodeRequest {
    /**
     * id
     */
    private long id;
    /**
     * 账号名称
     */
    private String accountName;
    /**
     * 密码
     */
    private String accountPassword;
    /**
     * ip
     */
    private String hostIp;
    /**
     * 设备序列码
     */
    private String serialId;
    /**
     * 助记词
     */
    private String mnemonic;
    /**
     * json
     */
    private String json;
    /**
     * 归属
     */
    private String ownerNode;
    /**
     * 账号类型, C账号或S账号
     */
    private String accountType;
    /**
     * 节点类型, owner or member
     */
    private String nodeType;
    /**
     * 服务类型, sealing,ungroup,grouped
     */
    private String serviceType;
}
