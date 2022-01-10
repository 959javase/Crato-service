CREATE TABLE IF NOT EXISTS `crato_business_type` (
    `id`           BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
    `name`          VARCHAR(100)  NOT NULL          COMMENT '账号名称',
    `product`       VARCHAR(20)   NOT NULL          COMMENT '产品名称',
    `service_type`  VARCHAR(20)   NOT NULL          COMMENT '服务类型',
    `fixed_space`   BIGINT        DEFAULT 0            COMMENT '空间大小',
    `used`          BIGINT        DEFAULT 0          COMMENT '使用大小',
    `op_num`        BIGINT        DEFAULT 0          COMMENT '次数',
    `date_time`     timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建日期',
    PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;