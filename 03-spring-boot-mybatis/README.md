# spring-boot-mybatis

### mybatis 使用

1. 引入POM依赖

2. 指定mapper文件位置

> #mapper文件位置，当文件在resource下的时候必须要配置
>
> mybatis.mapper-locations=classpath:com/chengqj/study/springbootmybatis/mapper/*.xml

还可以指定mapper文件映射pojo的前缀

> #实体类包别名，不需要写前缀 
>
> mybatis.type-aliases-package=com.chengqj.study.springbootmybatis.entity

3. 指定mapper接口的扫描

两种方式：

1）可以在spring-boot启动类上 添加注解 `@MapperScan("com.chengqj.study.springbootmybatis.dao")`

2）可以在接口类上声明 注解 `@Mapper` 

4. 配置数据源

> spring.datasource.username=root
> spring.datasource.password=root
> spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
> spring.datasource.url=jdbc:mysql://localhost:3306/video?useUnicode=true&characterEncoding=utf8&useSSL=false

5. **注意事项**

如果是在IDEA中,如果映射xml文件没有放到resources中，还需要在POM中指定xml资源的编译规则。如下：

```xml
 <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.*</include>
                </includes>
            </resource>
            <!--<resource>-->
                <!--<directory>src/main/webapp</directory>-->
                <!--<targetPath>META-INF/resources</targetPath>-->
                <!--<includes>-->
                    <!--<include>**/*.*</include>-->
                <!--</includes>-->
            <!--</resource>-->
        </resources>
```
如果xml文件放到了resources中，则还需要在 核心配置文件 application.properties中指定配置文件位置，不然找不到。

`mybatis.mapper-locations=classpath:com/chengqj/study/springbootmybatis/mapper/*.xml`

### 逆向生成pojo代码

主要用于根据数据库配置，生成相应表的pojo类 mybatis的mapper接口类，已经mapper的xml文件

这里多一句，有逆向，自然有正向工程，也就是根据entity类生成 对应的表。

逆向配置步骤

1. 需要在POM中加入以下配置

```
<!--mybatis自动生成代码插件-->
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.6</version>
                <configuration>
                    <!-- 是否覆盖，true表示会替换生成的JAVA文件，false则不覆盖 -->
                    <overwrite>true</overwrite>
                    <verbose>true</verbose>
                    <configurationFile>generatorMapper.xml</configurationFile>
                </configuration>
                <dependencies>
                    <!--mysql驱动包-->
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>5.1.45</version>
                    </dependency>
                </dependencies>
            </plugin>
```

2. 在项目中添加generatorMapper.xml配置文件

详细见项目中该文件。

该文件可放在模块根目录，项目根目录等地方，需要在pom文件配置中声明。

`<configurationFile>generatorMapper.xml</configurationFile>`--在模块根目录

3. 执行生成

在maven插件中执行mybatis-generator下的mybatis-generator:generate 则会按照配置文件配置生成相应文件