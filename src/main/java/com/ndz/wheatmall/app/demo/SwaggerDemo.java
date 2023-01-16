package com.ndz.wheatmall.app.demo;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Api(tags = "Swagger演示模块")
@ApiSupport(author = "535704264@qq.com",order = 278)
@RequestMapping("/demo/swaggerDemo")
@RestController
public class SwaggerDemo {

    @ApiImplicitParam(name = "name", value = "姓名", required = true)
    @ApiOperation(value = "向客人问好")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam(value = "name") String name) {
        String msg = "Hi: " + name;
        ResponseEntity<String> ok = ResponseEntity.ok(msg);
        return ResponseEntity.ok(msg);
    }

    @ApiOperation(value = "文件上传-带参数Header")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",value = "请求token",required = true,paramType = "header"),
            @ApiImplicitParam(name = "file",value = "文件",required = true,paramType = "form",allowMultiple = true,dataType = "__File"),
            @ApiImplicitParam(name = "name",value = "文件名称",required = true),
    })
    @PostMapping("/uploadParamHeader")
    public ResponseEntity<String> uploadParamHeader(@RequestHeader("token") String token, @RequestParam("file") MultipartFile[] files, @RequestParam("name") String name){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("token:").append(token);
        for (MultipartFile file:files){
            stringBuilder.append("file:"+file.getOriginalFilename());
        }
        stringBuilder.append("name:").append(name);
        return ResponseEntity.ok(stringBuilder.toString());
    }

    @ApiOperation(value = "动态参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "名称",example = "张飞",dataTypeClass = String.class),
            @ApiImplicitParam(name = "money",value = "金钱",example = "123",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "age",value = "年龄",example = "22",dataTypeClass = Integer.class)

    })
    @PostMapping("/params")
    public ResponseEntity<Map<String,String>> params(@ApiIgnore @RequestParam Map<String,String> value){
        return ResponseEntity.ok(value);
    }

    @ApiOperation(value = "allow-示例")
    @PostMapping("/allow")
    public ResponseEntity<SysUserAllow> allowResponseEntity(@RequestBody  SysUserAllow sysUserAllow){
        return ResponseEntity.ok(sysUserAllow);
    }

    @ApiOperation(value = "Put请求")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "email",value = "邮箱地址",required = true),
            @ApiImplicitParam(name = "name",value = "昵称",required = true)
    })
    @PutMapping(value = "/putMethod",produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> putParam(@RequestParam("email") String email,@RequestParam("name") String name){
        return ResponseEntity.ok("email:"+email);
    }


    @ApiOperation(value = "邮箱")
    @ApiImplicitParam(name = "email",value = "邮箱地址",defaultValue = "xiaoymin@foxmail.com",paramType = "path")
    @GetMapping("/test/{email}")
    public ResponseEntity<String> email(@PathVariable("email") String email){
        return ResponseEntity.ok("email:"+email);
    }

    @ApiOperation(value = "测试功能-b", notes = "测试功能B(domain=bbb)")
    @PostMapping(value = "/rest", params = {"domain=bbb"})
    public Object restb_bbb(String username, @RequestParam("pwd") String password) {
        System.err.println("/test/rest_bbb ......" + username + ", " + password);
        return "BBB";
    }

    @PutMapping("projects/{name}")
    @ApiOperation(value = "修改一个项目的名称")
    public ResponseEntity<Map<String,Object>> modifyProject(@PathVariable String name,
                                                            @RequestBody String newName) {
        log.info("name:"+name+",newName:"+newName);
        Map<String,Object> data=new HashMap<>();
        data.put("name",name);
        data.put("newName",newName);
        return ResponseEntity.ok(data);
    }


    @GetMapping("/parents/{parentId:.+}")
    @ApiOperation(value = "url特殊字符")
    public ResponseEntity<String> list(@PathVariable(name = "parentId") String parentId){
        return ResponseEntity.ok("pid:"+parentId);
    }

    @ApiOperation(value = "测试功能-0", notes = "测试功能(domain=other)")
    @PostMapping("/rest")
    public Object rest(HttpServletRequest request, HttpServletResponse response) {
        System.err.println("/test/rest ......");
        return "default method";
    }

    @ApiOperation(value = "测试功能-a", notes = "测试功能A(domain=aaa)")
    @PostMapping(value = "/rest", params = {"domain=aaa"})
    public Object rest_aaa(Integer goodsId, Integer goodsCount) {
        System.err.println("/test/rest_aaa ......" + goodsId + ", " + goodsCount);
        return "AAA";
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response){

        String path="/Users/nidazhong/Desktop/47D0887C-F6ED-4a67-B70E-575050658E9C.png";
        log.info("download-path:{}",path);
        response.setHeader("Access-Control-Expose-Headers","Content-Disposition");
        ServletUtil.write(response,new File(path));
    }

//    @ApiOperation(value = "API_JOB_I18n",notes = "API_JOB_I18n_DESC")
//    @PostMapping("/hidden3")
//    public ResponseEntity<ModelUser> i18n3(@RequestBody ModelUser sysUser){
//        return ResponseEntity.ok(sysUser);
//    }


    @GetMapping("/multi")
    @ApiOperation("批量获取字典数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", allowMultiple = true, name = "codes", value = "字典编码")
    })
    public ResponseEntity<String[]> multiDict(@RequestParam String[] codes) {
        return ResponseEntity.ok(codes);
    }

    @PostMapping("/oauth")
    public ResponseEntity<Map<String,Object>> oauth(){
        Map<String,Object> map=new LinkedHashMap<>();
        map.put("token_type","Benear");
        map.put("access_token", RandomUtil.randomString(64));
        return ResponseEntity.ok(map);
    }



    @ApiOperation(value = "枚举可用值")
    @PostMapping("/mo")
    public ResponseEntity<MapEnumUser> mo(@RequestBody MapEnumUser mapEnumUser){
        ResponseEntity<MapEnumUser> ok = ResponseEntity.ok(mapEnumUser);
        return ok;
    }


}
