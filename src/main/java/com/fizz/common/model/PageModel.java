package com.fizz.common.model;

import lombok.Data;

import java.util.List;

/**
 * @author Fizz
 * @since 2019/10/1 9:36
 */
@Data
public class PageModel<T> {

    private List<T> items;

    private long total;

}
