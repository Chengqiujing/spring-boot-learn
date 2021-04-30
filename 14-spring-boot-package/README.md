# spring-boot-package

spring boot 部署打包

### war包

1. 启动类 继承 `SpringBootServletInitializer`

2. 覆盖一个方法

```java
//打war包需要重写这个方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
```

3. pom中添加spring boot打包插件

``` xml
<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```
并修改打包格式为war

```xml
<packaging>war</packaging>
```


4. 打包命令

1. 直接操作IDEA maven插件 Lifecycle下命令 install
2. 可以在mvn命令行执行 mvn clean package -DskipTests     或者 mvn clean package -Dmaven.test.skip=true

**ps:** 当使用docker 运行tomcat容器时，如果要挂载外部文件夹到webapps，需指定 `-v outDir:/usr/local/tomcat/webapps`      
如果指定为/usr/local/tomcat，则会报错

**docker的几个查看属性命令**

查看容器 名称，ip，端口,挂载卷：        
`docker inspect -f='{{.Name}} {{.NetworkSettings.IPAddress}} {{.HostConfig.PortBindings}} {{.Mounts}}' $(docker ps -aq)`


### jar包

1. pom文件中`<packaging>jar</packaging>`

2. 直接在 mvn命令行执行 mvn clean package -DskipTests     或者 mvn clean package -Dmaven.test.skip=true