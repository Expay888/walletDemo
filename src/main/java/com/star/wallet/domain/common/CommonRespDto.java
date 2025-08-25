package com.star.wallet.domain.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonRespDto<T> implements Serializable {

    private String msg;
    private String code;
    private T data;
    private String timestamp;

}
