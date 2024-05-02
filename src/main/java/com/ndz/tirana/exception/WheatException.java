package com.ndz.tirana.exception;

import com.ndz.tirana.common.enums.BizCodeEnum;

public class WheatException extends RuntimeException{
    public WheatException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum.getMsg());
    }

    public WheatException(String msg) {
        super(msg);
    }
}
