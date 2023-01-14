package com.ndz.wheatmall.dto.index;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class GreetDTO {
    private LocalDate date;
    private LocalDateTime time;
    private String msg;
}
