package com.ndz.tirana.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.tirana.entity.sys.SysUserEntity;
import com.ndz.tirana.vo.sys.SysUserVO;
import com.ndz.tirana.dao.base.BaseDao;
import com.ndz.tirana.dto.sys.QuerySysUserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {
    IPage<SysUserVO> page(Page<SysUserVO> page, QuerySysUserDTO query);
}