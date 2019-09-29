package com.fizz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author Fizz
 * @since 2019/9/11 10:44
 */
@ServletComponentScan
@SpringBootApplication
@Slf4j
public class CommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
        log.debug("===================debug");
        log.info("=================info");
        log.warn("=================warn");
        log.error("=====================error");
    }

}
