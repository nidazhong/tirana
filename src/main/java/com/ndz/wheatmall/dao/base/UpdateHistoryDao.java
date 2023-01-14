package com.ndz.wheatmall.dao.base;

import com.ndz.wheatmall.entity.org.EmployeeEntity;
import com.ndz.wheatmall.entity.sys.UpdateHistoryEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UpdateHistoryDao extends BaseDao<UpdateHistoryEntity> {
}
