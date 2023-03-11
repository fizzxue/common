package com.fizz.gendata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityOrder implements Serializable {

    /**
     * 主键
     */
    @TableId(type = IdType.INPUT)
    private Integer id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 域名
     */
    private String domain;

    /**
     * 订单描述
     */
    private String description;

    /**
     * 订单金额
     */
    private BigDecimal price;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
