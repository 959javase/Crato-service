package com.nash.product.request.crato;

import com.nash.product.request.BaseRequest;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description：添加上传成功文件记录
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/29 4:25 下午
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode()
@ToString
public class AddFileRequest extends BaseRequest {
    /**
     * 账号名称
     */
    private String name;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * cid
     */
    private String cid;
    /**
     * 文件大小
     */
    private Double fileSize;
    /**
     * 费用
     */
    private Double cost;
    /**
     * 上传日期
     */
    private Date dataTime;
    private int storageDays;
}
