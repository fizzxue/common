package com.fizz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Fizz
 * @since 2019/9/11 10:44
 */
@SpringBootApplication
@EnableTransactionManagement
//@ImportResource(locations = "classpath:spring.xml")
public class CommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }

}
