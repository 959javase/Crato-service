package com.nash.product.service.crato;

import com.nash.product.entity.crato.BusinessType;
import com.nash.product.entity.crato.CratoFileRecord;
import com.nash.product.request.crato.AddFileRequest;
import com.nash.product.request.crato.OpenCratoServiceRequest;
import com.nash.product.response.ApiResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description：crato服务
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/28 7:33 下午
 */
public interface CratoService {
    /**
     * 检查用户crato服务状态
     * @param userName
     * @param lang
     * @return
     */
    ApiResponse checkCratoStatus(String userName, String lang);

    /**
     * 开通crato服务
     * @param request
     * @return
     */
    ApiResponse openCratoService(OpenCratoServiceRequest request);

    /**
     * 添加上传成功文件记录
     * @param request
     * @return
     */
    ApiResponse addFile(AddFileRequest request);

    /**
     * 查询文件记录
     * @param fileName
     * @param belong
     * @param expiredTime
     * @param lang
     * @return
     */
    public List<CratoFileRecord> listFilesByCondition(String username,String fileName, String belong, String expiredTime, String lang, int pageNum, int pageSize);
    public BusinessType selectCratoBusinessByName(String name);
    public int update(BusinessType businessType);

}
