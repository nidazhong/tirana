package com.ndz.tirana.service.org.impl;

import com.ndz.tirana.entity.org.EmployeeEntity;
import com.ndz.tirana.service.org.EmployeeService;
import com.ndz.tirana.dao.org.EmployeeDao;
import com.ndz.tirana.dto.org.EmployeeDTO;
import com.ndz.tirana.service.base.impl.BaseServiceImpl;
import com.ndz.tirana.service.sys.UpdateAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 员工业务
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<EmployeeDao, EmployeeEntity> implements EmployeeService {

    @Resource
    EmployeeDao employeeDao;
    @Autowired
    UpdateAgent updateAgent;

   @Override
    public void update(EmployeeDTO dto) {
        updateAgent.update(employeeDao, dto, dto.getEmpId());
    }
}
