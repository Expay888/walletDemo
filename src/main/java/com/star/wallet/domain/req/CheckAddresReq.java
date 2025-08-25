package com.star.wallet.domain.req;

import com.star.wallet.domain.common.BaseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class CheckAddresReq extends BaseDto implements Serializable {

    /** 业务值 */
    @NotBlank(message = "钱包地址不能为空")
    private String address;

    /** 链类型 */
    @NotBlank(message = "链类型不能为空")
    private String chainType;

}
