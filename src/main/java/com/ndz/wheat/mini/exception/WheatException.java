package com.ndz.wheat.mini.exception;

import com.ndz.wheat.mini.common.enums.BizCodeEnum;

public class WheatException extends RuntimeException{
    public WheatException(BizCodeEnum bizCodeEnum) {
        super(bizCodeEnum.getMsg());
    }

    public WheatException(String msg) {
        super(msg);
    }
}
