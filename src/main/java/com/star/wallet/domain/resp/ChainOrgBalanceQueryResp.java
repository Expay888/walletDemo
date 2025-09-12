package com.star.wallet.domain.resp;

import com.star.wallet.domain.common.BaseDto;
import lombok.Data;

import com.star.wallet.domain.common.ChainOrgBalanceDto;
import java.io.Serializable;
import java.util.List;

//商户钱包余额查询响应
@Data
public class ChainOrgBalanceQueryResp extends BaseDto implements Serializable {

    //商户钱包余额
    private List<ChainOrgBalanceDto> coinBalanceList;

    //状态1正常0禁用
    private Integer status;

}
