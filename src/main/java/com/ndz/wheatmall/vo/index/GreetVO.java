package com.ndz.wheatmall.vo.index;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GreetVO {

    /**
     *
     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        private LocalDate date;
        private LocalDateTime time;
        private String msg;
}
