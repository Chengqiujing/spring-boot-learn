package com.chengqj.study.quartz.starter;

import com.chengqj.study.quartz.test.QuartzDemo;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author chengqj
 * @Date 2020/8/17 23:23
 * @Desc
 */
@Component
public class Stater implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try{
            //1 .创建Job对象
            JobDetail job = JobBuilder.newJob(QuartzDemo.class).build();
            //2. 创建Trigger对象
            /**
             * 参数
             *  简单trigger触发时间：通过Quartz提供的方法来完成简单的重复调用
             *  cron Trigger：按照Cron的表达式来给定触发时间
             */
            Trigger build = TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(3)).build();

            //3. 创建Scheduler对象
            Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
            defaultScheduler.scheduleJob(job,build);

            //启动
            defaultScheduler.start();

        }catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
