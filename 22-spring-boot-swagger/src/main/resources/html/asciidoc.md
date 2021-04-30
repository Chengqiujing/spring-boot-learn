# swagger API文档


<a name="overview"></a>
## 概览
简单优雅的restful风格


### 版本信息
*版本* : 1.0


### 许可信息
*服务条款* : http://www.btbullet.com


### URI scheme
*域名* : localhost:8080  
*基础路径* : /


### 标签

* 测试接口 : Restful Controller




<a name="paths"></a>
## 资源

<a name="cba1afa8756152014d0034b3b9dc688b"></a>
### 测试接口
Restful Controller


<a name="restusingpost"></a>
#### 测试rest
```
POST /rest/testRestful
```


##### 说明
获取restEntity


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**restEntity**  <br>*必填*|测试entity|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|成功|[RestEntity](#restentity)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/rest/testRestful
```


###### 请求 query
```
json :
{
  "restEntity" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "age" : 19,
  "name" : "我是rest"
}
```


<a name="rest2usingpost"></a>
#### 获取人员信息
```
POST /rest/testRestful2
```


##### 说明
获取人员的年龄,姓名


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**age**  <br>*必填*|年龄|[Integer](#integer)|
|**Body**|**name**  <br>*必填*|姓名|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|成功|[RestEntity](#restentity)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/rest/testRestful2
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "age" : 19,
  "name" : "我是rest"
}
```




<a name="definitions"></a>
## 定义

<a name="restentity"></a>
### RestEntity

|名称|说明|类型|
|---|---|---|
|**age**  <br>*可选*|age  <br>**样例** : `19`|integer (int32)|
|**name**  <br>*可选*|name  <br>**样例** : `"我是rest"`|string|





