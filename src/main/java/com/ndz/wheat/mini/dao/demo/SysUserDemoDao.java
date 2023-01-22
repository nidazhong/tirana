package com.ndz.wheat.mini.dao.demo;

import com.ndz.wheat.mini.dao.base.BaseDao;
import com.ndz.wheat.mini.entity.demo.SysUserDemoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDemoDao extends BaseDao<SysUserDemoEntity> {
}
