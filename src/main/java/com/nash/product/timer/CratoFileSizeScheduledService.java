package com.nash.product.timer;

import com.nash.product.entity.indicator.CratoDashBoardIndicator;
import com.nash.product.mapper.crato.CratoDashBoardIndicatorMapper;
import com.nash.product.mapper.crato.CratoFileMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Description：定时任务执行器
 * Author: zhujian from nashcloud
 * Date: Created in 2021/8/1 8:02 下午
 */
@Slf4j
@Component
@Async
public class CratoFileSizeScheduledService {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * crato文件记录映射器
     */
    @Autowired
    private CratoFileMapper cratoFileMapper;
    /**
     * 文件大小5分钟汇聚定时任务
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void fileSize5Scheduled(){
        executeScheduleTask(300);
    }

    /**
     * 文件大小1小时汇聚定时任务
     */
    //@Scheduled(cron = "0 5 */1 * * ?")
    public void fileSize60Scheduled() {
        executeScheduleTask(3600);
    }

    /**
     * 文件大小按天汇聚定时任务
     */
    //@Scheduled(cron = "0 0 3 */1 *  ?")
    public void fileSizeDayScheduled() {
        executeScheduleTask(86400);
    }

    private void executeScheduleTask(int frequ) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
        String endTime=dtf.format(LocalDateTime.now());
        String startTime=dtf.format(LocalDateTime.now().minusMinutes(5));
        int res= cratoFileMapper.batchInsertIndicator(startTime, endTime);
        log.info("schedule %d seconds file_size data success: %d", endTime);
    }

    /**
     * 批量添加文件大小指标
     * @return
     */
    private void batchInsertIndicator(List<CratoDashBoardIndicator> cratoDashBoardIndicators, long currentTime) {
        //可以执行批量操作的sqlSession
        SqlSession openSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);

        //批量保存执行前时间
        long start = System.currentTimeMillis();
        try{
            CratoDashBoardIndicatorMapper mapper = openSession.getMapper(CratoDashBoardIndicatorMapper.class);
            for( CratoDashBoardIndicator item : cratoDashBoardIndicators){
                mapper.insertIndicator(new CratoDashBoardIndicator(item.getName(), item.getFileSize(), 0, 0, 0, 0, 0, "five", currentTime));

            }
            openSession.commit();
            long end=  System.currentTimeMillis();
            //批量保存执行后的时间
            log.info("执行时长"+(end-start));
        } catch (Exception e) {
            log.info("batch add crato indicator failed: %s", e.getMessage());
        }
        finally{
            openSession.close();
        }
    }

}
