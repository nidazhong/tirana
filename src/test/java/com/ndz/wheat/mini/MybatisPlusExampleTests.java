package com.ndz.wheat.mini;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.wheat.mini.common.enums.demo.CadreRankEnum;
import com.ndz.wheat.mini.dao.org.EmployeeDao;
import com.ndz.wheat.mini.entity.org.EmployeeEntity;

import cn.hutool.json.JSONUtil;

@SpringBootTest
@ActiveProfiles("test")
public class MybatisPlusExampleTests {

    // INSERT INTO `wheat_mini`.`org_employee` (`emp_id`, `name`, `job`, `department`, `entry_date_time`, `cadre_rank`, `position`, `emp_tag`, `role_extra`) VALUES (1, '基努里维斯', '演员', 'devDept', '2023-01-16 21:58:10', 3, '[1,2]', '[\"帅哥\",\"硬汉\"]', '[{\"id\":\"1\",\"roleName\":\"角色1\"}]');

    @Resource
    EmployeeDao employeeDao;

    @Test
    public void test0() {
        // 新增职员
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("Jack Lee");
        employeeEntity.setDepartment("开发部");
        employeeEntity.setCadreRank(CadreRankEnum.SELECT);
        employeeEntity.setJob("Java Coder");
//        employeeEntity.setPosition(Arrays.asList(PositionEnum.COO, PositionEnum.STAFF));
        employeeEntity.setEntryDateTime(LocalDateTime.now());
        employeeDao.insert(employeeEntity);
    }

    @Test
    public void test01() {
        // 查询部分字段
        LambdaQueryWrapper<EmployeeEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(EmployeeEntity::getEmpId, EmployeeEntity::getName, EmployeeEntity::getCadreRank).eq(EmployeeEntity::getEmpId, "1");
        EmployeeEntity employeeEntity = employeeDao.selectOne(queryWrapper);
        System.out.println(JSONUtil.toJsonStr(employeeEntity));
    }

    @Test
    public void test02() {
        // 默认查询所有字段
        EmployeeEntity employeeEntity = employeeDao.selectById("1");
        System.out.println(JSONUtil.toJsonStr(employeeEntity));
        System.out.println(employeeEntity.getRoleExtra().get(0));
        System.out.println(employeeEntity.getLivePlace().getCity());
    }

    @Test
    public void test03() {
        // 分页（单表）
        Page<EmployeeEntity> page = employeeDao.selectPage(new Page<>(1, 10), new LambdaQueryWrapper<>());
        System.out.println(JSONUtil.toJsonStr(page));
    }
}
