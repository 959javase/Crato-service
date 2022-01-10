CREATE TABLE IF NOT EXISTS `device_info` (
    `id`               BIGINT UNSIGNED AUTO_INCREMENT   COMMENT 'id',
    `host_name`        VARCHAR(50)   NOT NULL           COMMENT '机器名称',
    `host_ip`          VARCHAR(50)   NOT NULL           COMMENT '机器IP',
    `room_name`        VARCHAR(100)                     COMMENT '机房',
    `rack_name`        VARCHAR(100)                     COMMENT '机架',
    `cabinet_name`     VARCHAR(100)                     COMMENT '机柜',
    `slot_name`        VARCHAR(100)                     COMMENT '槽位',
    `account_name`     VARCHAR(100)                     COMMENT '账号',
    `device_type`      VARCHAR(100)  NOT NULL           COMMENT '设备类型',
    `serial`           VARCHAR(100)  NOT NULL           COMMENT '序列号',
    `switch_server`    VARCHAR(20)                      COMMENT '交换机名称',
    `disk_quantity`    BIGINT                           COMMENT '磁盘数量',
    `status`           VARCHAR(20)                      COMMENT '设备状态',
    `date_time`        timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP    COMMENT '创建日期',
    PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
