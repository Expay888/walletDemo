package com.star.wallet.domain.resp;

import com.star.wallet.domain.common.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 创建或查询地址出参
 */
@Data
public class CreateAddresResp extends BaseDto implements Serializable {




    /** 业务类型:CREATEUSER创建用户，CREATEORDER创建订单 */
    @NotBlank(message = "业务类型不能为空")
    private String buzType;

    /** 当业务类型为CREATEUSER时填用户code,当业务类型为CREATEORDER时传订单号 */
    @NotBlank(message = "业务参数不能为空")
    private String buzValue;



    /** 链类型 */
    @NotBlank(message = "链类型不能为空")
    private String chainType;

    private String address;

    /**
     * 钱包端的用户id
     */
    private String userUid;

}
