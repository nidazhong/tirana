package com.ndz.wheat.mini.entity.base;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("base_update_history")
@NoArgsConstructor
public class UpdateHistoryEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public UpdateHistoryEntity(String tableName, String recordId) {
        this.tableName = tableName;
        this.recordId = recordId;
    }

    /**
     * 标志ID
     */
    private Long id;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 变更的记录ID
     */
    private String recordId;

    /**
     * 变更字段名，即表的字段名
     */
    private String fieldName;

    /**
     * 修改前的字段值
     */
    private String valueBefore;

    /**
     * 修改后的字段值
     */
    private String valueAfter;

    /**
     * 修改人ID
     */
    private String operatorId;

    /**
     * 修改人姓名
     */
    private String operatorName;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    public void setUpdateFieldVal(String fieldName, String valueBefore, String valueAfter) {
        this.fieldName = fieldName;
        this.valueBefore = valueBefore;
        this.valueAfter = valueAfter;
        this.modifyTime = LocalDateTime.now();
    }
}
