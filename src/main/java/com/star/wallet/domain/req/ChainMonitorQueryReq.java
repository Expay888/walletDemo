package com.star.wallet.domain.req;

import com.star.wallet.domain.common.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 充值申请详情查询
 * 
 * @author alex
 * @date 2025-03-08
 */
@Data
public class ChainMonitorQueryReq extends BaseDto
{
    /** 链类型 */
    @NotBlank(message = "链类型不能为空")
    private String chainType;

    @NotBlank(message = "交易哈希不能为空")
    private String tranHash;
}
