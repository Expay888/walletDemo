package com.star.wallet.domain.resp;

import com.star.wallet.domain.common.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 提币记录对象
 * 
 * @author alex
 * @date 2025-03-08
 */
@Data
public class ChainWithdrawApplyResp extends BaseDto
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
    @NotBlank(message = "申请总数量不能为空")
    private BigDecimal amount;

    @NotBlank(message = "商户订单号不能为空")
    private String tradeNo;

    /** 钱包提币订单号，用来查询提币状态 */
    private String orderNo;

    /** 0审核中，1等待放币，2失败，3成功 */
    private Integer status;

    /** 是否是自动提现0否1是 */
    private Integer isAuto;

    /** 交易发起时间 */
    private Date dealTime;//'交易发起时间',

    /** 交易哈希 */
    private String txHash;//'交易哈希'


}
