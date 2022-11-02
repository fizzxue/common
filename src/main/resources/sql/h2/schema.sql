-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          varchar(255) NOT NULL COMMENT '主键',
    `account`     varchar(255) NOT NULL COMMENT '账号',
    `password`    varchar(255) NOT NULL COMMENT '密码，md5加密存储',
    `name`        varchar(255) NOT NULL COMMENT '姓名',
    `sex`         tinyint NULL COMMENT '性别，1男2女',
    `create_time` datetime(0) NULL COMMENT '创建时间',
    `update_time` datetime(0) NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
);
