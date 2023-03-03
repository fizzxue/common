package com.fizz.gendata.service;

import com.fizz.gendata.mapper.CommodityOrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ICommodityOrderServiceTest {

    @Resource
    private CommodityOrderMapper commodityOrderMapper;

    @Test
    public void t1() {
        List<String> strings = commodityOrderMapper.multiSelect(Arrays.asList("1", "2"));
        System.out.println(strings);
    }

}