package com.chengqj.study.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengqj.study.mybatisplus.test.dao.UserMapper;
import com.chengqj.study.mybatisplus.test.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sound.sampled.Line;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chengqiujing
 * @Date 2020/4/13 9:09
 * @Desc
 */
@SpringBootTest
public class SelectTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void selectById(){
        User user = userMapper.selectById("321456952");
        System.out.println(user);

    }
    @Test
    void selectByIds(){
        List<Integer> integers = Arrays.asList(1, 2);
        List<User> users = userMapper.selectBatchIds(integers);
        users.forEach(System.out::println);

    }
    @Test
    void selectByMap(){
        HashMap<String, Object> columnMap = new HashMap<>();
        columnMap.put("name","张三");
        columnMap.put("age",25);
        List<User> users = userMapper.selectByMap(columnMap);
        users.forEach(System.out::println);

    }

    //条件构造器
    @Test
    void selectByWraper(){
        //获取条件对象两种方式
        //QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        QueryWrapper<User> query = Wrappers.<User>query();
        //大于，like
        //query.like("name","丽").lt("age",40);
        ////区间，非空
        //query.like("name","丽").between("age",20,40).isNotNull("email");
        ////or，右模糊，排序
        //query.likeRight("name","徐").or().ge("age",25).orderByDesc("age");
        //子查询，函数调用
        //query.apply("date_format(create_time,'%Y-%m-%d')={0}","2005").inSql("id","select id from user where name like 'zhang'");
        //and与or优先级
        //query.likeRight("name","王").and(qw->qw.lt("age",40).isNotNull("email"));
        //and与or优先级
        //query.likeRight("name","王").or(q -> q.lt("age",40).isNotNull("email"));
        //小括号 （age<40 and email is not null） and name like '王%'
        //query.nested(q -> q.lt("age",40).isNotNull("email")).likeRight("name","王");
        //in
        //query.in("age", Arrays.asList(20,25,30));
        //last补充
        //query.in("age", Arrays.asList(20,25,30)).last("limit 1");
        //指定显示字段
        //query.select("id","name").in("age", Arrays.asList(20,25,30)).last("limit 1")
        //指定不显示字段
        //query.select(User.class, info -> !info.getColumn().equals("create_time") & !info.getColumn().equals("age")).in("age", Arrays.asList(20,25,30)).last("limit 1")

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);

    }

    //condition 执行条件
    @Test
    void condition(){
        String name = "王";
        String email= "email";
        QueryWrapper<User> query = Wrappers.<User>query();
        query.like(StringUtils.isNotBlank(name),"name",name);//是否加入到where语句中，由第一个参数决定
    }

    //在构造器中用entity指定条件
    @Test
    void entity(){
        User user = new User();
        user.setName("刘洪玉");
        user.setEmail("liuhongyu@163.ccom");
        user.setAge(25);
        QueryWrapper<User> query = Wrappers.<User>query(user);
        query.gt("age",20);

        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    //allEq
    @Test
    void allEq(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age",null);//如果不指定第二个参数为false，则null作为is null条件出现
        QueryWrapper<User> query = Wrappers.<User>query();
        query.allEq(map,false);//如果第二个参数指定为null,则忽略null
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    //BiPredicate条件过滤
    @Test
    void allEqBiPredicate(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age",null);
        QueryWrapper<User> query = Wrappers.<User>query();
        query.allEq((k,v)-> !k.equals("name") ,map);//等于name的将被过滤掉
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    //selectMaps
    @Test
    void map(){
        QueryWrapper<User> query = Wrappers.<User>query();
        query.select("avg(age) avg_age","min(age) min_age").groupBy("mannager_id")
                .having("sum(age)<{0}",100);//动态参数
        List<Map<String, Object>> maps = userMapper.selectMaps(query);
        maps.forEach(System.out::println);
    }

    //selectObjs
    @Test
    void objs(){
        QueryWrapper<User> query = Wrappers.<User>query();
        query.eq("name","zhangsan");
        List<Object> objects = userMapper.selectObjs(query);
    }

    //selectOne
    @Test
    void selectOne(){
        QueryWrapper<User> query = Wrappers.<User>query();
        query.eq("name","zhangsan");
        User user = userMapper.selectOne(query);//只能返回一条或者0条
        System.out.println(user);
    }

    //lambda条件构造器
    @Test
    void lambda(){
        //LambdaQueryWrapper<User> lambda = Wrappers.<User>query().lambda();
        //LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        //LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        //lambda.eq(User::getName,"zhangsan");
        List<User> zhangsan = new LambdaQueryChainWrapper<User>(userMapper).like(User::getName, "zhangsan").list();

//        List<User> list = userMapper.selectList(chainWrapper);
//        list.forEach(System.out::println);
    }

    //分页
    @Test
    void page(){
        QueryWrapper<User> query = Wrappers.<User>query();
        query.eq("name","zhangsan");

        Page<User> page = new Page<User>(1,2);
//        Page<Map<String,Object>> pages =  userMapper.selectMapsPage(page,query);
//        //Page<User> userPage = userMapper.selectPage(page, query);
//        List<Map<String, Object>> records = pages.getRecords();
//        System.out.println("总页数：" + pages.getPages());
//        System.out.println("总记录数：" + pages.getTotal());
    }




}
