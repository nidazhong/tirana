package com.ndz.wheat.mini.dao.sys;

import com.ndz.wheat.mini.dao.base.BaseDao;
import com.ndz.wheat.mini.entity.sys.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
    List<SysMenuEntity> listByUserId(@Param("userId") Long userId);
}