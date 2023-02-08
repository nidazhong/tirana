package com.ndz.wheat.mini.config.mybatis;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.alibaba.fastjson2.JSON;
import com.ndz.wheat.mini.common.enums.demo.PositionEnum;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;


@MappedJdbcTypes(JdbcType.VARBINARY)
@MappedTypes({List.class})
public  class ListEnumTypeHandler extends BaseTypeHandler<List<PositionEnum>> {

    /**
     * 将List枚举转换sql插入的值
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<PositionEnum> positionEnums, JdbcType jdbcType) throws SQLException {
        JSONArray jr = new JSONArray();
        if (CollUtil.isNotEmpty(positionEnums)) {
            for (PositionEnum en: positionEnums) {
                jr.put(en.getCode());
            }
        }
        preparedStatement.setString(i, jr.toString());
    }

    /**
     * 将sql返回的值转换List枚举
     */
    @Override
    public List<PositionEnum> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        List<PositionEnum> enumList = new ArrayList<>();
        String columnStr = resultSet.getString(s);
        if (JSONUtil.isTypeJSONArray(columnStr)) {
            List<Integer> enumValList = JSON.parseArray(columnStr, Integer.class);
            enumValList.forEach(val->{
                PositionEnum byVal = PositionEnum.getByVal(val);
                if (byVal!=null){
                    enumList.add(byVal);
                }
            });
        }
        return enumList.size()==0?null: enumList;
    }

    @Override
    public List<PositionEnum> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public List<PositionEnum> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
