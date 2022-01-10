package com.nash.product.mapper.inspect;

import com.nash.product.entity.inspect.InspectTaskModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description：
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021/9/29 2:26 下午
 */
@Component
@Mapper
public interface InspectMapper {
    /**
     * 获取巡检任务
     * @param model
     * @return
     */
    List<InspectTaskModel> insertTask(InspectTaskModel model);

    /**
     * 删除巡检任务
     * @param ids
     * @return
     */
    int deleteTask(List<Long> ids);

    /**
     * 更新巡检任务
     * @param id
     * @return
     */
    int updateTask(Long id);

    /**
     * 查询巡检任务
     * @param product
     * @return
     */
    List<InspectTaskModel> selectTask(String product);
}
