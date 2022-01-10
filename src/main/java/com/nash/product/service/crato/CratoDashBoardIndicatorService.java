package com.nash.product.service.crato;

import com.nash.product.entity.indicator.CratoDashBoardIndicator;

import java.util.List;
import java.util.Map;

public interface CratoDashBoardIndicatorService {
    /**
     * 添加crato指标
     * @param indicators
     * @return
     */
    int insertIndicator(CratoDashBoardIndicator indicators);
    public List<CratoDashBoardIndicator> selectIndicatorByName(String username,String granularity);
}
