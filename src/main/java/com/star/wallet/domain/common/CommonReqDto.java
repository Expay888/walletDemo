package com.star.wallet.domain.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class CommonReqDto implements Serializable {

    private String tenantCode;

    /** 机构id */
    @NotBlank(message = "机构code不能为空")
    private String orgCode;
    /**
     * 加密后的入参
     */
    @NotBlank(message = "data不能为空")
    private String data;

}
