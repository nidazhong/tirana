package com.ndz.tirana.common.page;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页工具类
 */
@Data
//@ApiModel(value = "分页数据")
public class PageData<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //@ApiModelProperty(value = "总记录数")
    private int total;

    //@ApiModelProperty(value = "列表数据")
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