package com.ndz.wheat.mini.dto.sys;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuerySysUserDTO {
    private String keyword;
    private LocalDateTime createTimeBegin;
    private LocalDateTime createTimeEnd;
}
