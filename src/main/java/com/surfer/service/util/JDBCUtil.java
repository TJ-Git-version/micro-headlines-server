package com.surfer.service.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2024/12/17 23:46
 * description JDBCUtil连接池工具类
 */
public class JDBCUtil {

    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private static final DataSource dataSource;

    static {
        Properties properties = new Properties();
        InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("读取配置文件失败");
        }

        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            threadLocal.set(dataSource.getConnection());
        } catch (Exception e) {
            throw new RuntimeException("创建数据库连接池异常");
        }
    }

    // 1.对外暴露连接池方法
    public static DataSource getDataSource() {
        return dataSource;
    }

    // 2.对外暴露获取数据库连接方法
    public static Connection getConnection() {
        Connection connection = threadLocal.get();
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException("创建数据库连接对象异常");
            }
            threadLocal.set(connection);
        }
        return connection;
    }

    // 定义一个归还连接的方法 (解除和ThreadLocal之间的关联关系)
    public static void releaseConnection() {
        Connection connection = threadLocal.get();
        if (connection != null) {
            threadLocal.remove();
            // 归还把连接池设置为自动提交
            try {
                //connection.setAutoCommit(true);
                // 如果事务没有设置成自动提交，则回滚
                if (!connection.getAutoCommit()) {
                    connection.rollback();
                }
                // 自动归还到连接池
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("数据库连接释放异常");
            }
        }
    }

}
