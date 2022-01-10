CREATE TABLE IF NOT EXISTS `crust_node_info` (
    `id`                    BIGINT UNSIGNED AUTO_INCREMENT COMMENT 'id',
    `account_name`          VARCHAR(100)   NOT NULL          COMMENT '账号名称',
    `account_password`      VARCHAR(100)   NOT NULL          COMMENT '账号密码',
    `host_ip`               VARCHAR(100)   NOT NULL          COMMENT 'IP',
    `serial_id`             VARCHAR(100)                     COMMENT '设备序列号',
    `mnemonic`              VARCHAR(1024)   NOT NULL          COMMENT '助记词',
    `json`                  VARCHAR(1024)   NOT NULL          COMMENT 'json',
    `owner_node`            VARCHAR(1024)                     COMMENT 'owner节点名称',
    `account_type`          VARCHAR(20)    NOT NULL          COMMENT '账号类型，C账号orS账号',
    `node_type`             VARCHAR(20)    NOT NULL          COMMENT '节点类型，owner or member',
    `service_type`          VARCHAR(20)    NOT NULL          COMMENT '服务类型，sealing，ungroup, grouped',
    PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
