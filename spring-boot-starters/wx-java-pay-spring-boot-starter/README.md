# 使用说明
1. 在自己的Spring Boot项目里，引入maven依赖
```xml
    <dependency>
        <groupId>com.github.binarywang</groupId>
        <artifactId>wx-java-pay-spring-boot-starter</artifactId>
        <version>${version}</version>
    </dependency>
 ```
2. 添加配置(application.yml)
###### 1）V2版本
```yml
wx:
  pay:
    appId: 
    mchId: 
    mchKey: 
    keyPath:
```
###### 2）V3版本
```yml
wx:
  pay:
    appId: xxxxxxxxxxx
    mchId: 15xxxxxxxxx #商户id
    apiHostUrl: http://10.0.0.1:3128 # 可选：代理主机
    apiHostUrlPath: /api-weixin # 可选：代理入口前缀
    apiV3Key: Dc1DBwSc094jACxxxxxxxxxxxxxxx #V3密钥
    certSerialNo: 62C6CEAA360BCxxxxxxxxxxxxxxx
    privateKeyPath: classpath:cert/apiclient_key.pem #apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径
    privateCertPath: classpath:cert/apiclient_cert.pem #apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径
```
###### 3）V3服务商版本
```yml
wx:
  pay: #微信服务商支付
    configs:
    - appId: wxe97b2x9c2b3d #spAppId
      mchId: 16486610 #服务商商户
      subAppId: wx118cexxe3c07679 #子appId
      subMchId: 16496705 #子商户
      apiV3Key: Dc1DBwSc094jAKDGR5aqqb7PTHr #apiV3密钥
      privateKeyPath: classpath:cert/apiclient_key.pem #服务商证书文件，apiclient_key.pem证书文件的绝对路径或者以classpath:开头的类路径（可以配置绝对路径）
      privateCertPath: classpath:cert/apiclient_cert.pem #apiclient_cert.pem证书文件的绝对路径或者以classpath:开头的类路径
```
