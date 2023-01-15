# 麦子商城Mini版

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
- 序列化枚举 @JsonFormat(shape = JsonFormat.Shape.OBJECT)

## 问题学习记录

### 日志记录重复

### 升级springboot3注意
* 1、knife4j 版本
* 2、javax
* 3、mybatis-plus

```
同一条日志既会通过 logger 记录，也会发送到 root 记录，因此应用 package 下的日志出现了重复记录
```

### 日志级别色彩丢失

```

```