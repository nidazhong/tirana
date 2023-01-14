package com.ndz.wheatmall.dto.index;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GreetDTO {
    private LocalDateTime date;
    private LocalDateTime time;
    private String msg;
}
