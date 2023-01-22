-- 用户角色
CREATE TABLE `sys_role`
(
    `id`          bigint                                                 NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `role_name`   varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
    `role_code`   varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci          DEFAULT NULL COMMENT '角色编码',
    `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci         DEFAULT NULL COMMENT '描述',
    `create_time` timestamp                                              NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint                                                NOT NULL DEFAULT '0' COMMENT '删除标记（0:可用 1:已删除）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='角色';