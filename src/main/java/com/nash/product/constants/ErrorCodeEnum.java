package com.nash.product.constants;

/**
 * Description：
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/21 3:33 下午
 */
public enum ErrorCodeEnum {
    /************************ token ***************************/
    TOKEN_INVALID(431, "token无效", "TOKEN_INVALID"),
    TOKEN_EXCEPTION(432, "token异常", "TOKEN_EXCEPTION"),
    TOKEN_NOT_EXIST(433, "token不存在，请重新登陆", "no token, please login"),
    USER_NOT_EXIST(434, "用户不存在", "The user is not exist"),
    USER_NAME_ERROR(435, "请求退出账号的用户名与当前登录账号的用户名不匹配", "The userName is not match account name"),
    /************************ 请求 ***************************/
    USER_NAME_REGISTED(436, "用户名已被注册", "USER_NAME_REGISTED"),
    MOBILE_REGISTED(437, "手机号已被注册", "MOBILE_REGISTED"),
    USER_NAME_NULL(438, "用户名为空", "USER_NAME_NULL"),
    PASSWOR_NULL(439, "密码为空", "PASSWOR_NULL"),
    MOBLIE_NULL(440, "手机号为空", "MOBLIE_NULL"),
    PASSWORD_INCONSISTENT(441, "密码不一致", "PASSWORD_INCONSISTENT"),
    CAPTCHA_NULL(442, "验证码为空", "CAPTCHA_NULL"),
    CAPTCHA_INCONSISTENT(443, "验证码不匹配", "CAPTCHA_INCONSISTENT"),
    CAPTCHA_TIMEOUT(444, "验证码超时", "CAPTCHA_TIMEOUT"),
    ADMIN_ACCOUNT_NULL(445, "主账号为空", "ADMIN_ACCOUNT_NULL"),
    CID_NULL(446, "cid为空", "CID_NULL"),
    FILE_SIZE_EXCEPTION(447, "文件大小异常", "FILE_SIZE_EXCEPTION"),
    HOST_NAME_IS_NULL(448, "机器名为空", "HOST_NAME_IS_NULL"),
    HOST_IP_IS_NULL(449, "机器IP为空", "HOST_IP_IS_NULL"),
    ACCOUNT_NAME_IS_NULL(450, "账号名称为空", "ACCOUNT_NAME_IS_NULL"),
    DEVICE_TYPE_IS_NULL(451, "设备类型为空", "DEVICE_TYPE_IS_NULL"),
    ID_IS_NULL(452, "id为空", "ID_IS_NULL"),
    PERIOD_TOO_LONG(453, "时间范围超过限制", "PERIOD_TOO_LONG"),
    QUERY_EXCEPTION(454, "查询异常", "QUERY_EXCEPTION"),
    MNEMONIC_IS_NULL(455, "CRUST助记词为空", "CRUST_MNEMONIC_SEED_IS_NULL"),
    JSON_IS_NULL(456, "CRUST json为空", "CRUST_JSON_IS_NULL"),
    TYPE_IS_NULL(456, "CRUST类型为空", "CRUST_TYPE_IS_NULL"),
    INSPECT_TASK_NAME_IS_NULL(456, "巡检任务名称为空", "INSPECT_TASK_NAME_IS_NULL"),
    RSA_FAILED(521, "验证码超时", "RSA_FAILED"),
    SMS_SEND_FAILED(522, "发送手机验证码失败", "SMS_SEND_FAILED"),
    ACCOUNT_NULL(523, "账号不存在", "ACCOUNT_NULL"),
    PWD_ERROR(524, "密码错误", "PWD_ERROR"),
    OK(0, "成功", "success");

    /**
     * 错误码
     */
    private int code;
    /**
     * 错误描述(中文)
     */
    private String description_zh;
    /**
     * 错误描述(英文)
     */
    private String description_en;

    /**
     * 构造方法
     */
    ErrorCodeEnum(int code, String description_zh, String description_en) {
        this.code = code;
        this.description_zh = description_zh;
        this.description_en = description_en;
    }

    public int getCode() {
        return code;
    }

    public String getDescription_zh() {
        return description_zh;
    }

    public String getDescription_en() {
        return description_en;
    }

    /**
     * 根据国际化情况返回描述
     * @return
     */
    public static String getDescription(ErrorCodeEnum errorCode, String lang) {
        if (lang == null || lang.equals(CommonConstant.CHINESE)) {
            return errorCode.getDescription_zh();
        }
        return errorCode.getDescription_en();
    }
}
