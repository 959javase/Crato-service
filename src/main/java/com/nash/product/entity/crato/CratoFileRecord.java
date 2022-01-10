package com.nash.product.entity.crato;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description：
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/29 5:48 下午
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CratoFileRecord {
    /**
     * id
     */
    private long id;
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
     * 文件费用, 固定存储空间方式时为空
     */
    private Double cost;
    /**
     * 上传日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date dateTime;
    /**
     * 到期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date expiredTime;

    public CratoFileRecord(String name, String fileName, String cid, Double fileSize, Double cost, Date expiredTime) {
        this.name = name;
        this.fileName = fileName;
        this.cid = cid;
        this.fileSize = fileSize;
        this.cost = cost;
        this.expiredTime=expiredTime;
    }
}
