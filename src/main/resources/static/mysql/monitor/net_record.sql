CREATE TABLE IF NOT EXISTS `net_record` (
    `id`               BIGINT UNSIGNED AUTO_INCREMENT   COMMENT 'id',
    `net_bytes_rev`    BIGINT   DEFAULT 0           COMMENT '接收流量',
    `net_bytes_send`   BIGINT   DEFAULT 0           COMMENT '发送流量',
    `net_package_rev`  BIGINT   DEFAULT 0           COMMENT '接收包数',
    `net_package_send` BIGINT   DEFAULT 0           COMMENT '发送包数',
    `net_drop_rev`     BIGINT   DEFAULT 0           COMMENT '流入丢包数',
    `net_drop_send`    BIGINT   DEFAULT 0           COMMENT '流出丢包数',
    `net_error_rev`    BIGINT   DEFAULT 0           COMMENT '流入错误数',
    `net_error_send`   BIGINT   DEFAULT 0           COMMENT '流出错误数',
    `host_ip`          VARCHAR(100)  NOT NULL      COMMENT 'ip',
    `name`             VARCHAR(50)   NOT NULL      COMMENT '指标类型',
    `date_time`        TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP      COMMENT '指标类型',
    PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;