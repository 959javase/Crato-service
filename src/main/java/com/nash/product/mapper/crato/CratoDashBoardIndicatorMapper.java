package com.nash.product.mapper.crato;

import com.nash.product.entity.indicator.CratoDashBoardIndicator;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Description：crato大盘指标映射器
 * Author: zhujian from nashcloud
 * Date: Created in 2021/8/1 9:44 下午
 */
@Component
@Mapper
public interface CratoDashBoardIndicatorMapper {
    /**
     * 添加crato指标
     * @param indicators
     * @return
     */
    int insertIndicator(CratoDashBoardIndicator indicators);

    /**
     * 查询指标
     * @param map
     * @return
     */
    public List<CratoDashBoardIndicator> selectIndicatorByName(Map<String,Object> map);
}
