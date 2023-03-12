package com.fizz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Fizz
 * @since 2019/9/11 10:44
 */
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@EnableTransactionManagement
//@ImportResource(locations = "classpath:spring.xml")
public class CommonApplication {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SpringApplication.run(CommonApplication.class, args);
    }

}
