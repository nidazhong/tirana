package com.ndz.wheat.mini.dto.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginDTO {
    @ApiModelProperty(required = true)
    private String username;
    @ApiModelProperty(required = true)
    private String password;
}
