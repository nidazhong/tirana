package com.ndz.tirana.dao.sys;

import com.ndz.tirana.entity.base.SysErrorLogEntity;
import com.ndz.tirana.dao.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogErrorDao extends BaseDao<SysErrorLogEntity> {
}
