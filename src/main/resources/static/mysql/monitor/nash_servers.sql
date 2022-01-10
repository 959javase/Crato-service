CREATE TABLE IF NOT EXISTS `nash_servers` (
    `id`               BIGINT UNSIGNED AUTO_INCREMENT   COMMENT 'id',
    `host_name`        VARCHAR(50)   NOT NULL           COMMENT '机器名称',
    `host_ip`          VARCHAR(50)   DEFAULT 0          COMMENT '机器IP',
    `os`               VARCHAR(50)   DEFAULT 0          COMMENT '操作系统',
    `platform`         VARCHAR(50)   DEFAULT 0          COMMENT '平台',
    `platform_version` VARCHAR(50)   DEFAULT 0          COMMENT '平台版本',
    `kernel_version`   VARCHAR(50)   DEFAULT 0          COMMENT '内核版本',
    `company`          VARCHAR(20)   NOT NULL           COMMENT '公司名',
    `date_time`        timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建日期',
    PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;