package com.nash.product.response;

import com.nash.product.constants.ErrorCodeEnum;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Description：api response
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/21 7:35 下午
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ApiResponse<T> extends BaseResponse implements Serializable {
    /**
     * 返回值
     */
    @NotNull
    private T data;

    public ApiResponse() {}
    public ApiResponse(int code, String message, String lang, T data) {
        super(code, message, lang);
        this.data = data;
    }

    public ApiResponse(int code, String message, String lang) {
        super(code, message, lang);
    }

    public ApiResponse(BaseResponse response, T data) {
        super(response.getCode(), response.getDescription(), response.getLang());
        this.data = data;
    }
    public static ApiResponse success(String lang,Object data){
        return new ApiResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, lang), lang,data);
    }
    public static ApiResponse success(String lang){
        return new ApiResponse(ErrorCodeEnum.OK.getCode(), ErrorCodeEnum.getDescription(ErrorCodeEnum.OK, lang), lang,null);
    }
    public static ApiResponse error(String lang,ErrorCodeEnum errorCodeEnum){
        return new ApiResponse(errorCodeEnum.OK.getCode(), errorCodeEnum.getDescription(ErrorCodeEnum.OK, lang), lang);
    }
}
