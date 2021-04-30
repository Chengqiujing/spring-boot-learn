package com.chengqj.study.mybatisplus.test.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author chengqiujing
 * @Date 2020/4/12 23:24
 * @Desc
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfiguration {
    public static ThreadLocal<String> myTableName = new ThreadLocal<>();

    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor()
        ArrayList<ISqlParser> iSqlParser = new ArrayList<>();
        //动态表明处理器
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        Map<String, ITableNameHandler> tableNameHandlerMap = new HashMap<>();
        tableNameHandlerMap.put("user", new ITableNameHandler() {
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                return myTableName.get();
            }
        });
        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap);
        //多租户sql解析器
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId(boolean where) {
                return new LongValue(123132); //租户信息id，一般session等地方取
            }

            @Override
            public String getTenantIdColumn() {
                return "manager_id";//多租户标识字段
            }

            @Override
            public boolean doTableFilter(String tableName) {
                //排除哪个表不受多租户控制
                if("role".equals(tableName)){
                    return true;//当等于这个表名时，不添加多租户
                }
                return false;
            }
        });
        iSqlParser.add(tenantSqlParser);
        iSqlParser.add(dynamicTableNameParser);
        paginationInterceptor.setSqlParserList(iSqlParser);

        //特定SQL过滤
        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement mappedStatement = SqlParserHelper.getMappedStatement(metaObject);
                if("com.chengqj.study.mybatisplus.test.dao.UserMapper.selectAll".equals(mappedStatement.getId())){
                    return true;
                }

                return false;
            }
        });

        return paginationInterceptor;
    }
    //乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }
//    //性能分析插件
//    @Bean
//    @Profile({"dev","test"}) //指定开发，测试环境开启
//    public PerformanceInterceptor performanceInterceptor(){
//        return new PerformanceInterceptor();//3.2.0以上被移除，推荐使用第三方
//    }
}
