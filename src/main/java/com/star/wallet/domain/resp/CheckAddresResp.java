package com.star.wallet.domain.resp;

import com.star.wallet.domain.common.BaseDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class CheckAddresResp extends BaseDto implements Serializable {

    /** 业务值 */
    private Boolean check = false;

}
