package com.ndz.wheatmall.app.demo;

import io.swagger.annotations.ApiModelProperty;

public class SysUserAllow {
    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "状态",allowableValues = "0,1,2")
    private Integer status;

    @ApiModelProperty(value = "部门id")
    private Long depId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }
}
