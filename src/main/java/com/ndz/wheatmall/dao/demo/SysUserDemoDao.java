package com.ndz.wheatmall.dao.demo;

import com.ndz.wheatmall.dao.base.BaseDao;
import com.ndz.wheatmall.entity.demo.SysUserDemoEntity;
import com.ndz.wheatmall.entity.org.EmployeeEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDemoDao extends BaseDao<SysUserDemoEntity> {
}
