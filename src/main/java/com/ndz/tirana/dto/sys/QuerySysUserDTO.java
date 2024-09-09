package com.ndz.tirana.dto.sys;

import cn.hutool.core.date.DatePattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class QuerySysUserDTO {
    private String keyword;
//    private String createTimeBegin;
//    private String createTimeEnd;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTimeBegin;
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTimeEnd;
}
