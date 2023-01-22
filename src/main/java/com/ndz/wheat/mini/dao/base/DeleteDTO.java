package com.ndz.wheat.mini.dao.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class DeleteDTO {
    @ApiModelProperty(value = "主键集合", required = true)
    private List<String> ids;
}
