package com.study.lease.common.exception;

import com.study.lease.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @Author Ryan Yan
 * @Since 2024/11/11 16:13
 */
@Data
public class LeaseException extends RuntimeException{

    private Integer code;

    public LeaseException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public LeaseException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

}
