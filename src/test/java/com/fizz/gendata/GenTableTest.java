package com.fizz.gendata;

import com.fizz.bf.GenData;
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
        int batchSize = 10000;
        int count = batchSize * 500;
        List<CommodityOrder> list = new ArrayList<>(batchSize);
        int saveCount = 0;
        for (int i = 1; i <= count; i++) {
            String orderNo = UUID.randomUUID().toString().replaceAll("-", "");
            String domain = genDomain();
            String desc = genZw();
            BigDecimal price = new BigDecimal(RandomStringUtils.randomNumeric(1, 8));
            Date now = new Date();
            CommodityOrder entity = new CommodityOrder(i, orderNo, domain, desc, price, now, now);
            list.add(entity);
            if (list.size() % batchSize == 0) {
                commodityOrderService.saveBatch(list);
                saveCount += list.size();
                list =  new ArrayList<>(batchSize);
                System.out.println("saveCount: " + saveCount);
            }
        }
        commodityOrderService.saveBatch(list);
        saveCount += list.size();
        System.out.println("saveCount: " + saveCount);
    }

    @Test
    public void gen2 () {
        int batchSize = 10000;
        int count = batchSize * 500;
        List<CommodityOrder> list = new ArrayList<>(batchSize);
        int saveCount = 0;
        for (int i = 10000 * 300; i <= count; i++) {
            String orderNo = UUID.randomUUID().toString().replaceAll("-", "");
            String domain = genDomain();
            String desc = genZw();
            BigDecimal price = new BigDecimal(RandomStringUtils.randomNumeric(1, 8));
            Date now = new Date();
            CommodityOrder entity = new CommodityOrder(i, orderNo, domain, desc, price, now, now);
            list.add(entity);
            if (list.size() % batchSize == 0) {
                commodityOrderService.saveBatch(list);
                saveCount += list.size();
                list =  new ArrayList<>(batchSize);
                System.out.println("saveCount: " + saveCount);
            }
        }
        commodityOrderService.saveBatch(list);
        saveCount += list.size();
        System.out.println("saveCount: " + saveCount);
    }

    @Test
    public void gen3 () {
        int batchSize = 10000;
        int count = batchSize * 500;
        List<CommodityOrder> list = new ArrayList<>(batchSize);
        int saveCount = 0;
        for (int i = 10000 * 400; i <= count; i++) {
            String orderNo = UUID.randomUUID().toString().replaceAll("-", "");
            String domain = genDomain();
            String desc = genZw();
            BigDecimal price = new BigDecimal(RandomStringUtils.randomNumeric(1, 8));
            Date now = new Date();
            CommodityOrder entity = new CommodityOrder(i, orderNo, domain, desc, price, now, now);
            list.add(entity);
            if (list.size() % batchSize == 0) {
                commodityOrderService.saveBatch(list);
                saveCount += list.size();
                list =  new ArrayList<>(batchSize);
                System.out.println("saveCount: " + saveCount);
            }
        }
        commodityOrderService.saveBatch(list);
        saveCount += list.size();
        System.out.println("saveCount: " + saveCount);
    }

    @Test
    public void gen4 () {
        int batchSize = 10000;
        int count = batchSize * 500;
        List<CommodityOrder> list = new ArrayList<>(batchSize);
        int saveCount = 0;
        for (int i = 10000 * 250; i <= count; i++) {
            String orderNo = UUID.randomUUID().toString().replaceAll("-", "");
            String domain = genDomain();
            String desc = genZw();
            BigDecimal price = new BigDecimal(RandomStringUtils.randomNumeric(1, 8));
            Date now = new Date();
            CommodityOrder entity = new CommodityOrder(i, orderNo, domain, desc, price, now, now);
            list.add(entity);
            if (list.size() % batchSize == 0) {
                commodityOrderService.saveBatch(list);
                saveCount += list.size();
                list =  new ArrayList<>(batchSize);
                System.out.println("saveCount: " + saveCount);
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

    public static String genDomain(){
        int domainLength = 5 + new Random().nextInt(15);
        StringBuilder sb = new StringBuilder();
        sb.append(RandomStringUtils.randomAlphanumeric(domainLength));
        sb.append(".");
        sb.append(ArrayConstant.DOMAIN_SUFFIX[new Random().nextInt(ArrayConstant.DOMAIN_SUFFIX_LENGTH)]);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(genDomain());
    }

}
