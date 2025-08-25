package com.star.wallet.domain.req;

import com.star.wallet.domain.common.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 提币记录对象
 * 
 * @author alex
 * @date 2025-03-08
 */
@Data
public class ChainWithdrawApplyReq extends BaseDto
{
    /** 链类型 */
    @NotBlank(message = "链类型不能为空")
    private String chainType;

    /** 协议类型 */
    @NotBlank(message = "协议类型不能为空")
    private String protocol;

    /** 币名称 */
    @NotBlank(message = "币名称不能为空")
    private String coinName;

    /** 钱包用户uid */
    @NotBlank(message = "钱包用户uid不能为空")
    private String userUid;

    /** 提币地址 */
    @NotBlank(message = "提币地址不能为空")
    private String toAddress;

    /** 申请总数量 */
    @NotNull(message = "申请总数量不能为空")
    private BigDecimal amount;

    private String orgUserId;

    @NotBlank(message = "商户订单号不能为空")
    private String tradeNo;

}
