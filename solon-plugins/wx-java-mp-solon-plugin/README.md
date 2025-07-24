# wx-java-mp-solon-plugin

## 快速开始

1. 引入依赖
    ```xml
    <dependency>
        <groupId>com.github.binarywang</groupId>
        <artifactId>wx-java-mp-solon-plugin</artifactId>
        <version>${version}</version>
    </dependency>
    ```
2. 添加配置(app.properties)
    ```properties
    # 公众号配置(必填)
    wx.mp.app-id=appId
    wx.mp.secret=@secret
    wx.mp.token=@token
    wx.mp.aes-key=@aesKey
    wx.mp.use-stable-access-token=@useStableAccessToken
    # 存储配置redis(可选)
    wx.mp.config-storage.type= edis                     # 配置类型: Memory(默认), Jedis, RedisTemplate
    wx.mp.config-storage.key-prefix=wx                  # 相关redis前缀配置: wx(默认)
    wx.mp.config-storage.redis.host=127.0.0.1
    wx.mp.config-storage.redis.port=6379
	  #单机和sentinel同时存在时，优先使用sentinel配置
	  #wx.mp.config-storage.redis.sentinel-ips=127.0.0.1:16379,127.0.0.1:26379
	  #wx.mp.config-storage.redis.sentinel-name=mymaster
    # http客户端配置
    wx.mp.config-storage.http-client-type=httpclient      # http客户端类型: HttpClient(默认), OkHttp, JoddHttp
    wx.mp.config-storage.http-proxy-host=
    wx.mp.config-storage.http-proxy-port=
    wx.mp.config-storage.http-proxy-username=
    wx.mp.config-storage.http-proxy-password=
	  # 公众号地址host配置
	  #wx.mp.hosts.api-host=http://proxy.com/
	  #wx.mp.hosts.open-host=http://proxy.com/
	  #wx.mp.hosts.mp-host=http://proxy.com/
    ```
3. 自动注入的类型

- `WxMpService`
- `WxMpConfigStorage`

4、参考demo：
https://github.com/binarywang/wx-java-mp-demo
