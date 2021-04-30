package com.chengqj.study.mybatisplus.test.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @Author chengqiujing
 * @Date 2020/4/14 10:32
 * @Desc
 */
//自定义sql注入器
public class DeleteAllMethod extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        //要执行的sql
        String sql = "delete from" + tableInfo.getTableName();
        //mapper接口方法名
        String method = "deleteAll";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addDeleteMappedStatement(mapperClass,method, sqlSource);
    }
}
