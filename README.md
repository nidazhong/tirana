# 麦子Mini版-SpringBoot项目

## 依赖版本

| soft        | version |
|-------------|---------|
| Java        | 17      |
| Maven       | 3.6.3   |
| Spring Boot | 2.7.7   |
| Mysql       | 8       |
| knife4j     | 4.0.0   |

## 特点

- 统一异常处理，自动异常数据落盘
- 统一响应处理，使用@History，@HistoryRecord 灵活变通
- 数据表变更实现代理记录
- 序列化枚举
- json数据字段支持

## TODO
- 数据库定时清理异常日志
- Knife4j文件下载有bug，待升级
- Knife4j 和 ResponseBodyAdvice 返回包装的api完整显示问题
- 返回前端默认值的问题
- logback-spring.xml的配置
- json数据保存数据库问题

## 升级springboot3注意
* 1、knife4j 版本
* 2、javax
* 3、mybatis-plus

## 帮助
- json字段序列化 https://blog.csdn.net/qnxyj/article/details/123095384
- jackson null返回默认值 https://www.cnblogs.com/top-sky-hua/p/15144358.html