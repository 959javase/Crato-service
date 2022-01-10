package com.nash.product.request.device;

import com.nash.product.request.BaseRequest;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description：批量删除设备请求
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021-08-05 23:25
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DeleteDevicesRequest extends BaseRequest {
    /**
     * 账号名称
     */
    @NotNull
    private String accountName;
    /**
     * 机器名列表
     */
    @NotNull
    private List<String> ids;
}
