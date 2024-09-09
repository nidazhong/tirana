package com.ndz.tirana.app.demo;


import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.ndz.tirana.vo.demo.SysUserDemoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.ndz.tirana.common.bean.ApiResult;
import com.ndz.tirana.dto.demo.SysUserDemoDTO;
import com.ndz.tirana.service.demo.SysUserDemoService;
import com.ndz.tirana.utils.ApiResultUtils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.extra.servlet.ServletUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@Api(tags = "Swagger示例")
@ApiSupport(author = "Jack Lee",order = 1)
@RequestMapping("/demo/swaggerDemo")
@RestController
public class SwaggerDemoController {

    @Autowired
    SysUserDemoService sysUserDemoService;

    @ApiOperation(value = "新增用户",notes = "接口描述信息")
    @ApiOperationSupport(author = "作者xxx", order = 1)
    @PostMapping("/sysUser/save")
    public ApiResult save(@RequestBody SysUserDemoDTO dto) {
        sysUserDemoService.save(dto);
        return ApiResultUtils.ok(dto);
    }

    @ApiOperation(value = "用户详情")
    @ApiOperationSupport(order = 2)
    @ApiImplicitParam(name = "id",value = "用户Id",defaultValue = "1", required = true,
                      paramType = "path",dataType = "Long")
    @GetMapping("/sysUser/info/{id}")
    public ApiResult<SysUserDemoVO> info(@PathVariable Long id) {
        return ApiResultUtils.ok(sysUserDemoService.info(id));
    }

    @ApiOperation(value = "删除用户")
    @ApiOperationSupport(order = 3)
    @DeleteMapping(value = "/sysUser/del")
    public ApiResult del(@RequestBody Long[] ids) {
        sysUserDemoService.del(ids);
        return ApiResultUtils.ok();
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "文件",dataType = "__File",required = true,allowMultiple = true),
            @ApiImplicitParam(name = "title",value = "标题",required = true),
    })
    @ApiOperation(value = "文件上传")
    @ApiOperationSupport(order = 4)
    @PostMapping("/testupload")
    public ApiResult<String> testupload(@RequestParam(value = "file")MultipartFile multipartFile,@RequestParam(value = "title")String title){
        String data="fileName:"+multipartFile.getOriginalFilename()+",title:"+title;
        return ApiResultUtils.ok(data);
    }


    @ApiOperation(value = "下载文件1")
    @GetMapping(value = "/download1",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadZip(HttpServletResponse response){
        try{
            File file=new File("/Users/nidazhong/Desktop/47D0887C-F6ED-4a67-B70E-575050658E9C.png");
            if (file.exists()){
                response.setContentType("multipart/form-data");
                //
                response.addHeader("Content-Disposition","attachement;filename=aaa.xls");
                // 写
                OutputStream outputStream= response.getOutputStream();
                // 读
                FileInputStream fileInputStream=new FileInputStream(file);
                int i=-1;
                byte[] b=new byte[1024*1024];
                while ((i=fileInputStream.read(b))!=-1){
                    outputStream.write(b,0,i);
                }
                IoUtil.close(fileInputStream);
                IoUtil.close(outputStream);
            }
        } catch (Exception e){

        }

    }

    @GetMapping("/download2")
    @ApiOperation(value = "下载文件2")
    public void download(HttpServletResponse response){
        String path="/Users/nidazhong/Desktop/47D0887C-F6ED-4a67-B70E-575050658E9C.png";
        log.info("download-path:{}",path);
        response.setHeader("Access-Control-Expose-Headers","Content-Disposition");
        ServletUtil.write(response,new File(path));
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



    /**
     * params -> 向文档添加一个实际不存在的参数
     */
    @ApiOperation(value = "测试功能-a", notes = "测试功能A(domain=aaa)")
    @PostMapping(value = "/rest1", params = {"domain=aaa"})
    public Object rest_aaa(Integer goodsId, Integer goodsCount) {
        System.err.println("/test/rest_aaa ......" + goodsId + ", " + goodsCount);
        return "AAA";
    }

    @ApiOperation(value = "测试功能-b", notes = "测试功能B(domain=bbb)")
    @PostMapping(value = "/rest2", params = {"domain=bbb"})
    public Object restb_bbb(String username, @RequestParam("pwd") String password) {
        System.err.println("/test/rest_bbb ......" + username + ", " + password);
        return "BBB";
    }


    @PutMapping("projects/{name}")
    @ApiOperation(value = "修改一个项目的名称",hidden = true)
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






}
