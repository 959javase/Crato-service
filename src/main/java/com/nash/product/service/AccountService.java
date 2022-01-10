package com.nash.product.service;

import com.nash.product.entity.account.Account;
import com.nash.product.entity.crato.CratoFileRecord;
import com.nash.product.request.account.SendSmsToMobileRequest;
import com.nash.product.request.account.SignInRequest;
import com.nash.product.request.account.SignOutRequest;
import com.nash.product.request.account.SignUpRequest;
import com.nash.product.response.ApiResponse;
import com.nash.product.response.BaseResponse;
import com.nash.product.response.account.SignInResponse;
import com.nash.product.response.account.SignUpResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 账号操作相关接口
 */
public interface AccountService {
    /**
     * 校验用户名
     * @param userName
     * @return
     */
    public BaseResponse checkUserName(String userName, String lang);

    /**
     * 校验手机号
     * @param userName
     * @return
     */
    public BaseResponse checkMobile(String userName, String lang);

    /**
     * 注册账号
     * @param request
     * @return
     */
    public ApiResponse<SignUpResponse> signUp(SignUpRequest request);

    /**
     * 用户登陆
     * @param request
     * @return
     */
    public ApiResponse<SignInResponse> signIn(SignInRequest request);

    /**
     * 登出账号
     * @param name
     * @param token
     */
    public ApiResponse signOut(String name, String token, String lang);

    /**
     * 发送手机验证码
     * @param request
     * @return
     */
    public String sendSmsToMobile(SendSmsToMobileRequest request);

    /**
     * 手机验证码登陆
     * @param request
     * @return
     */
    public ApiResponse<SignInResponse> verifyMobileAndCaptcha(SignInRequest request);

    /**
     * 更新密码
     * @param request
     * @return
     */
    public ApiResponse updatePasswordByMobile(SignUpRequest request);

    /**
     * 更新密码
     * @param request
     * @return
     */
    public ApiResponse updatePasswordByName(SignUpRequest request);
    public ApiResponse forgetPwd(SignUpRequest request);

    /**
     * 创建子账号
     * @param request
     * @return
     */
    public ApiResponse<SignUpResponse> createSubAccount(SignUpRequest request);

    /**
     * 查询账号列表
     * @param userName
     * @param lang
     * @param keyVision
     * @return
     */
    public List<Account> getAccountList(String userName, String lang, String belong);

    /**
     * 通过id和name查询账号
     * @param id
     * @param name
     * @return
     */
    public Account getAccountByIdAndName(Long id, String name);

    /**
     * 通过name查询账号
     * @param name
     * @return
     */
    public Account getAccountByName(String name);
    /**
     * 通过ID查询账号信息
     * @param id
     * @return
     */
    Account selectById(long id);
}
