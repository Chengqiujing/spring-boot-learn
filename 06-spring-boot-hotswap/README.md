# spring-boot-hotswap

### POM配置文件

```xml
<!-- spring-boot热部署插件 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```
IDEA中，当最文件修改，然后右键重新build后，项目会自动重新部署并启动。或者点击启动项旁边的小锤头.     
Eclipse中只要文件修改，就会自动部署。

之所以热部署比较快,是因为它使用了两个类加载器,其中一个用以第三方jar包,这些类不容易变动.另一个用以我们的项目代码,比较容易变动. 热部署只部署
项目的部分.
*以前觉得这个功能很鸡肋,现在看来很香*