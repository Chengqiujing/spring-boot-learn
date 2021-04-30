# spring-boot-jsp

### POM文件

```html
<!--引入Spring boot内嵌Tomcat对JSP的解析包-->
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
        </dependency>
        <!--servlet依赖的jar包start-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
        </dependency>
        <!--JSP依赖的jar包-->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>javax.servlet.jsp-api</artifactId>
            <version>2.3.1</version>
        </dependency>
        <!--JSTL标签依赖的jar包-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>
```

### JSP目录创建

要在contextpath下（在maven中，contextpath就是src/main下的webapp）创建webapp目录

关于springboot目录: src/main/resources下有目录static，是存放静态资源的，这些资源可以通过浏览器url直接定位访问。还有一个目录templates是springboot       
指定的模板存放目录，这个目录是对外安全的。这两个目录的名称不可修改。对于JSP页面就按照maven约定放在webapp下就好（也有放在webapp/WEB-INF下的）。       
**springboot会默认搜索/src/main/resource/static和/src/main/webapp两个目录**       
**注意：页面最好按照约定去放，不然会冒出很多路径问题**

若是IDEA要在POM文件中添加
```html
        IDEA中不添加该编译配置项，jsp文件将不会被编译到target目录中
        <build>
            <resource>
                <directory>src/main/webapp</directory>
                <targetPath>META-INF/resources</targetPath>
                <includes>
                    <include>**/*.*</include>
                </includes>
            </resource>
        </build>
```

### POM文件修改

**jsp前缀**       
`spring.mvc.view.prefix=/`

**jsp后缀**       
`spring.mvc.view.suffix=.jsp`

**静态资源指定路径，可以用逗号隔开；可以配置，也可用 默认目录**      
spring.resources.static-locations=classpath:/META-INF/resources/,classpath:/resources/

**static-path-pattern，这个配置项是告诉springboot，应该以什么样的方式去寻找资源。默认配置为 /\* 。换句话说，只有静态资源满足什么样的匹配条件，Spring Boot才会处理静态资源请求，我们也可配置成 /user/\**,这样我们必须输入/user/index.html才能访问到这些目录下的资源，**

spring.mvc.static-path-pattern: /**