package com.ndz.wheatmall.common.bean;

import com.ndz.wheatmall.common.enums.BizCodeEnum;
import com.ndz.wheatmall.common.enums.StatusCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Controller统一结果返回
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编码：0表示成功，其他值表示失败")
    private Integer code;
    @ApiModelProperty(value = "消息内容")
    private String msg;
    @ApiModelProperty(value = "响应数据")
    private T data;


    public ApiResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResult(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

}
