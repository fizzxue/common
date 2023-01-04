package com.fizz.gendata;

import com.fizz.common.constant.ArrayConstant;
import com.fizz.gendata.entity.CommodityOrder;
import com.fizz.gendata.mapper.CommodityOrderMapper;
import com.fizz.gendata.service.ICommodityOrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
public class GenTableTest {

    @Resource
    private ICommodityOrderService commodityOrderService;

    @Test
    public void gen () {
        int count = 10000 * 1000;
        int batchSize = 10000;
        List<CommodityOrder> list = new ArrayList<>(batchSize);
        int saveCount = 0;
        for (int i = 1; i <= count; i++) {
            String orderNo = UUID.randomUUID().toString().replaceAll("-", "");
            String desc = genZw();
            BigDecimal price = new BigDecimal(RandomStringUtils.randomNumeric(1, 8));
            Date now = new Date();
            CommodityOrder entity = new CommodityOrder(i + "", orderNo, desc, price, now, now);
            list.add(entity);
            if (list.size() % batchSize == 0) {
                commodityOrderService.saveBatch(list);
                saveCount += list.size();
                list =  new ArrayList<>(batchSize);
            }
        }
        commodityOrderService.saveBatch(list);
        saveCount += list.size();
        System.out.println("saveCount: " + saveCount);
    }

    private String genZw() {
        StringBuilder sb = new StringBuilder();
        int count = new Random().nextInt(20) + 1;
        for (int i = 0; i < count || sb.toString().length() < 20; i++) {
            int randomIndex = new Random().nextInt(ArrayConstant.KYZ_ARR_LENGTH - 1) + 1;
            sb.append(ArrayConstant.KYZ_ARR[randomIndex]);
        }
        return sb.toString();
    }

}
