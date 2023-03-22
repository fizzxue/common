package com.fizz.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        System.out.println("==========================");
        from("quartz://report?cron=1 * * * * ?&stateful=true")
                .to("https://httpbin.org/image/png")
                .log("1111111111111111111111111111111")
                .to("file:d:/temp/outbox?fileName=demo.png")
                ;
    }
}
