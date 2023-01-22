package com.ndz.wheat.mini.entity.org;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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