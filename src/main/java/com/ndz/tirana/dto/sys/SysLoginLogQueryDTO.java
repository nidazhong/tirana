package com.ndz.tirana.dto.sys;

import cn.hutool.core.date.DatePattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class SysLoginLogQueryDTO {

    private String keyword;

    private String username;

    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTimeBegin;
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTimeEnd;
}
