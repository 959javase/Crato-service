package com.nash.product.service.crato.impl;

import com.nash.product.entity.indicator.CratoDashBoardIndicator;
import com.nash.product.mapper.crato.CratoDashBoardIndicatorMapper;
import com.nash.product.service.crato.CratoDashBoardIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CratoDashBoardIndicatorServiceIMpl implements CratoDashBoardIndicatorService {
    @Autowired
    private CratoDashBoardIndicatorMapper cratoDashBoardIndicatorMapper;
    @Override
    public int insertIndicator(CratoDashBoardIndicator indicators) {
        return 0;
    }

    @Override
    public List<CratoDashBoardIndicator> selectIndicatorByName(String username,String granularity) {
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("name",username);
        if ("min".equals(granularity)){
            map.put("dateFormat","%Y-%m-%d %H:%i:%s");
        }else if("hour".equals(granularity)){
            map.put("dateFormat","%Y-%m-%d %H");
        }else if("day".equals(granularity)){
            map.put("dateFormat","%Y-%m-%d");
        }
        return cratoDashBoardIndicatorMapper.selectIndicatorByName(map);
    }
}
