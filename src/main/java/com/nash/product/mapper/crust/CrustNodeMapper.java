package com.nash.product.mapper.crust;

import com.nash.product.entity.crust.CrustNode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description：crust映射器
 * @Author: zhujian from nashcloud
 * @Date: Created in 2021-09-09 15:34
 */
@Component
@Mapper
public interface CrustNodeMapper {
    /**
     * 查询crust节点管理
     *
     * @param id crust节点管理ID
     * @return crust节点管理
     */
    public CrustNode selectCrustNodeById(String id);

    /**
     * 查询crust节点管理列表
     *
     * @param crustNode crust节点管理
     * @return crust节点管理集合
     */
    public List<CrustNode> selectCrustNodeList(CrustNode crustNode);

    /**
     * 新增crust节点管理
     *
     * @param crustNode crust节点管理
     * @return 结果
     */
    public int insertCrustNode(CrustNode crustNode);

    /**
     * 修改crust节点管理
     *
     * @param crustNode crust节点管理
     * @return 结果
     */
    public int updateCrustNode(CrustNode crustNode);

    /**
     * 删除crust节点管理
     *
     * @param id crust节点管理ID
     * @return 结果
     */
    public int deleteCrustNodeById(String id);

    /**
     * 批量删除crust节点管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCrustNodeByIds(String[] ids);
}
