package com.surfer.service.service;

import com.surfer.service.util.JDBCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/20 23:55
 * description 封装公共的查询方法和公共的增删改方法
 */
public class BaseDao {

    private static final Logger log = LoggerFactory.getLogger(BaseDao.class);

    /**
     * 公共的查询方法: 查询单个对象
     *
     * @param clazz: 查询后需要转换的对象
     * @param sql:   查询sql
     * @param args:  查询条件
     * @param <T>    对象类型
     * @return 查询对象
     */
    public <T> T baseQueryObject(Class<T> clazz, String sql, Object... args) {
        log.info("query start, sql: {}; args: {}", sql, args);
        T obj = null;
        ResultSet resultSet = null;
        PreparedStatement prepareStatement = null;
        try {
            // 获取连接池对象
            Connection connection = JDBCUtil.getConnection();
            // 获取预加载器
            prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                prepareStatement.setObject(i + 1, args[i]);
            }
            log.info("----query object sql: {}", sql);
            log.info("----param: {}", Arrays.toString(args));
            resultSet = prepareStatement.executeQuery();
            obj = resultSet.getObject(1, clazz);
            log.info("result object: {}", t);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询单个对象异常");
        } finally {
            closeResources(resultSet, prepareStatement);
        }
        return obj;
    }

    /**
     * 公共的查询方法: 查询集合对象
     *
     * @param clazz: 查询后需要转换的对象
     * @param sql:   查询sql
     * @param args:  查询条件
     * @param <T>    对象类型
     * @return 查询对象
     */
    public <T> List<T> baseQueryList(Class<T> clazz, String sql, Object... args) {
        log.info("query start, sql: {}; args: {}", sql, args);
        List<T> list = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement prepareStatement = null;
        try {
            Connection connection = JDBCUtil.getConnection();
            prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                prepareStatement.setObject(i + 1, args[i]);
            }
            log.info("----query object sql: {}", sql);
            log.info("----param: {}", Arrays.toString(args));
            resultSet = prepareStatement.executeQuery();
            // 获取元数据集合
            ResultSetMetaData metaData = resultSet.getMetaData();
            log.info("metaData: {}", metaData);
            int columnCount = metaData.getColumnCount();
            // 获取列数
            log.info("meteData.columnCount: {}", columnCount);
            while (resultSet.next()) {
                // 创建当前表对象
                T obj = clazz.getDeclaredConstructor().newInstance();
                // 属性赋值 列字段 -> 属性
                for (int i = 0; i < columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String columnName1 = metaData.getColumnName(i);
                    log.info("columnName1 -> {}", columnName1);
                    log.info("columnCount --> columnName: {} -> {}", i, columnName);
                    T columnValue = resultSet.getObject(columnName, clazz);
                    log.info("column value -> {}", columnValue);
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(obj, columnValue);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询集合对象异常");
        } finally {
            closeResources(resultSet, prepareStatement);
        }
        return list;
    }

    /**
     * 公共的增删改方法
     *
     * @param sql 增删改sql
     * @param args 参数
     * @return 影响行数
     */
    public int baseUpdate(String sql, Object... args) {
        log.info("update object, sql: {}; args: {}", sql, args);
        int rows = 0;
        PreparedStatement prepareStatement = null;
        try {
            Connection connection = JDBCUtil.getConnection();
            prepareStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                prepareStatement.setObject(i + 1, args[i]);
            }
            log.info("----update object sql: {}", sql);
            log.info("----param: {}", Arrays.toString(args));
            rows = prepareStatement.executeUpdate(sql);
            log.info("influence rows：{}", rows);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeResources(null, prepareStatement);
        }
        return rows;
    }

    private static void closeResources(ResultSet resultSet, PreparedStatement prepareStatement) {
        // 释放资源
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (prepareStatement != null) {
            try {
                prepareStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        JDBCUtil.releaseConnection();
    }


}
