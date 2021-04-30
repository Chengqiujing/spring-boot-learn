package com.chengqj.study.mybatisplus.test.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.chengqj.study.mybatisplus.test.method.DeleteAllMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author chengqiujing
 * @Date 2020/4/14 10:39
 * @Desc
 */
@Component
public class MyDefaultInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList =  super.getMethodList(mapperClass);
        methodList.add(new DeleteAllMethod());//加入自定义的类
        methodList.add(new InsertBatchSomeColumn());
        return methodList;
    }
}
