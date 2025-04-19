# wx-java-miniapp-spring-boot-starter
## 快速开始
1. 引入依赖
    ```xml
    <dependency>
        <groupId>com.github.binarywang</groupId>
        <artifactId>wx-java-miniapp-spring-boot-starter</artifactId>
        <version>${version}</version>
    </dependency>
    ```
2. 添加配置(application.properties)
    ```properties
    # 小程序配置(必填)
    wx.miniapp.appid = appId
    wx.miniapp.secret = @secret
    wx.miniapp.token = @token
    wx.miniapp.aesKey = @aesKey
    wx.miniapp.msgDataFormat = @msgDataFormat                  # 消息格式，XML或者JSON.
    wx.miniapp.use-stable-access-token=@useStableAccessToken
    # 存储配置redis(可选)
    # 注意: 指定redis.host值后不会使用容器注入的redis连接(JedisPool)
    wx.miniapp.config-storage.type = Jedis                     # 配置类型: Memory(默认), Jedis, RedisTemplate
    wx.miniapp.config-storage.key-prefix = wa                  # 相关redis前缀配置: wa(默认)
    wx.miniapp.config-storage.redis.host = 127.0.0.1
    wx.miniapp.config-storage.redis.port = 6379
    # http客户端配置
    wx.miniapp.config-storage.http-client-type=HttpClient      # http客户端类型: HttpClient(默认), OkHttp, JoddHttp
    wx.miniapp.config-storage.http-proxy-host=
    wx.miniapp.config-storage.http-proxy-port=
    wx.miniapp.config-storage.http-proxy-username=
    wx.miniapp.config-storage.http-proxy-password=
    ```
3. 自动注入的类型
- `WxMaService`
- `WxMaConfig`

