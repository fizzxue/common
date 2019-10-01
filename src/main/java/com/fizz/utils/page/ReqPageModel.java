package com.fizz.utils.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author fizz
 * @since 2019/7/25 10:52
 * 分页请求对象封装
 */
@Data
@AllArgsConstructor
public final class ReqPageModel {

    /**
     * 当前第几页
     */
    @JsonProperty("curPage")
    private String curPage;

    /**
     * 每页条数
     */
    @JsonProperty("pageSize")
    private String pageSize;

    /**
     * 请求实体参数封装
     */
    @JsonProperty("entity")
    private String entity;
}
