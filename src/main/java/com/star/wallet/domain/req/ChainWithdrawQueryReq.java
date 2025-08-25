package com.star.wallet.domain.req;

import com.star.wallet.domain.common.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 提币申请详情查询
 * 
 * @author alex
 * @date 2025-03-08
 */
@Data
public class ChainWithdrawQueryReq extends BaseDto
{


    /** 钱包用户uid */
    @NotBlank(message = "钱包用户uid不能为空")
    private String userUid;

    /** 钱包提币订单号，用来查询提币状态 */
    @NotBlank(message = "订单号不能为空")
    private String orderNo;
}
