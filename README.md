# Tirana 地拉那系统

## 简介
- 地拉那，阿尔巴尼亚首都 
- Spring Boot 经典，且后续持续更新
- 基于vue-element-admin的前端构成
- 结合了企业实践经验，可作为模版落地
- 实现了不同用户的动态权限

## 版本

| soft        | version |
|-------------|---------|
| Java        | 17      |
| Maven       | 3.6.3   |
| Spring Boot | 2.7.7   |
| Mysql       | 8       |
| Redis       | 6       |
| knife4j     | 4.0.0   |

## 功能特点

- 统一异常处理，自动异常数据落盘
- 统一响应处理，使用@History，@HistoryRecord 灵活变通
- 数据表变更实现代理记录
- 序列化枚举
- json数据字段支持
- JWT

## TODO
- 数据库定时清理异常日志
- Knife4j文件下载有bug，待升级
- Knife4j 和 ResponseBodyAdvice 返回包装的api完整显示问题
- 返回前端默认值的问题
- logback-spring.xml的配置
- json数据保存数据库问题, jsonArr
- 改名词后数据库名词sql名也要改

## 升级springboot3注意
* 1、knife4j 版本
* 2、javax
* 3、mybatis-plus

## 前端
- 权限系统 https://github.com/nidazhong/tirana-ui.git


## 帮助
- auth_node 为权限系统的笔记
- json字段序列化 https://blog.csdn.net/qnxyj/article/details/123095384
- jackson null返回默认值 https://www.cnblogs.com/top-sky-hua/p/15144358.html

学习来源：
尚硅谷通用权限管理系统 https://www.bilibili.com/video/BV1vh411w7PF/?spm_id_from=333.337.search-card.all.click
