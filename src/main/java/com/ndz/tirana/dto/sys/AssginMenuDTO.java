package com.ndz.tirana.dto.sys;

import lombok.Data;

import java.util.List;

@Data
public class AssginMenuDTO {
    public Long roleId;
    private List<Long> menuIdList;
}
