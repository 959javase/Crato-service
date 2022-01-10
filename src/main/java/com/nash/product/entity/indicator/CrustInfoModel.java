package com.nash.product.entity.indicator;

import lombok.*;
/**
 * @Description：
 * @Author: meijiajun
 * @Date: Created in 2021-08-11 09:37
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CrustInfoModel {
    private int id;
    private String addr;
    private String smanager;
    private String chain;
    private String name;
    private String ipfs;
    private String hostIp;
    private String hostName;
    private String currStaking;
    private String maxStaking;

}
