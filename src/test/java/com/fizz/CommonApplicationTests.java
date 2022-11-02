package com.fizz;

import com.fizz.testtx.ThisTest;
import com.fizz.testtx.UserLogic;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootTest
//@ImportResource(locations = "classpath:spring.xml")
public class CommonApplicationTests {

    @Resource
    private UserLogic userLogic;

    @Resource
    private ThisTest thisTest;

    @Resource
    private DataSource dataSource;

    @Test
    public void contextLoads() throws Throwable {
//        System.out.println("===============" + userLogic);
//        userLogic.updateUser();
//        System.out.println(111);
        thisTest.t4();
//        System.out.println(dataSource);
    }

}
