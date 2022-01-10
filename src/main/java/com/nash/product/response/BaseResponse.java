package com.nash.product.response;

import com.nash.product.constants.CommonConstant;
import lombok.*;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Description：
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/21 7:13 下午
 */
@NoArgsConstructor(force = true)
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BaseResponse implements Serializable {
    /**
     * 错误码
     */
    @NotNull
    private int code;
    /**
     * 错误描述
     */
    @NotNull
    private String description;
    /**
     * 语言
     */
    private String lang = CommonConstant.CHINESE;

    public BaseResponse(int code, String description, String lang) {
        this.code = code;
        this.description = description;
        if (!StringUtils.isBlank(lang)) {
            this.lang = lang;
        }
    }
}
