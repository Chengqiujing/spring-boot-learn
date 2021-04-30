package com.chengqj.study.mybatisplus.test.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author chengqiujing
 * @Date 2020/4/13 22:57
 * @Desc
 */
@Component
public class MyObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //3.x该方法已过时
        setInsertFieldValByName("createTime", LocalDateTime.now(),metaObject);
        // 3.x推荐方法
        boolean hasSetter = metaObject.hasSetter("createTime");//先判断是否有填充字段
        if(hasSetter){
            this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //3.x该方法已过时
        setUpdateFieldValByName("updateTime",LocalDateTime.now(),metaObject);
        Object val = getFieldValByName("updateTime",metaObject);
        if(val==null){
            // 3.x推荐使用
            this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        }
    }
}
