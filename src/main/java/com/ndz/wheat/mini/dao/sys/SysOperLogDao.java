package com.ndz.wheat.mini.dao.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ndz.wheat.mini.dao.base.BaseDao;
import com.ndz.wheat.mini.dto.sys.SysOperLogQueryDTO;
import com.ndz.wheat.mini.entity.sys.SysOperLogEntity;
import com.ndz.wheat.mini.vo.sys.SysOperLogVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysOperLogDao extends BaseDao<SysOperLogEntity> {
    IPage<SysOperLogVO> page(Page<SysOperLogVO> pageParam, SysOperLogQueryDTO query);


}