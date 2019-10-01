package com.fizz.utils.page;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fizz.common.model.RespModel;
import com.fizz.utils.date.RespUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Fizz
 * @since 2019/10/1 11:10
 */
@Slf4j
public final class ServiceUtils {

    private ServiceUtils() {
    }

    public static <T> RespModel page(IService<T> iService, ReqPageModel reqPageModel) {
        try {
            String entity = reqPageModel.getEntity();
            T t = null;
            if (StringUtils.isNotEmpty(entity)) {
                t = JSON.parseObject(entity, (Type) Class.forName(((ParameterizedType) iService.getClass().getGenericSuperclass()).getActualTypeArguments()[1].getTypeName()));
            }
            Page<T> page = null;
            if (StringUtils.isNotEmpty(reqPageModel.getCurPage()) && StringUtils.isNotEmpty(reqPageModel.getPageSize())) {
                page = new Page<>(Long.parseLong(reqPageModel.getCurPage()), Long.parseLong(reqPageModel.getPageSize()));
            } else {
                //设置默认分页参数
                page = new Page<>(1, 10);
                reqPageModel.setCurPage("1");
                reqPageModel.setPageSize("10");
            }
            QueryWrapper<T> qw = new QueryWrapper<>(t);
            IPage<T> iPage = iService.page(page, qw);
            return RespUtils.success(new RespPageModel<>(reqPageModel, iPage));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return RespUtils.failure(e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println(1);
    }

}
