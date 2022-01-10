CREATE TABLE IF NOT EXISTS `crato_file_record` (
    `id`          BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
    `name`          VARCHAR(100)   NOT NULL          COMMENT '账号名称',
    `file_name`     VARCHAR(100)   NOT NULL          COMMENT '文件名称',
    `cid`           VARCHAR(256)   NOT NULL          COMMENT 'cid',
    `file_size`     BIGINT         NOT NULL          COMMENT '文件大小',
    `cost`          BIGINT         DEFAULT -1        COMMENT '费用',
    `expired_time`  timestamp     NOT NULL           COMMENT '到期日期',
    `date_time`     timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建日期',
    PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;