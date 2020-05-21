package com.infowoo.purchase.mybatis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * http://www.cnblogs.com/waterystone/p/5547254.html
 * mapper里json型字段到类的映射。
 * 用法一:
 * 入库：#{jsonDataField, typeHandler=com.adu.spring_test.mybatis.typehandler.JsonTypeHandler}
 * 出库：
 * <resultMap>
 * <result property="jsonDataField" column="json_data_field" javaType="com.xxx.MyClass" typeHandler="com.adu.spring_test.mybatis.typehandler.JsonTypeHandler"/>
 * </resultMap>
 *
 * 用法二：
 * 1）在mybatis-config.xml中指定handler:
 *      <typeHandlers>
 *              <typeHandler handler="com.adu.spring_test.mybatis.typehandler.JsonTypeHandler" javaType="com.xxx.MyClass"/>
 *      </typeHandlers>
 * 2)在MyClassMapper.xml里直接select/update/insert。
 */
@MappedJdbcTypes({JdbcType.LONGVARCHAR, JdbcType.VARCHAR})
public class ListMybatisHandler<T extends Object> implements TypeHandler<List<T>> {

    private Class<T> clazz;
    public ListMybatisHandler(Class<T> clazz) {
        if (clazz == null){
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.clazz = clazz;
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List<T> list, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSON.toJSONString(list));
    }

    @Override
    public List<T> getResult(ResultSet resultSet, String s) throws SQLException {
        return JSONObject.parseArray(resultSet.getString(s),clazz);
    }

    @Override
    public List<T> getResult(ResultSet resultSet, int i) throws SQLException {
        return JSONObject.parseArray(resultSet.getString(i),clazz);
    }

    @Override
    public List<T> getResult(CallableStatement callableStatement, int i) throws SQLException {
        JSONArray jsonArray =  JSONObject.parseArray(callableStatement.getString(i));
        return jsonArray.stream().map(json->JSONObject.parseObject(String.valueOf(json),clazz)).collect(Collectors.toList());
    }
}
