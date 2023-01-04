package com.fizz.gendata;

import com.fizz.common.constant.ArrayConstant;
import com.fizz.gendata.entity.CommodityOrder;
import com.fizz.gendata.mapper.CommodityOrderMapper;
import com.fizz.gendata.service.ICommodityOrderService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
public class GenTableCsvTest {

    @Test
    public void gen () {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Fizz\\Desktop\\order_1000w.csv"))) {
//            int count = 10000 * 1000;
            int count = 10;
            for (int i = 1; i <= count; i++) {
                String orderNo = UUID.randomUUID().toString().replaceAll("-", "");
                String desc = genZw();
                BigDecimal price = new BigDecimal(RandomStringUtils.randomNumeric(1, 8));
                String now = sdf.format(new Date());
                bw.write(i + "," + orderNo + "," + desc + "," + price + "," + now + "," + now);
                bw.newLine();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
