package com.ndz.wheat.mini.service.sys;

import com.ndz.wheat.mini.dto.sys.LoginDTO;

import java.util.Map;

public interface IndexService {
    Map<String,Object> login(LoginDTO dto);
}
