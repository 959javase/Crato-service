CREATE TABLE IF NOT EXISTS `inspect_task` (
    `id`             BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
    `name`           VARCHAR(256)   NOT NULL          COMMENT '巡检任务名称',
    `owner`          VARCHAR(100)   NOT NULL          COMMENT 'owner',
    `script`         text           NOT NULL          COMMENT '巡检脚本',
    `process_script` text                             COMMENT '处理脚本',
    `date_time`      timestamp      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       COMMENT '时间',
    PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
