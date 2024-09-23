package com.fizz.business.acl;

import com.alibaba.druid.pool.DruidDataSource;
import com.fizz.business.acl.entity.User;
import com.fizz.business.acl.mapper.UserMapper;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Fizz
 * @since 2024/9/22 13:31
 */
public class MybatisTest {

    public static void main(String[] args) throws Exception {
/*        // 创建 Druid 数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://192.168.1.111:3306/common?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&useAffectedRows=true&allowMultiQueries=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // MySQL 示例

        // 创建 TransactionFactory
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("development", transactionFactory, dataSource);

        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);*/


        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));

        try (SqlSession session1 = sqlSessionFactory.openSession();SqlSession session2 = sqlSessionFactory.openSession()) {
            UserMapper mapper1 = session1.getMapper(UserMapper.class);
            UserMapper mapper2 = session2.getMapper(UserMapper.class);
            User user = mapper1.queryById("1");
            session1.commit();
            mapper2.queryById("1");
            System.out.println(user);
        }
    }

}
