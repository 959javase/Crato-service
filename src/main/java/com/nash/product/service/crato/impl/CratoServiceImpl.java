package com.nash.product.service.crato.impl;

import com.nash.product.constants.CommonConstant;
import com.nash.product.constants.ErrorCodeEnum;
import com.nash.product.entity.crato.BusinessType;
import com.nash.product.entity.crato.CratoFileRecord;
import com.nash.product.mapper.account.AccountMapper;
import com.nash.product.mapper.crato.CratoFileMapper;
import com.nash.product.mapper.crato.CratoMapper;
import com.nash.product.request.crato.AddFileRequest;
import com.nash.product.request.crato.OpenCratoServiceRequest;
import com.nash.product.response.ApiResponse;
import com.nash.product.service.crato.CratoService;
import com.nash.product.utils.DateUtils;
import com.nash.product.utils.JwtUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Description：
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/28 7:36 下午
 */
@Component
public class CratoServiceImpl implements CratoService {
    /**
     * crato服务
     */
    @Autowired
    private CratoService cratoService;
    @Autowired
    private AccountMapper accountMapper;
    /**
     * crato数据库映射类
     * @param userName
     * @return
     */
    @Autowired
    private CratoMapper cratoMapper;

    /**
     * crato数据库映射类
     * @param userName
     * @return
     */
    @Autowired
    private CratoFileMapper cratoFileMapper;
    /**
     * JWT工具类
     */
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 校验crato是否开通
     * @param userName
     * @return
     */
    @Override
    public ApiResponse checkCratoStatus(String userName, String lang) {
        ApiResponse response = new ApiResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, lang), lang);
        if (userName == null) {
            response.setCode(ErrorCodeEnum.USER_NAME_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.USER_NAME_NULL, lang));
            return response;
        }
        BusinessType businessType = cratoMapper.selectCratoBusinessByName(userName);
        response.setData(businessType != null);
        return response;
    }

    /**
     * 开通crato服务
     * @param request
     * @return
     */
    @Override
    public ApiResponse openCratoService(OpenCratoServiceRequest request) {
        ApiResponse response = new ApiResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, request.getLang()), request.getLang());
        if (request == null) {
            response.setCode(ErrorCodeEnum.USER_NAME_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.USER_NAME_NULL, request.getLang()));
            return response;
        }

        int id = cratoMapper.insertBusiness(new BusinessType(request.getName(), CommonConstant.SUBJECT, request.getServiceType(), request.getSpace(), request.getUsed(), request.getOpNum(),
                DateUtils.localDate2Date(LocalDate.now().plusDays(request.getDuration()))));
        response.setData(id);
        return response;
    }

    /**
     * 文件上传成功添加文件记录
     * @param request
     * @return
     */
    @Transactional
    @Override
    public ApiResponse addFile(AddFileRequest request) {
        ApiResponse response = new ApiResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, request.getLang()), request.getLang());
        if (StringUtils.isBlank(request.getName())) {
            response.setCode(ErrorCodeEnum.USER_NAME_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.USER_NAME_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getCid())) {
            response.setCode(ErrorCodeEnum.CID_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.CID_NULL, request.getLang()));
            return response;
        }
        if (request.getFileSize() <= 0) {
            response.setCode(ErrorCodeEnum.FILE_SIZE_EXCEPTION.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.FILE_SIZE_EXCEPTION, request.getLang()));
            return response;
        }

        BusinessType user=cratoMapper.selectCratoBusinessByName(request.getName());
        if (user.getUnused()<request.getFileSize()){
            response.setCode(ErrorCodeEnum.FILE_SIZE_EXCEPTION.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.FILE_SIZE_EXCEPTION, request.getLang()));
            return response;
        }
        BusinessType b= new BusinessType();
        b.setName(request.getName());
        b.setUsed(request.getFileSize());
        cratoMapper.update(b);
       // accountMapper.updateBalance(request.getName(),request.getCost());
        int id = cratoFileMapper.insertFile(new CratoFileRecord(request.getName(), request.getFileName(), request.getCid(),request.getFileSize(), request.getCost(),DateUtils.localDate2Date(LocalDate.now().plusDays(request.getStorageDays()))));
        response.setData(id);
        return response;
    }

    @Override
    public List<CratoFileRecord> listFilesByCondition(String username,String fileName, String belong, String expiredTime, String lang, int pageNum, int pageSize) {
        if (StringUtils.isBlank(lang)) {
            lang = CommonConstant.ENGLISH;
        }
        pageNum=(pageNum-1)*pageSize;
        List<CratoFileRecord>  files = cratoFileMapper.selectFilesByCondition(username, fileName, belong, expiredTime, pageNum, pageSize);
        return files;
    }

    @Override
    public BusinessType selectCratoBusinessByName(String name) {
        return cratoMapper.selectCratoBusinessByName(name);
    }

    @Override
    public int update(BusinessType businessType) {
        return cratoMapper.update(businessType);
    }
}
