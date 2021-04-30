<!-- Hystrix -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-netflix-hystrix</artifactId>
            <version>2.1.0.RELEASE</version>
        </dependency>

        <!-- Hystrix @HystrixCommand 依赖  用以简化Hystrix -->
        <dependency>
            <groupId>com.netflix.hystrix</groupId>
            <artifactId>hystrix-javanica</artifactId>
            <version>1.5.18</version>
        </dependency>
        
circuitBreakerRequestVolumeThreshold  请求次数

circuitBreakerSleepWindowInMilliseconds 时间窗口

circuitBreaker.errorThresholdPercentage 成功比率

execution.isolation.thread.timeoutInMilliseconds 超时时间