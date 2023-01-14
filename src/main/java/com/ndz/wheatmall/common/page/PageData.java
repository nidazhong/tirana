package com.ndz.wheatmall.common.page;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


import lombok.Data;

/**
 * 分页工具类
 */
@Data
public class PageData<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int total;

    private List<T> list;

    /**
     * 分页
     * @param list   列表数据
     * @param total  总记录数
     */
    public PageData(List<T> list, long total) {
        this.list = list;
        this.total = (int)total;
    }
}