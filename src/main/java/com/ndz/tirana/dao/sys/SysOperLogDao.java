package com.ndz.tirana.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.tirana.dao.base.BaseDao;
import com.ndz.tirana.entity.sys.SysOperLogEntity;
import com.ndz.tirana.vo.sys.SysOperLogVO;
import com.ndz.tirana.dto.sys.SysOperLogQueryDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysOperLogDao extends BaseDao<SysOperLogEntity> {
    IPage<SysOperLogVO> page(Page<SysOperLogVO> pageParam, SysOperLogQueryDTO query);


}