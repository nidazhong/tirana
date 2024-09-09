package com.ndz.tirana.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.tirana.dao.base.BaseDao;
import com.ndz.tirana.entity.sys.SysLoginLogEntity;
import com.ndz.tirana.vo.sys.SysLoginLogVO;
import com.ndz.tirana.dto.sys.SysLoginLogQueryDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysLoginLogDao extends BaseDao<SysLoginLogEntity> {
    IPage<SysLoginLogVO> page(Page<SysLoginLogVO> pageParam, SysLoginLogQueryDTO query);
}