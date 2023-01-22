package com.ndz.wheat.mini.vo.index;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class GreetVO {

    /**
     *
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
       @ApiModelProperty("日期")
        private LocalDate date;
        private LocalDateTime time;
        private String msg;
}
