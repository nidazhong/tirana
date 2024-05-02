package com.ndz.tirana.dto.sys;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import cn.hutool.core.date.DatePattern;
import lombok.Data;

@Data
public class SysOperLogQueryDTO {

    private String operUrl;
    private String title;
    private String operName;

    private String keyword;

    private String username;

    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTimeBegin;
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTimeEnd;
}
