package com.chengqj.springbootsenior.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleDemo {
    /**
     * 定时任务
     * @Scheduled(cron = "")定时任务
     * cron属性：cron表达式。定时任务触发是时间的一个字符串表达形式。
     *
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void scheduledMethod(){
        System.out.println("定时器被触发" + new Date());
    }

}
