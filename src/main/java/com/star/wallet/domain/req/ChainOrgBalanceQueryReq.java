package com.star.wallet.domain.req;

import com.star.wallet.domain.common.BaseDto;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询商户余额
 */
@Data
public class ChainOrgBalanceQueryReq extends BaseDto implements Serializable {

    /** 链类型 不填查全部*/
    private String chainType;

    /** 协议类型  不填查全部*/
    private String protocol;

    /** 币名称 不填查全部*/
    private String coinName;

}
