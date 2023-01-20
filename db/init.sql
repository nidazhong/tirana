-- 历史变更记录表
CREATE TABLE base_update_history
(
    id            BIGINT       not null auto_increment COMMENT '标志ID',
    table_name    VARCHAR(100) not null COMMENT '表名',
    record_id     VARCHAR(100) not null COMMENT '变更的记录ID',
    field_name    VARCHAR(100) not null COMMENT '变更字段名，即表的字段名',
    value_before  VARCHAR(1000) COMMENT '修改前的字段值',
    value_after   VARCHAR(1000) COMMENT '修改后的字段值',
    operator_id   VARCHAR(100) NOT null COMMENT '修改人ID',
    operator_name VARCHAR(100) NOT null COMMENT '修改人姓名',
    modify_time   datetime     NOT null COMMENT '修改时间',
    PRIMARY KEY (id)
);

-- 员工表
CREATE TABLE org_employee
(
    emp_id          BIGINT      not null auto_increment COMMENT '员工ID',
    name            VARCHAR(30) not null COMMENT '姓名',
    job             VARCHAR(100) COMMENT '工作',
    department      VARCHAR(100) COMMENT '部门',
    entry_date_time datetime    not null COMMENT '变更字段名，即表的字段名',
    PRIMARY KEY (emp_id)
);

INSERT INTO org_employee(name, job, department, entry_date_time)
VALUES ('Jack', 'Coder', 'devDept', NOW());


-- 异常日志
create table sys_log_error
(
    id             bigint NOT NULL auto_increment COMMENT 'id',
    request_uri    varchar(200) COMMENT '请求URI',
    request_method varchar(20) COMMENT '请求方式',
    request_params text COMMENT '请求参数',
    user_agent     varchar(500) COMMENT '用户代理',
    ip             varchar(32) COMMENT '操作IP',
    error_info     text COMMENT '异常信息',
    creator        bigint COMMENT '创建者',
    create_date    datetime COMMENT '创建时间',
    primary key (id),
    key idx_create_date (create_date)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8mb4 COMMENT ='异常日志';

--
CREATE TABLE `sys_user_demo`
(
    `user_id`     bigint      NOT NULL AUTO_INCREMENT COMMENT '员工ID',
    `user_name`   varchar(30) NOT NULL COMMENT '姓名',
    `dep_id`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '部门Id',
    `position`    tinyint                                                       DEFAULT NULL COMMENT '职位枚举',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `delete_flag` int                                                           DEFAULT '0',
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

alter table sys_user_demo add column sex int;
alter table sys_user_demo add column extra_json json;
alter table sys_user_demo add column extra_object json;
alter table sys_user_demo add column extra_list json;
alter table sys_user_demo add column extra_array json;

INSERT INTO `sys_user_demo` (user_name, extra_json, extra_object, extra_list, extra_array, create_time) VALUES ('Lee','{\"id\": 6, \"name\": \"7\"}', '{\"id\": 1, \"name\": \"2\"}', '[{\"id\": 1, \"name\": \"2\"}, {\"id\": 2, \"name\": \"3\"}]', '[{\"id\": 1, \"name\": \"2\"}, {\"id\": 4, \"name\": \"5\"}]', NOW());


alter table sys_user_demo add column extra_json_arr_str varchar(2000);