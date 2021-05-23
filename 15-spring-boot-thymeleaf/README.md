

```thymeleafexpressions
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<!--th:text-->

    <p th:text="${msg}"></p>
    <div th:text="${msg}"></div>
    <input th:text="${msg}">
<!--th:value-->
<input th:value="123"/>
<input th:attr="value=${user.id}">

<!--
标准变量表达式
${...}
从tomcat上下文环境中的变量
-->
<p th:text="${map.one}"></p>
<p th:text="${map.two}"></p>

<!--
选择变量表达式
*{...}
先使用th:object = "${user}"绑定对象
然后用*{name}取
-->
<p th:object="${user}">
    <span th:text="*{name}"></span>
    <span th:text="*{address}"></span>
</p>

<!-- url 表达式 th:href-->
<a href="1000" th:href="@{'http://localhost:8080/boot/user?id='+${user.id}}">test url</a>
<!--url 表达式 拼接不需要引号-->
<a href="1000" th:href="@{|http://localhost:8080/boot/user?id=${user.id}&name=${user.name}|}">test url2</a>


<a href="1000" th:href="@{'boot/index'}"></a>  <!-- 前端 <a href="boot/index"></a> -->
<a href="1000" th:href="@{'/boot/index'}"></a>  <!-- 前端 <a href="/chengqj/boot/index"></a> 如果有的话，会自动加上下文路径-->

    <!--
    循环
    循环信息：可以用 循环项Stat 来表示或者使用interStat来定义(要首先声明 user,interStat:${list})
    项包含：
    index
    count
    size
    even
    odd
    first
    last
    -->
<div th:each="user:${list}">
    <p th:text="${user.id}"></p>
    <p th:text="${userStat.index}"></p>
</div>

<!--map 循环 th:each-->
    <table border="1" width="30%">
        <tr>
            <th>ID</th>
            <th>map->key</th>
        </tr>
        <tr th:each="entry : ${map}">
            <td th:text="${entry.value}"></td>
            <td th:text="${entry.key}"></td>
        </tr>
    </table>

    <!--如若 attribute是动态的 用 th:+ attribute 表示 比如 th:id="${}" th:src-->
    <div th:id="${user.id}"></div>
    <img th:src="@{/png/p1.jpg}"/>
    <script th:src="@{/js/test.js}"></script>
    <!--如果需要动态 action 可以用 th:action-->
    <from id="form1" th:action="@{/boot/user}" th:method="post">
        <input th:name="${user.name}"/>
    </from>
    <div th:onclick="'getId()'" style="width: 100px;height: 100px;background-color: red"></div>

    <div th:style="'height:100px;width:50px;background-color:gray'"></div>

    <!--判断条件 th:if-->
    <span th:if="${sex} == '男'">性别：男</span>
    <span th:if="${sex} == 'nv'">性别：女</span>
    <!--还可以这么写-->
    <span th:if="${sex == '男'}">性别：男</span>
    <span th:if="${sex == 'nv'}">性别：女</span>
    <!-- 反选判断 th:unless-->
    <span th:unless="${sex == '男'}">性别：男</span>
    <span th:unless="${sex == 'nv'}">性别：女</span>
    <div th:switch="${user.id}">
        <span th:case="100">ID为1</span>
        <span th:case="2">ID为2</span>
        <span th:case="3">ID为3</span>
    </div>

<!--
内联脚本 内联文本
在元素或父元素上指定 th:inline=有三个取值类型（text，javascript，none）
用 [[...]] 展示变量数据
<span th:inline="text">[[${user.id}]]</span>
等价于
<span th:text="${user.id}">
-->
<div th:inline="text">
    <span>[[${user.name}]]</span>
</div>

<script th:inline="javascript" type="text/javascript">
    var user = [[${user.name}]];
    console.log(user);
</script>

<!--
字面量
文本字面量
数字字面量
boolean字面量
null字面量
-->
<span th:text="${123 + 123 }"></span>
<span th:if="${true == 123 }"></span>
<span th:if="${'123' == null}"></span>
<span th:if="${'123' eq '123'}"></span>


<!--
字符串拼接
两种方式：
th:text="'当前是低'+${sex}"
另一种更优雅
th:text="|当前是第${sex}页，共${sex}页|"
-->
<p th:text="'abcd' + ${user.id}"></p>
<p th:text="|abcd${sex}|"></p>

<!--三目运算-->

<span th:text="${sex eq 'nv'}? '女' : '男'"></span>

<!--算数和判断
算数运算  + - * /
关系比较  > < >= <= (gt,lt,ge,le)
相等判断  == !=(eq,ne)

都可以
-->

<!--
内置对象

以# + 对象名调用
#httpServletRequest.getAttribute() //2.0
#request.getAttribute()  //3.0

#httpSession
#session
-->
<p th:text="${#request.getAttribute('lisi')}"></p>
<p th:text="${#session.getAttribute('lisi')}"></p>

<!-- 还有大量的内置对象 可以通过# 提示观察，也可从thymeleaf.org官网文档进行查看使用-->

</body>
</html>
```

还有一部分内容是关于thymeleaf公共布局的，由于内容较多，这里暂时先不整理请移步网络博客
[Thymeleaf 模板布局 th:fragment、th:replace、th:insert、th:remove](https://blog.csdn.net/wangmx1993328/article/details/84747497)


