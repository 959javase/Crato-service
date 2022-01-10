package com.nash.product.mapper.crato;

import com.nash.product.entity.crato.CratoFileRecord;
import com.nash.product.entity.indicator.CratoDashBoardIndicator;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description：上传文件数据映射类
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/29 5:52 下午
 */
@Component
@Mapper
public interface CratoFileMapper {
    /**
     * 添加文件记录
     * @param record
     * @return
     */
    int insertFile(CratoFileRecord record);

    /**
     * 查询文件列表
     * @param name
     * @param fileName
     * @param belong
     * @param expiredTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<CratoFileRecord> selectFilesByCondition(String name, String fileName, String belong, String expiredTime, int pageNum, int pageSize);

    /**
     * 按用户查询文件大小
     * @param startTime
     * @param endTime
     * @return
     */
    List<CratoDashBoardIndicator> selectFilesGroupByName(String startTime, String endTime);

    /**
     * 批量插入指标
     * @param startTime
     * @param endTime
     * @return
     */
    int batchInsertIndicator(String startTime, String endTime);
}
