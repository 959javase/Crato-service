CREATE TABLE IF NOT EXISTS `nashcloud_account` (
    `id`      BIGINT UNSIGNED AUTO_INCREMENT   COMMENT 'id',
    `name`       VARCHAR(100)  NOT NULL  COMMENT '账号名称',
    `password`   VARCHAR(256)  NOT NULL  COMMENT '账号密码',
    `mobile`     VARCHAR(20)   NOT NULL  COMMENT '手机号',
    `type`       VARCHAR(10)   NOT NULL  COMMENT '账号类型，主账号or子账号',
    `belong`     VARCHAR(100)  NOT NULL  COMMENT '归属账号名称',
    `access_key` VARCHAR(256)  NOT NULL  COMMENT 'ak',
    `secret_key` VARCHAR(256)  NOT NULL  COMMENT 'sk',
    `balance`    BIGINT        DEFAULT 0 COMMENT '余额',
    `date_time`  timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建日期',
    PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;