package com.ndz.wheatmall.service.org.impl;

import com.ndz.wheatmall.dao.org.EmployeeDao;
import com.ndz.wheatmall.dto.org.EmployeeDTO;
import com.ndz.wheatmall.entity.org.EmployeeEntity;
import com.ndz.wheatmall.service.base.impl.BaseServiceImpl;
import com.ndz.wheatmall.service.org.EmployeeService;
import com.ndz.wheatmall.service.base.UpdateAgent;
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
