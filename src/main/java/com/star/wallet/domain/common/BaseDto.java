package com.star.wallet.domain.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class BaseDto implements Serializable {

    /** 租户id */
    @NotBlank(message = "租户code不能为空")
    private String tenantCode;

    /** 机构id */
    @NotBlank(message = "机构code不能为空")
    private String orgCode;

    @NotNull(message = "时间戳不能为空")
    private Long timestamp = System.currentTimeMillis();

}
