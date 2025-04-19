# wx-java-channel-spring-boot-starter

## 快速开始
1. 引入依赖
    ```xml
    <dependencies>
        <dependency>
            <groupId>com.github.binarywang</groupId>
            <artifactId>wx-java-channel-multi-spring-boot-starter</artifactId>
            <version>${version}</version>
        </dependency>
   
        <!-- 配置存储方式为jedis 则引入jedis -->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>${jedis.version}</version>
        </dependency>

        <!-- 配置存储方式为redisson 则引入redisson -->
        <dependency>
            <groupId>org.redisson</groupId>
            <artifactId>redisson</artifactId>
            <version>${redisson.version}</version>
        </dependency>

        <!-- 配置存储方式为redis_template 则引入spring data redis -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
    </dependencies>
    ```
2. 添加配置(application.properties)
    ```properties
    # 视频号配置(必填)
    ## 视频号小店的appId和secret
    wx.channel.app-id=@appId
    wx.channel.secret=@secret
    # 视频号配置 选填
    ## 设置视频号小店消息服务器配置的token
    wx.channel.token=@token
    ## 设置视频号小店消息服务器配置的EncodingAESKey
    wx.channel.aes-key=
    ## 支持JSON或者XML格式，默认JSON
    wx.channel.msg-data-format=JSON
    ## 是否使用稳定版 Access Token
    wx.channel.use-stable-access-token=false
    
    
    # ConfigStorage 配置（选填）
    ## 配置类型: memory(默认), jedis, redisson, redis_template
    wx.channel.config-storage.type=memory
    ## 相关redis前缀配置: wx:channel(默认)
    wx.channel.config-storage.key-prefix=wx:channel
    wx.channel.config-storage.redis.host=127.0.0.1
    wx.channel.config-storage.redis.port=6379
    wx.channel.config-storage.redis.password=123456
    
    # redis_template 方式使用spring data redis配置
    spring.data.redis.database=0
    spring.data.redis.host=127.0.0.1
    spring.data.redis.password=123456
    spring.data.redis.port=6379
    
    # http 客户端配置（选填）
    ## # http客户端类型: http_client(默认)
    wx.channel.config-storage.http-client-type=http_client
    wx.channel.config-storage.http-proxy-host=
    wx.channel.config-storage.http-proxy-port=
    wx.channel.config-storage.http-proxy-username=
    wx.channel.config-storage.http-proxy-password=
    ## 最大重试次数，默认：5 次，如果小于 0，则为 0
    wx.channel.config-storage.max-retry-times=5
    ## 重试时间间隔步进，默认：1000 毫秒，如果小于 0，则为 1000
    wx.channel.config-storage.retry-sleep-millis=1000
    ```
3. 自动注入的类型
- `WxChannelService`
- `WxChannelConfig`
4. 使用样例
```java
import me.chanjar.weixin.channel.api.WxChannelService;
import me.chanjar.weixin.channel.bean.shop.ShopInfoResponse;
import me.chanjar.weixin.channel.util.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
  @Autowired
  private WxChannelService wxChannelService;

  public String getShopInfo() throws WxErrorException {
    // 获取店铺基本信息
    ShopInfoResponse response = wxChannelService.getBasicService().getShopInfo();
    // 此处为演示，如果要返回response的结果，建议自己封装一个VO，避免直接返回response
    return JsonUtils.encode(response);
  }
}
```

