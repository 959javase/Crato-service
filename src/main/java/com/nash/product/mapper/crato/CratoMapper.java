package com.nash.product.mapper.crato;

import com.nash.product.entity.crato.BusinessType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Description：
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/28 8:40 下午
 */
@Component
@Mapper
public interface CratoMapper {
    /**
     * 通过名称查询业务状态
     * @param name
     * @return
     */
    BusinessType selectCratoBusinessByName(@Param("name") String name);

    /**
     * 添加业务
     * @param businessType
     * @return
     */
    int insertBusiness(BusinessType businessType);
    int update(BusinessType businessType);

}
