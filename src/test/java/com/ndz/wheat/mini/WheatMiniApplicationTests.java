package com.ndz.wheat.mini;

import com.ndz.wheat.mini.common.helper.JwtHelper;
import com.ndz.wheat.mini.dto.org.EmployeeDTO;
import com.ndz.wheat.mini.service.org.EmployeeService;
import com.ndz.wheat.mini.service.sys.UpdateAgent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * springboot 2.7 使用了junit5
 */
@SpringBootTest
public class WheatMiniApplicationTests {

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

    @Test
    public void testJWT() {
        String token = JwtHelper.createToken(1L, "admin");//"eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSCjAK0A0Ndg1S0lFKrShQsjI0MzY2sDQ3MTbQUSotTi3yTFGyMjKEsP0Sc1OBWp6unfB0f7NSLQDxzD8_QwAAAA.2eCJdsJXOYaWFmPTJc8gl1YHTRl9DAeEJprKZn4IgJP9Fzo5fLddOQn1Iv2C25qMpwHQkPIGukTQtskWsNrnhQ";//JwtHelper.createToken(7L, "admin");
        System.out.println(token);
        System.out.println(JwtHelper.getUserId(token));
        System.out.println(JwtHelper.getUsername(token));
    }


}
