package com.ndz.wheatmall.dao.base;

import com.ndz.wheatmall.entity.base.LogErrorEntity;
import com.ndz.wheatmall.entity.base.UpdateHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogErrorDao extends BaseDao<LogErrorEntity>{
}
