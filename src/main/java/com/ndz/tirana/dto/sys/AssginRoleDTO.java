package com.ndz.tirana.dto.sys;

import lombok.Data;

import java.util.List;

@Data
public class AssginRoleDTO {
    private Long userId;
    private List<Long> roleIdList;


}
