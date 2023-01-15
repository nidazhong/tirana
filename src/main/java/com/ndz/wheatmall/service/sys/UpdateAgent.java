package com.ndz.wheatmall.service.sys;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ndz.wheatmall.common.annotation.History;
import com.ndz.wheatmall.common.annotation.HistoryRecord;
import com.ndz.wheatmall.dao.base.BaseDao;
import com.ndz.wheatmall.dao.sys.UpdateHistoryDao;
import com.ndz.wheatmall.entity.base.UpdateHistoryEntity;
import com.ndz.wheatmall.service.base.impl.BaseServiceImpl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 带有历史变更记录的代理类
 */
@Slf4j
@Component
public class UpdateAgent extends BaseServiceImpl<UpdateHistoryDao, UpdateHistoryEntity> {

    @Transactional
    public void update(BaseDao dao, Object newObj, String bizId){
        // 保存历史变更记录
        compareAndRecordHistory(dao, newObj, bizId);
        // 更新业务表
        dao.updateById(newObj);
    }

    private void compareAndRecordHistory(BaseDao dao, Object newObj, String bizId) {
        // 数据库取出旧值
        Object old = dao.selectById(bizId);
        List<UpdateHistoryEntity> updateHistoryEntities = this.reflectChangFields(old, newObj, bizId);
        // 保存变更的字段内容
        updateHistoryEntities.forEach(it-> {
            // TODO 操作人从上下文获取 || 反射获取
            it.setOperatorId("admin");
            it.setOperatorName("admin");
        });
        this.insertBatch(updateHistoryEntities);
    }

    /**
     * 目前支持String类型
     */
    private   List<UpdateHistoryEntity>  reflectChangFields(Object oldObj, Object newObj, String bizId){
        // 记录的变更日志
        List<UpdateHistoryEntity> updateHistoryList = new ArrayList<>();

        try {
            // 得到类对象
            Class<? extends Object> oldClass = oldObj.getClass();
            Class<? extends Object> newClass = newObj.getClass();

            // 有History注解才记录
            History historyAnno = newClass.getAnnotation(History.class);
            if (historyAnno==null) return null;

            // 得到属性集合
            Field[] oldFields = oldClass.getDeclaredFields();
            Field[] newFields = newClass.getDeclaredFields();

            for (Field oldfield : oldFields) {
                oldfield.setAccessible(true);  // 设置属性是可以访问的(私有的也可以)
                for (Field newfield : newFields) {
                    newfield.setAccessible(true);   // 设置属性是可以访问的(私有的也可以)
                    if(oldfield.getName().equals(newfield.getName()) && oldfield.getType().equals(newfield.getType())){    // 比较属性名和类型是否一样
                        if(newfield.get(newObj) == null || StringUtils.isEmpty(newfield.get(newObj) + "")){
                            break;    // 属性值为空一样就退出二级循环
                        }
                        if((oldfield.get(oldObj) == null && newfield.get(newObj)!=null) || // 老对象没值
                                oldfield.get(oldObj) !=null && (!oldfield.get(oldObj).equals(newfield.get(newObj)))){ // 老对象有值，比较属性值不一样
                            // 得到需要记录变更字段的注解
                            HistoryRecord hr = newfield.getAnnotation(HistoryRecord.class);
                            if(hr != null){
                                UpdateHistoryEntity updateHistoryEntity = new UpdateHistoryEntity(historyAnno.table(), bizId);
                                String oldVal = null;
                                String newVal = null;
                                if (oldfield.getType().equals(String.class) || oldfield.getType().equals(Integer.class) ){
                                    if (oldfield.get(oldObj) != null) {
                                        oldVal =  (String) oldfield.get(oldObj);
                                    }
                                    newVal = (String) newfield.get(newObj);
                                } else if (oldfield.getType().equals(LocalDateTime.class)) {
                                    if (oldfield.get(oldObj) != null){
                                        oldVal = LocalDateTimeUtil.format((LocalDateTime) oldfield.get(oldObj),DatePattern.NORM_DATETIME_PATTERN);
                                    }
                                    newVal = LocalDateTimeUtil.format((LocalDateTime) newfield.get(newObj),DatePattern.NORM_DATETIME_PATTERN);
                                }
                                updateHistoryEntity.setUpdateFieldVal(hr.field(), oldVal, newVal);
                                updateHistoryList.add(updateHistoryEntity);
                            }
                        }
                    }
                }
            }
            return updateHistoryList;
        } catch (RuntimeException ex) {
            log.error("对象变更记录出错！", ex);
        } catch (Exception ex) {
            log.error("属性内容更改前后验证错误,日志无法被记录！", ex);
        }
        return updateHistoryList;
    }


}
