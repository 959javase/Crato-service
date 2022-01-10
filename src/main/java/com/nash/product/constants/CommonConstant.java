package com.nash.product.constants;

/**
 * Description：
 * Author: zhujian from nashcloud
 * Date: Created in 2021/7/21 11:07 上午
 */
public class CommonConstant {
    /**
     * 项目subject
     */
    public static final String SUBJECT = "Crato";
    /**
     * 语言
     */
    public static final String LANG = "LANG";
    /**
     * 英文
     */
    public static final String ENGLISH = "English";
    /**
     * 中文
     */
    public static final String CHINESE = "Chinese";
    /**
     * 用户名
     */
    public static final String USER_NAME = "userName";
    /**
     * 管理员
     */
    public static final String ADMIN = "admin";
    /**
     * 子账号
     */
    public static final String SUB = "sub";
    /**
     * ak
     */
    public static final String ACCESS_KEY = "publicKey";
    /**
     * sk
     */
    public static final String SECRET_KEY = "privateKey";
    /**
     * success
     */
    public static final String SUCCESS = "success";
    /**
     * true
     */
    public static final String TRUE = "true";
    /**
     * crato服务方式固定空间 or 按次付费
     */
    public static final String FIXED_SPACE = "fixed_space";
    /**
     * crato服务方式固定空间 or 按次付费
     */
    public static final String EVERY_TIME = "every_time";
    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";
    /**
     * server
     */
    public static final String SERVER = "host";
    /**
     * 日期格式
     */
    public static final String DATA_FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 用户态cpu时间
     */
    public static final String CPU_USER = "用户态CPU占用时间";
    /**
     * 系统占用cpu时间
     */
    public static final String CPU_SYS = "系统态CPU占用时间";
    /**
     * cpu空闲时间
     */
    public static final String CPU_IDLE = "CPU空闲时间";
    /**
     * cpu ioawait
     */
    public static final String CPU_IOWAIT = "CPU IOWAIT";
    /**
     * 硬件中断次数
     */
    public static final String CPU_IRQ = "CPU硬件中断次数";
    /**
     * 软中断次数
     */
    public static final String CPU_SOFIRQ = "cpu软中断次数";
    /**
     * load1
     */
    public static final String LOAD1 = "1分钟平均负载";
    /**
     * load5
     */
    public static final String LOAD5 = "5分钟平均负载";
    /**
     * load15
     */
    public static final String LOAD15 = "15分钟平均负载";
    /**
     * process总量
     */
    public static final String LOAD_PROCESS_TOTAL = "进程总数";
    /**
     * process运行
     */
    public static final String LOAD_PROCESS_RUN = "运行进程数";
    /**
     * 内存swap总量
     */
    public static final String MEM_SWAP_TOTAL = "swap内存总量";
    /**
     * 使用量
     */
    public static final String MEM_SWAP_USED = "swap内存使用量";
    /**
     * 空闲量
     */
    public static final String MEM_SWAP_FREE = "swap内存剩余量";
    /**
     * 占比
     */
    public static final String MEM_SWAP_PERCENT = "swap内存使用占比";
    /**
     * 总量
     */
    public static final String MEM_VTOTAL = "内存总量";
    /**
     * 使用量
     */
    public static final String MEM_VUSED = "内存使用量";
    /**
     * 空闲量
     */
    public static final String MEM_VFREE = "内存剩余量";
    /**
     * 占比
     */
    public static final String MEM_VPERCENT = "内存使用占比";
    /**
     * 网络接收流量
     */
    public static final String NET_TRAFFIC_REV = "流入流量";
    /**
     * 网络发送流量
     */
    public static final String NET_TRAFFIC_SENT = "流出流量";
    /**
     * 网络接收流量
     */
    public static final String NET_PACKAGE_REV = "流入包数";
    /**
     * 网络发送流量
     */
    public static final String NET_PACKAGE_SENT = "流出包数";
    /**
     * 网络接收丢包
     */
    public static final String NET_DROP_REV = "流入丢包率";
    /**
     * 网络发送丢包
     */
    public static final String NET_DROP_SENT = "流出丢包率";
    /**
     * 网络接收错误
     */
    public static final String NET_ERROR_REV = "流入错误数";
    /**
     * 网络发送错误
     */
    public static final String NET_ERROR_SENT = "流出错误数";
    /**
     * 磁盘读次数
     */
    public static final String DISK_READ_COUNT = "磁盘读取数量";
    /**
     * 磁盘写次数
     */
    public static final String DISK_WRITE_COUNT = "磁盘写入数量";
    /**
     * 磁盘读字节
     */
    public static final String DISK_READ_TRAFFIC = "磁盘读取流量";
    /**
     * 磁盘写字节
     */
    public static final String DISK_WRITE_TRAFFIC = "磁盘写入流量";
    /**
     * 磁盘读时间
     */
    public static final String DISK_READ_TIME = "磁盘读取时间";
    /**
     * 磁盘写时间
     */
    public static final String DISK_WRITE_TIME = "磁盘写入时间";
    /**
     * io时间
     */
    public static final String IO_TIME = "磁盘IO";
    /**
     * 磁盘总大小
     */
    public static final String DISK_TOTAL = "磁盘总量";
    /**
     * 磁盘使用大小
     */
    public static final String DISK_USED = "磁盘使用率";
    /**
     * 磁盘空闲大小
     */
    public static final String DISK_FREE = "磁盘剩余量";
    /**
     * inode总量
     */
    public static final String INODE_TOTAL = "inode总量";
    /**
     * inode使用量
     */
    public static final String INODE_USED = "inode使用率";
    /**
     * inode空闲量
     */
    public static final String INODE_FREE = "indoe剩余量";
    /**
     * 机器信息
     */
    public static final String HOST_INFO = "host_info";
    /**
     * 异常机器信息
     */
    public static final String EXCRPTION_HOST_INFO = "exception_host_info";
    /**
     * 磁盘读次数
     */
    public static final String DISK_READ_COUNT_EN = "disk_read_count";
    /**
     * 磁盘写次数
     */
    public static final String DISK_WRITE_COUNT_EN = "disk_write_count";
    /**
     * 磁盘读字节
     */
    public static final String DISK_READ_TRAFFIC_EN = "disk_read_bytes";
    /**
     * 磁盘写字节
     */
    public static final String DISK_WRITE_TRAFFIC_EN = "disk_write_bytes";
    /**
     * 磁盘读时间
     */
    public static final String DISK_READ_TIME_EN = "disk_read_time";
    /**
     * 磁盘写时间
     */
    public static final String DISK_WRITE_TIME_EN = "disk_write_time";
    /**
     * io时间
     */
    public static final String IO_TIME_EN = "io_time";
    /**
     * 磁盘总大小
     */
    public static final String DISK_TOTAL_EN = "disk_total";
    /**
     * 磁盘使用大小
     */
    public static final String DISK_USED_EN = "disk_used";
    /**
     * 磁盘空闲大小
     */
    public static final String DISK_FREE_EN = "disk_free";
    /**
     * inode总量
     */
    public static final String INODE_TOTAL_EN = "inode_total";
    /**
     * inode使用量
     */
    public static final String INODE_USED_EN = "inode_used";
    /**
     * inode空闲量
     */
    public static final String INODE_FREE_EN = "inode_free";
    /**
     * 磁盘使用率
     */
    public static final String DISK_USED_PERCENT = "磁盘使用率";
    /**
     * 磁盘使用率
     */
    public static final String DISK_USED_PERCENT_EN = "inode_used_percent";
    /**
     * 磁盘inode使用率
     */
    public static final String INODE_USED_PERCENT = "inode使用率";
    /**
     * 磁盘inode使用率
     */
    public static final String INODE__USED_PERCENT_EN = "inode_used_percent";
    /**
     * crust
     */
    public static final String CRUST = "crust";
}
