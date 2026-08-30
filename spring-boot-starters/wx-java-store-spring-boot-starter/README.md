# wx-java-store-spring-boot-starter

## 快速开始
1. 引入依赖
    ```xml
    <dependencies>
        <dependency>
            <groupId>com.github.binarywang</groupId>
            <artifactId>wx-java-store-spring-boot-starter</artifactId>
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
    ## 微信小店的appId和secret
    wx.store.app-id=@appId
    wx.store.secret=@secret
    # 视频号配置 选填
    ## 设置微信小店消息服务器配置的token
    wx.store.token=@token
    ## 设置微信小店消息服务器配置的EncodingAESKey
    wx.store.aes-key=
    ## 支持JSON或者XML格式，默认JSON
    wx.store.msg-data-format=JSON
    ## 是否使用稳定版 Access Token
    wx.store.use-stable-access-token=false


    # ConfigStorage 配置（选填）
    ## 配置类型: memory(默认), jedis, redisson, redis_template
    wx.store.config-storage.type=memory
    ## 相关redis前缀配置: wx:store(默认)
    wx.store.config-storage.key-prefix=wx:store
    wx.store.config-storage.redis.host=127.0.0.1
    wx.store.config-storage.redis.port=6379
    wx.store.config-storage.redis.password=123456

    # redis_template 方式使用spring data redis配置
    spring.data.redis.database=0
    spring.data.redis.host=127.0.0.1
    spring.data.redis.password=123456
    spring.data.redis.port=6379

    # http 客户端配置（选填）
    ## # http客户端类型: http_client(默认)
    wx.store.config-storage.http-client-type=http_client
    wx.store.config-storage.http-proxy-host=
    wx.store.config-storage.http-proxy-port=
    wx.store.config-storage.http-proxy-username=
    wx.store.config-storage.http-proxy-password=
    ## 最大重试次数，默认：5 次，如果小于 0，则为 0
    wx.store.config-storage.max-retry-times=5
    ## 重试时间间隔步进，默认：1000 毫秒，如果小于 0，则为 1000
    wx.store.config-storage.retry-sleep-millis=1000
    ```
3. 自动注入的类型
- `WxStoreService`
- `WxStoreConfig`
4. 使用样例
```java
import com.binarywang.wxjava.store.api.WxStoreService;
import com.binarywang.wxjava.store.bean.shop.ShopInfoResponse;
import com.binarywang.wxjava.store.util.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
  @Autowired
  private WxStoreService wxStoreService;

  public String getShopInfo() throws WxErrorException {
    // 获取店铺基本信息
    ShopInfoResponse response = wxStoreService.getBasicService().getShopInfo();
    // 此处为演示，如果要返回response的结果，建议自己封装一个VO，避免直接返回response
    return JsonUtils.encode(response);
  }
}
```
