package com.ndz.wheatmall.dao.sys;

import com.ndz.wheatmall.dao.base.BaseDao;
import com.ndz.wheatmall.entity.base.LogErrorEntity;
import com.ndz.wheatmall.entity.base.UpdateHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogErrorDao extends BaseDao<LogErrorEntity> {
}
