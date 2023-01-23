package com.ndz.wheat.mini.service.sys;

import com.ndz.wheat.mini.dto.sys.LoginDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IndexService {
    Map<String,Object> login(LoginDTO dto);

}
