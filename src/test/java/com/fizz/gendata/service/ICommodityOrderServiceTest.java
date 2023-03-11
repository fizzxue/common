package com.fizz.gendata.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fizz.gendata.entity.CommodityOrder;
import com.fizz.gendata.mapper.CommodityOrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ICommodityOrderServiceTest {

    @Resource
    private CommodityOrderMapper commodityOrderMapper;

    @Test
    public void t1() {
        List<CommodityOrder> select = commodityOrderMapper.select();

//        int count = 1 * 10000;
//        List<Integer> list = new ArrayList<>(count);
//        for (int i = 1; i <= count; i++) {
//            list.add(i);
//        }
        StopWatch sw = new StopWatch();
        sw.start("t1");
        List<CommodityOrder> data = commodityOrderMapper.multiSelect(select);
        sw.stop();
        System.out.println(sw.prettyPrint());
        System.out.println(sw.getTotalTimeSeconds());
        System.out.println("size: " + data.size());
        System.out.println(data.get(data.size() - 1));
    }

    @Test
    public void t2() {
        List<CommodityOrder> select = commodityOrderMapper.select();

//        int count = 1 * 10000;
//        List<Integer> list = new ArrayList<>(count);
//        for (int i = 1; i <= count; i++) {
//            list.add(i);
//        }

        StopWatch sw = new StopWatch();
        List<CommodityOrder> list = new ArrayList<>(10000);
        sw.start("t1");
        for (CommodityOrder commodityOrder : select) {
            QueryWrapper<CommodityOrder> qw = new QueryWrapper<>();
            qw.eq("order_no", commodityOrder.getOrderNo());
            qw.eq("domain", commodityOrder.getDomain());
            List<CommodityOrder> commodityOrders = commodityOrderMapper.selectList(qw);
            list.addAll(commodityOrders);
        }
        sw.stop();
        System.out.println(sw.prettyPrint());
        System.out.println(sw.getTotalTimeSeconds());
//        System.out.println("size: " + data.size());
//        System.out.println(data.get(data.size() - 1));
    }

}