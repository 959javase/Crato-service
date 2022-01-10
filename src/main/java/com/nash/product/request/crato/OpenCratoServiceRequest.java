package com.nash.product.request.crato;

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
public class OpenCratoServiceRequest extends BaseRequest {
    /**
     * 用户名
     */
    private String name;
    /**
     * 产品名称
     */
    private String product;
    /**
     * 服务方式
     */
    private String serviceType;
    /**
     * 总空间
     */
    private Double space = -1D;
    /**
     * 使用空间
     */
    private Double used = 0D;
    /**
     * 操作次数
     */
    private int opNum = 0;
    private int duration;
}
