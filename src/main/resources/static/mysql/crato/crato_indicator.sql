CREATE TABLE IF NOT EXISTS `crato_indicator` (
    `id`          BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
    `name`          VARCHAR(100)  NOT NULL           COMMENT '账号名称',
    `file_size`     BIGINT        DEFAULT 0          COMMENT '上传大小',
    `down_size`     BIGINT        DEFAULT 0          COMMENT '下载大小',
    `qps`           BIGINT        DEFAULT 0          COMMENT 'qps',
    `cost`          BIGINT        DEFAULT 0          COMMENT '费用',
    `pin_size`      BIGINT        DEFAULT 0          COMMENT '存储空间',
    `used_size`     BIGINT        DEFAULT 0          COMMENT '使用空间',
    `granularity`   VARCHAR(20)     NOT NULL           COMMENT '粒度',
    `date_time`     timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建日期',
    PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;