package com.nash.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nash.product.annotation.PassToken;
import com.nash.product.constants.CommonConstant;
import com.nash.product.constants.ErrorCodeEnum;
import com.nash.product.entity.account.Account;
import com.nash.product.entity.crato.BusinessType;
import com.nash.product.mapper.account.AccountMapper;
import com.nash.product.mapper.crato.CratoMapper;
import com.nash.product.request.account.SendSmsToMobileRequest;
import com.nash.product.request.account.SignInRequest;
import com.nash.product.request.account.SignOutRequest;
import com.nash.product.request.account.SignUpRequest;
import com.nash.product.response.ApiResponse;
import com.nash.product.response.BaseResponse;
import com.nash.product.response.account.SignInResponse;
import com.nash.product.response.account.SignUpResponse;
import com.nash.product.service.AccountService;
import com.nash.product.service.crato.CratoService;
import com.nash.product.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

/**
 * Description：账号服务
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/21 6:56 下午
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    /**
     * 验证码匹配前缀
     */
    private static final String MOBILE_PREFIX = "aliyun_redis_phone_verifier_for_nashcloud";
    /**
     * 账号mapper
     */
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private CratoMapper cratoMapper;
    /**
     * 阿里云服务接口
     */
    @Autowired
    private AliyunUtils aliyunUtils;
    /**
     * redis服务接口
     */
    @Autowired
    private RedisUtils redisUtils;
    /**
     * JWT服务接口
     */
    @Autowired
    private JwtUtils jwtUtils;
    /**
     * 密钥对生成器
     */
    @Autowired
    private RSAUtils rsaUtils;

    /**
     * 校验用户名
     * @param name
     * @param lang
     * @return
     */
    @PassToken
    @Override
    public BaseResponse checkUserName(String name, String lang) {
        BaseResponse response = new BaseResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, lang), lang);
        Account account = accountMapper.selectByName(name);
        if (account != null) {
            response.setCode(ErrorCodeEnum.USER_NAME_REGISTED.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.USER_NAME_REGISTED, lang));
        }
        return response;
    }

    /**
     * 校验手机号
     * @param mobile
     * @param lang
     * @return
     */
    @PassToken
    @Override
    public BaseResponse checkMobile(String mobile, String lang) {
        BaseResponse response = new BaseResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, lang), lang);
        Account account = accountMapper.selectByMobile(mobile);
        if (account != null) {
            response.setCode(ErrorCodeEnum.MOBILE_REGISTED.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.MOBILE_REGISTED, lang));
        }
        return response;
    }

    public boolean checkSms(String code,String uuid,String phone){
        String url = "";
        RestTemplate client = new RestTemplate();
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("code",code);
        map.put("uuid",uuid);
        map.put("phone",phone);
        String result = client.postForObject(url, map, String.class);
        JSONObject object= JSON.parseObject(result);
        if (object!=null&&!"200".equals(object.getString("code"))){//验证码不匹配
           return false;
        }
        return  true;
    }
    /**
     * 注册账号
     * @param request
     * @return
     */
    @PassToken
    @Override
    public ApiResponse<SignUpResponse> signUp(SignUpRequest request) {
        ApiResponse response = new ApiResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, request.getLang()), request.getLang());
        if (!checkSms(request.getPhonecode(),request.getUuid(),request.getMobile())){//验证码不匹配
            response.setCode(ErrorCodeEnum.CAPTCHA_INCONSISTENT.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.CAPTCHA_INCONSISTENT, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getName())) {//用户名为空
            response.setCode(ErrorCodeEnum.USER_NAME_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.USER_NAME_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getPassword())) {//密码为空
            response.setCode(ErrorCodeEnum.PASSWOR_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWOR_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getConfirmPassword())) {
            response.setCode(ErrorCodeEnum.PASSWOR_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWOR_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getMobile())) {//手机号为空
            response.setCode(ErrorCodeEnum.MOBLIE_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.MOBLIE_NULL, request.getLang()));
            return response;
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {//密码不一致
            response.setCode(ErrorCodeEnum.PASSWORD_INCONSISTENT.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWORD_INCONSISTENT, request.getLang()));
            return response;
        }
        try {
            Map<String, String> keyMap = rsaUtils.initKey(request.getName());
            long id = accountMapper.insertAccount(new Account(request.getName(), request.getPassword(), request.getMobile(), CommonConstant.ADMIN, request.getName(), keyMap.get(CommonConstant.ACCESS_KEY), keyMap.get(CommonConstant.SECRET_KEY), 0l));
            SignUpResponse signUpResponse = new SignUpResponse(id);
            response.setData(signUpResponse);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("generate RSAKeys failed: ", e.getMessage());
            response.setCode(ErrorCodeEnum.RSA_FAILED.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.RSA_FAILED, request.getLang()));
            return response;
        }
        return response;
    }

    /**
     * 登陆账号
     * @param request
     * @return
     */
    @PassToken
    @Override
    public ApiResponse<SignInResponse> signIn(SignInRequest request) {
        ApiResponse response = new ApiResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, request.getLang()), request.getLang());
        if (StringUtils.isBlank(request.getName())) {
            response.setCode(ErrorCodeEnum.USER_NAME_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.USER_NAME_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getPassword())) {
            response.setCode(ErrorCodeEnum.PASSWOR_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWOR_NULL, request.getLang()));
            return response;
        }
        Account account = accountMapper.selectByName(request.getName());
        if (account==null){
            response.setCode(ErrorCodeEnum.ACCOUNT_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.ACCOUNT_NULL, request.getLang()));
            return response;
        }
        if(!request.getPassword().equals(account.getPassword())){
            response.setCode(ErrorCodeEnum.PWD_ERROR.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PWD_ERROR, request.getLang()));
            return response;
        }
        String token = jwtUtils.createToken(account.getId(), account.getName());
        SignInResponse signInResponse = new SignInResponse(account.getId(), token);
        response.setData(signInResponse);
        return response;
    }

    /**
     * 登出
     * @param name
     * @param token
     * @param lang
     * @return
     */
    @Override
    public ApiResponse signOut(String name, String token, String lang) {
        ApiResponse response = new ApiResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, lang), lang);
        if (StringUtils.isBlank(name)) {
            response.setCode(ErrorCodeEnum.USER_NAME_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.USER_NAME_NULL, lang));
            return response;
        }
        if (StringUtils.isBlank(token)) {
            response.setCode(ErrorCodeEnum.TOKEN_NOT_EXIST.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.TOKEN_NOT_EXIST, lang));
            return response;
        }
        if (!jwtUtils.getClaimByName(token, CommonConstant.USER_NAME).asString().equals(name)) {
            response.setCode(ErrorCodeEnum.TOKEN_EXCEPTION.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.TOKEN_EXCEPTION, lang));
            return response;
        }
        response.setData(true);
        return response;
    }

    /**
     * 发送短信验证码
     * @param request
     * @return
     */
    @Override
    public String sendSmsToMobile(SendSmsToMobileRequest request) {
        log.info("Send sms to mobile {}", request.getMobile());
        return aliyunUtils.sendVerifySms(request.getMobile());
    }

    /**
     * 验证短信验证码
     * @param request
     * @return
     */
    @Override
    public ApiResponse<SignInResponse> verifyMobileAndCaptcha(SignInRequest request) {
        ApiResponse response = new ApiResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, request.getLang()), request.getLang());
        if (StringUtils.isBlank(request.getPhonecode())) {
            response.setCode(ErrorCodeEnum.CAPTCHA_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.CAPTCHA_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getMobile())) {
            response.setCode(ErrorCodeEnum.MOBLIE_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.MOBLIE_NULL, request.getLang()));
            return response;
        }
        if (!checkSms(request.getPhonecode(),request.getUuid(),request.getMobile())){//验证码不匹配
            response.setCode(ErrorCodeEnum.CAPTCHA_INCONSISTENT.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.CAPTCHA_INCONSISTENT, request.getLang()));
            return response;
        }
        Account account = accountMapper.selectByMobile(request.getMobile());
        String token = jwtUtils.createToken(account.getId(), account.getName());
        SignInResponse signInResponse = new SignInResponse(account.getId(), token);
        response.setData(signInResponse);
        return response;
    }

    /**
     * 更新密码
     * @param request
     * @return
     */
    @Override
    public ApiResponse updatePasswordByMobile(SignUpRequest request) {
        ApiResponse response = new ApiResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, request.getLang()), request.getLang());
        if (StringUtils.isBlank(request.getMobile())) {
            response.setCode(ErrorCodeEnum.MOBLIE_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.MOBLIE_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getPassword())) {
            response.setCode(ErrorCodeEnum.PASSWOR_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWOR_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getConfirmPassword())) {
            response.setCode(ErrorCodeEnum.PASSWOR_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWOR_NULL, request.getLang()));
            return response;
        }
        if (request.getPassword().equals(request.getConfirmPassword())) {
            response.setCode(ErrorCodeEnum.PASSWORD_INCONSISTENT.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWORD_INCONSISTENT, request.getLang()));
            return response;
        }
        long id = accountMapper.updatePassByMobile(request.getPassword(), request.getMobile());
        SignUpResponse signUpResponse = new SignUpResponse(id);
        response.setData(signUpResponse);
        return response;
    }

    /**
     * 更新密码
     * @param request
     * @return
     */
    @Override
    public ApiResponse updatePasswordByName(SignUpRequest request) {
        ApiResponse response = new ApiResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, request.getLang()), request.getLang());
        if (StringUtils.isBlank(request.getName())) {
            response.setCode(ErrorCodeEnum.USER_NAME_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.USER_NAME_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getPassword())) {
            response.setCode(ErrorCodeEnum.PASSWOR_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWOR_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getConfirmPassword())) {
            response.setCode(ErrorCodeEnum.PASSWOR_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWOR_NULL, request.getLang()));
            return response;
        }
        if (request.getPassword().equals(request.getConfirmPassword())) {
            response.setCode(ErrorCodeEnum.PASSWORD_INCONSISTENT.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWORD_INCONSISTENT, request.getLang()));
            return response;
        }
        long id = accountMapper.updatePassByName(request.getPassword(), request.getName());
        SignUpResponse signUpResponse = new SignUpResponse(id);
        response.setData(signUpResponse);
        return response;
    }

    @Override
    public ApiResponse forgetPwd(SignUpRequest request) {
        ApiResponse response = new ApiResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, request.getLang()), request.getLang());
        if (!checkSms(request.getPhonecode(),request.getUuid(),request.getMobile())){//验证码不匹配
            response.setCode(ErrorCodeEnum.CAPTCHA_INCONSISTENT.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.CAPTCHA_INCONSISTENT, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getPassword())) {
            response.setCode(ErrorCodeEnum.PASSWOR_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWOR_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getConfirmPassword())) {
            response.setCode(ErrorCodeEnum.PASSWOR_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWOR_NULL, request.getLang()));
            return response;
        }
        if (request.getPassword().equals(request.getConfirmPassword())) {
            response.setCode(ErrorCodeEnum.PASSWORD_INCONSISTENT.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWORD_INCONSISTENT, request.getLang()));
            return response;
        }
        long id = accountMapper.updatePassByMobile(request.getPassword(), request.getMobile());
        SignUpResponse signUpResponse = new SignUpResponse(id);
        response.setData(signUpResponse);
        return response;
    }

    /**
     * 创建子账号
     * @param request
     * @return
     */
    @Override
    public ApiResponse<SignUpResponse> createSubAccount(SignUpRequest request) {
        ApiResponse response = new ApiResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, request.getLang()), request.getLang());
        if (StringUtils.isBlank(request.getName())) {
            response.setCode(ErrorCodeEnum.USER_NAME_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.USER_NAME_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getPassword())) {
            response.setCode(ErrorCodeEnum.PASSWOR_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWOR_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getConfirmPassword())) {
            response.setCode(ErrorCodeEnum.PASSWOR_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWOR_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getMobile())) {
            response.setCode(ErrorCodeEnum.MOBLIE_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.MOBLIE_NULL, request.getLang()));
            return response;
        }
        if (StringUtils.isBlank(request.getBelong())) {
            response.setCode(ErrorCodeEnum.ADMIN_ACCOUNT_NULL.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.ADMIN_ACCOUNT_NULL, request.getLang()));
            return response;
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            response.setCode(ErrorCodeEnum.PASSWORD_INCONSISTENT.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.PASSWORD_INCONSISTENT, request.getLang()));
            return response;
        }
        try {
            Map<String, String> keyMap = rsaUtils.initKey(request.getName());
            Account sub=accountMapper.selectByName(request.getBelong());
            long id = accountMapper.insertAccount(new Account(request.getName(), request.getPassword(), request.getMobile(), CommonConstant.SUB, request.getBelong(), keyMap.get(CommonConstant.ACCESS_KEY), keyMap.get(CommonConstant.SECRET_KEY), 0l));
            cratoMapper.insertBusiness(new BusinessType(request.getName(), CommonConstant.SUBJECT, sub.getServiceType(), 0D,0D, 0,
                    sub.getExpiredTime()));
            SignUpResponse signUpResponse = new SignUpResponse(id);
            response.setData(signUpResponse);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("generate RSAKeys failed: ", e.getMessage());
            response.setCode(ErrorCodeEnum.RSA_FAILED.getCode());
            response.setDescription(ErrorCodeEnum.getDescription(ErrorCodeEnum.RSA_FAILED, request.getLang()));
            return response;
        }
        return response;
    }

    /**
     * 查询账号列表
     * @param userName
     * @return
     */
    @Override
    public List<Account> getAccountList(String userName, String lang, String belong) {

        Map<String,Object>map =new HashMap<String,Object>();
        map.put("userName",userName);
        map.put("belong",belong);
        return accountMapper.list(map);
    }

    /**
     * 通过ID和name查询账号信息
     * @param id
     * @param name
     * @return
     */
    @Override
    public Account getAccountByIdAndName(Long id, String name) {
        if (id == null || name == null) {
            return null;
        }
        return accountMapper.selectByIdAndName(id, name);
    }

    /**
     * 通过name查询账号信息
     * @param name
     * @return
     */
    @Override
    public Account getAccountByName(String name) {
        if (name == null) {
            return null;
        }
        return accountMapper.selectByName(name);
    }

    @Override
    public Account selectById(long id) {
        return accountMapper.selectById(id);
    }
}
