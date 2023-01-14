CREATE TABLE t_update_history
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


CREATE TABLE org_employee
(
    emp_id          BIGINT      not null auto_increment COMMENT '员工ID',
    name            VARCHAR(30) not null COMMENT '姓名',
    job             VARCHAR(100) COMMENT '工作',
    department      VARCHAR(100) COMMENT '部门',
    entry_date_time datetime    not null COMMENT '变更字段名，即表的字段名',
    PRIMARY KEY (emp_id)
);

INSERT INTO org_employee(name, job, department, entry_date_time) VALUES ('Jack', 'Coder', 'devDept', NOW())