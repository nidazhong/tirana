package com.ndz.tirana.dao.demo;

import com.ndz.tirana.dao.base.BaseDao;
import com.ndz.tirana.entity.demo.SysUserDemoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDemoDao extends BaseDao<SysUserDemoEntity> {
}
