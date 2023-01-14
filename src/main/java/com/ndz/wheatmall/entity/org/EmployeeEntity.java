package com.ndz.wheatmall.entity.org;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ndz.wheatmall.annotation.HistoryRecord;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("org_employee")
public class EmployeeEntity {

    @TableId
    private String empId;
    private String name;
    private String job;
    private String department;

    private LocalDateTime entryDateTime;
}
