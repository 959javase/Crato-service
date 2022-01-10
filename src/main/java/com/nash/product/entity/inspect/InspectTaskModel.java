package com.nash.product.entity.inspect;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description：巡检任务字段
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021/9/29 2:28 下午
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class InspectTaskModel {
    /**
     * id
     */
    private long id;
    /**
     * 巡检任务名称
     */
    private String name;
    /**
     * 巡检任务owner
     */
    private String owner;
    /**
     * 巡检任务脚本，shell
     */
    private String script;
    /**
     * 异常恢复脚本
     */
    private String processScript;
    /**
     * 产品
     */
    private String product;
    /**
     * 时间
     */
    private Long dateTime;
}
