package com.ndz.wheat.mini.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.wheat.mini.dao.base.BaseDao;
import com.ndz.wheat.mini.dto.sys.QuerySysUserDTO;
import com.ndz.wheat.mini.entity.sys.SysUserEntity;
import com.ndz.wheat.mini.vo.sys.SysUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {
    IPage<SysUserVO> page(Page<SysUserVO> page, QuerySysUserDTO query);
}