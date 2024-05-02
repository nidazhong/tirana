package com.ndz.tirana.dao.sys;

import com.ndz.tirana.entity.sys.SysMenuEntity;
import com.ndz.tirana.dao.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
    List<SysMenuEntity> listByUserId(@Param("userId") Long userId);
}