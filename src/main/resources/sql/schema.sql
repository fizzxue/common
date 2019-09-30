DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `id`          varchar(255) NOT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `sex`         tinyint(255) DEFAULT NULL COMMENT '1男2女',
    `create_time` datetime     DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
