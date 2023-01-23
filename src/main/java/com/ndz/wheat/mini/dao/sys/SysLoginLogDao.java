package com.ndz.wheat.mini.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.wheat.mini.dao.base.BaseDao;
import com.ndz.wheat.mini.dto.sys.SysLoginLogQueryDTO;
import com.ndz.wheat.mini.entity.sys.SysLoginLogEntity;
import com.ndz.wheat.mini.vo.sys.SysLoginLogVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysLoginLogDao extends BaseDao<SysLoginLogEntity> {
    IPage<SysLoginLogVO> page(Page<SysLoginLogVO> pageParam, SysLoginLogQueryDTO query);
}