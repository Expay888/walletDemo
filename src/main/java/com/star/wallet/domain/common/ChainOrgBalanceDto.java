package com.star.wallet.domain.common;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商户余额
 */
@Data
public class ChainOrgBalanceDto {

    /** 链类型 */
    private String chainType;

    //协议类型不能为空
    private String protocol;

    //币名称不能为空
    private String coinName;

    //可用余额
    private BigDecimal balance;

}
