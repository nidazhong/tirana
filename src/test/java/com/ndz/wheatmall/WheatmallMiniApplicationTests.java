package com.ndz.wheatmall;

import com.ndz.wheatmall.dto.org.EmployeeDTO;
import com.ndz.wheatmall.service.org.EmployeeService;
import com.ndz.wheatmall.service.base.UpdateAgent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * springboot 2.7 使用了junit5
 */
@SpringBootTest
public class WheatmallMiniApplicationTests {

    @Autowired
    UpdateAgent updateAgent;

    @Autowired
    EmployeeService employeeService;

    @Test
    public void test01 () {
        System.out.println("hello world");
    }

    @Test
    public void test02 () {
        System.out.println("2023-01-12".matches("^\\d{4}-\\d{2}-\\d{2}"));
    }
    @Test
    public void test03(){
        EmployeeDTO newDTO = new EmployeeDTO();
        newDTO.setName("基努里维斯");
        newDTO.setJob("演员");
        newDTO.setEntryDateTime(LocalDateTime.now());
        updateAgent.update(null, newDTO, "1");
    }

    @Test
    public void test04(){
        EmployeeDTO newDTO = new EmployeeDTO();
        newDTO.setEmpId("1");
        newDTO.setName("基努里维斯");
        newDTO.setJob("演员");
        newDTO.setEntryDateTime(LocalDateTime.now());

        employeeService.update(newDTO);

    }


}
