package com.star.wallet.domain.resp;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ChainMonitorNotifyDto implements Serializable {

    /** 租户code */
    // @Excel(name = "租户code")
    private String tenantCode;

    /** 机构code */
    // @Excel(name = "机构code")
    private String orgCode;

    /** 链类型 */
    // @Excel(name = "链类型")
    private String chainType;

    /** 协议类型 */
    // @Excel(name = "协议类型")
    private String protocol;

    /** 块id */
    // @Excel(name = "块id")
    private Long blockId;

    /** 交易hash */
    // @Excel(name = "交易hash")
    private String tranHash;

    private Long tranTimestamp;

    /** 交易状态 */
    // @Excel(name = "交易状态")
    private String tranStatus;

    /** 币种名称 */
    // @Excel(name = "币种名称")
    private String coinName;

    /** 币种合约 */
    // @Excel(name = "币种合约")
    private String coinAddr;

    /** 转出地址 */
    // @Excel(name = "转出地址")
    private String fromAddr;

    /** 转入地址 */
    // @Excel(name = "转入地址")
    private String toAddr;

    /** 转移金额 */
    // @Excel(name = "转移金额")
    private BigDecimal amount;

    /** 转账当时汇率 */
    // @Excel(name = "转账当时汇率")
    private BigDecimal rate;

    /** 业务类型 */
    // @Excel(name = "业务类型")
    private String buzType;

    /** 业务参数 */
    // @Excel(name = "业务参数")
    private String buzValue;

    /**
     * 当前块Id
     */
    private Long newBlockId;
    /**
     * 块差
     */
    private Integer blockSub;

    /**
     * 安全块数量
     */
    private Integer safeBlockNum;

    /**
     * 审核状态 审核状态-1等待安全块0待审核1审核通过2审核拒绝
     */
    private Integer status;

    private String userUid;
    private String remark;
}
