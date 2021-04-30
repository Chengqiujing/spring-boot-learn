# Springboot整合WebSocket

### 环境

- JDK 1.8

- Springboot 2.3.5.RELEASE
- 开发工具 IDEA

### 补充

##### ws与wss协议的区别

- WebSocket的ws协议时基于HTTP协议实现的
- WebSocket的wss协议是基于HTTPS协议实现的
- 一旦你的项目使用https协议,你的websocket就要使用wss协议才可以



### 依赖

##### 1. pom.xml

```xml
    <!--   web socket 依赖    -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-websocket</artifactId>
    </dependency>
```

##### 2. Springboot下配置

http下配置

暴露@endpoint注解路径

```java
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
```

https下配置

```java
@Configuration
public class WebSocketConfig {

    //TODO https下配置
}
```



##### 3.html代码

前端接收的生命周期函数

注意WebSocket用的url协议是`ws`或者`wss`

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script>
        var socket;
        if (typeof (WebSocket) == "undefined") {
            console.log("遗憾:您的浏览器不支持WebSocket");
        } else {
            socket = new WebSocket("ws://localhost:8888/ws/asset");

            // 打开连接事件
            socket.onopen = function () {
                console.log("Socket 已打开")
                socket.send("消息发送测试(From Client)");
            }
            // 收到消息事件
            socket.onmessage = function (msg) {
                document.getElementById('message').innerHTML += msg.data + '<br>';
            }
            // 连接关闭事件
            socket.onclose = function () {
                console.log("Socket已关闭");
            }
            // 发生错误事件
            socket.onerror = function () {
                console.log("Socket发生了错误");
            }
            // 窗口关闭时,关闭连接

        }

        function sendToServer() {
            let value = document.getElementById("text").value;
            socket.send(value);
        }

        function closeWebSocket() {
            socket.close();
        }
    </script>
</head>
<body>
<h3>请输入要发给服务器的内容</h3>
<label for="text">输入发送信息</label><input id="text" type="text"/>
<button onclick="sendToServer()">发送服务器消息</button>
<button onclick="closeWebSocket()">关闭连接</button>
<br>
信息:<span id="message"></span>
</body>
</html>
```

##### 4.java代码

后端接收的生命周期函数

```java
/**
 * @Author chengqj
 * @Date 2020/11/12 20:33
 * @Desc
 */
@Component
@Slf4j
@ServerEndpoint(value = "/ws/asset")
public class WSWebSocketServer {
    AtomicInteger onlineCount = new AtomicInteger();
    // concurrent包的线程安全list,用来存放每个客户端对应的Session对象
    private static CopyOnWriteArrayList<Session> sessionList = new CopyOnWriteArrayList<>();

    /**
     * 发送消息
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        sessionList.add(session);
        int cnt = onlineCount.incrementAndGet(); // 在线数+1
        log.info("有连接加入,当前连接数为:{}", cnt);
    }

    /**
     * 接收消息
     *
     * @param message
     * @param session
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("来自客户端的消息:{}", message);
        // sendMessage(session, "Echo消息内容: " + message);
        batchSendMessage(message);
    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        sessionList.remove(session);
        int cnt = onlineCount.decrementAndGet();
        log.info("有连接关闭,当前连接数为:{}", cnt);
    }

    /**
     * 出现错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误:{},Session ID:{}", error.getMessage(), session.getId());
    }

    private static void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(String.format("%s (From Server. Session ID=%s)", message, session.getId()));
    }

    private static void batchSendMessage(String message) throws IOException {
        for (Session session : sessionList) {
            sendMessage(session, message);
        }
    }
}
```

