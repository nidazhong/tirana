package com.ndz.wheat.mini.service.org.impl;

import com.ndz.wheat.mini.dao.org.EmployeeDao;
import com.ndz.wheat.mini.dto.org.EmployeeDTO;
import com.ndz.wheat.mini.entity.org.EmployeeEntity;
import com.ndz.wheat.mini.service.base.impl.BaseServiceImpl;
import com.ndz.wheat.mini.service.org.EmployeeService;
import com.ndz.wheat.mini.service.sys.UpdateAgent;
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
