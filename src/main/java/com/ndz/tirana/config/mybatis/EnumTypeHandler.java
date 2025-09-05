//package com.ndz.tirana.mini.config.mybatis;
//
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import com.ndz.tirana.common.enums.demo.CadreRankEnum;
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//import org.apache.ibatis.type.MappedJdbcTypes;
//import org.apache.ibatis.type.MappedTypes;
//
//import com.alibaba.fastjson2.JSON;
//import com.ndz.tirana.common.enums.demo.PositionEnum;
//
//import cn.hutool.core.collection.CollUtil;
//import cn.hutool.json.JSONArray;
//import cn.hutool.json.JSONUtil;
//
//
//@MappedJdbcTypes(JdbcType.VARBINARY)
//@MappedTypes({CadreRankEnum.class})
//public  class EnumTypeHandler extends BaseTypeHandler<CadreRankEnum> {
//
//
//    @Override
//    public void setNonNullParameter(PreparedStatement preparedStatement, int i, CadreRankEnum cadreRankEnum, JdbcType jdbcType) throws SQLException {
//        // 将code存放数据库
//        preparedStatement.setInt(i, cadreRankEnum.getCode());
//    }
//
//    @Override
//    public CadreRankEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
//        // 将枚举返回ResultSet
//        String columnVal = resultSet.getString(s);
//        Integer val = Integer.valueOf(columnVal);
//        CadreRankEnum cadreRankEnum = Arrays.stream(CadreRankEnum.values()).filter(t -> t.getCode().equals(val)).findFirst().orElse(null);
//        return cadreRankEnum;
//    }
//
//    @Override
//    public CadreRankEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
//        return null;
//    }
//
//    @Override
//    public CadreRankEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
//        return null;
//    }
//}
