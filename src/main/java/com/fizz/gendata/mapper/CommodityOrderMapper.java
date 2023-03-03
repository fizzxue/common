package com.fizz.gendata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fizz.gendata.entity.CommodityOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommodityOrderMapper extends BaseMapper<CommodityOrder> {

    public List<String> multiSelect(List<String> ids);
}
