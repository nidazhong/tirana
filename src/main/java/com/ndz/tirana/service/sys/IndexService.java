package com.ndz.tirana.service.sys;

import com.ndz.tirana.dto.sys.LoginDTO;

import java.util.Map;

public interface IndexService {
    Map<String,Object> login(LoginDTO dto);

}
