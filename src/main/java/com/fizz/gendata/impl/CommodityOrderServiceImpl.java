package com.fizz.gendata.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fizz.business.acl.entity.Permission;
import com.fizz.business.acl.mapper.PermissionMapper;
import com.fizz.business.acl.service.IPermissionService;
import com.fizz.gendata.entity.CommodityOrder;
import com.fizz.gendata.mapper.CommodityOrderMapper;
import com.fizz.gendata.service.ICommodityOrderService;
import org.springframework.stereotype.Service;

@Service
public class CommodityOrderServiceImpl extends ServiceImpl<CommodityOrderMapper, CommodityOrder> implements ICommodityOrderService {

}
