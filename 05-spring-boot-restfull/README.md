# spring-boot-restfull(v0.0.1.待完善)

### RESTFUL风格

**Restfull 定义：用url定义资源，用method定义请求操作**

简单理解，就是将        
`http://localhost:8080/boot/user?userId=12&userName=zhangsan`       
变成      
`http://localhost:8080/boot/user/12/zhangsan`

还可以用 http 请求方法映射不同的业务请求
```java
    @GetMapping //查询

    @PostMapping //提交

    @DeleteMapping //删除
 
    @PutMapping //更新（修改）
```
###### 目前大家并不完全遵守这种规则，而是只用GET/POST两种方式，GET对应查询操作，POST对应增删改这个操作

### RestTemplate

API的简单介绍

1. 用url定位资源
2. url中用名字不要动词 website/getUsers -> website/users/{id}
3. 用 Post Delete Put Get 对应 增 删 改 查
4. 最好在url中添加版本,面向修改关闭,面向扩展开启
5. 需要条件可用
6. 统一规范状态码 200 success 400 bad request 500 server error