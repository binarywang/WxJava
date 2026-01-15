# wx-java-open-multi-spring-boot-starter

## 快速开始

1. 引入依赖
    ```xml
    <dependency>
        <groupId>com.github.binarywang</groupId>
        <artifactId>wx-java-open-multi-spring-boot-starter</artifactId>
        <version>${version}</version>
    </dependency>
    ```
2. 添加配置(application.properties)
    ```properties
    # 开放平台配置
    ## 应用 1 配置(必填)
    wx.open.apps.tenantId1.app-id=appId
    wx.open.apps.tenantId1.secret=@secret
    ## 选填
    wx.open.apps.tenantId1.token=@token
    wx.open.apps.tenantId1.aes-key=@aesKey
    wx.open.apps.tenantId1.api-host-url=@apiHostUrl
    wx.open.apps.tenantId1.access-token-url=@accessTokenUrl
    ## 应用 2 配置(必填)
    wx.open.apps.tenantId2.app-id=@appId
    wx.open.apps.tenantId2.secret=@secret
    ## 选填
    wx.open.apps.tenantId2.token=@token
    wx.open.apps.tenantId2.aes-key=@aesKey
    wx.open.apps.tenantId2.api-host-url=@apiHostUrl
    wx.open.apps.tenantId2.access-token-url=@accessTokenUrl
   
    # ConfigStorage 配置（选填）
    ## 配置类型: memory(默认), jedis, redisson, redistemplate
    wx.open.config-storage.type=memory
    ## 相关redis前缀配置: wx:open:multi(默认)
    wx.open.config-storage.key-prefix=wx:open:multi
    wx.open.config-storage.redis.host=127.0.0.1
    wx.open.config-storage.redis.port=6379
    ## 注意：当前版本暂不支持 sentinel 配置，以下配置仅作为预留
    # wx.open.config-storage.redis.sentinel-ips=127.0.0.1:16379,127.0.0.1:26379
    # wx.open.config-storage.redis.sentinel-name=mymaster
   
    # http 客户端配置（选填）
    wx.open.config-storage.http-proxy-host=
    wx.open.config-storage.http-proxy-port=
    wx.open.config-storage.http-proxy-username=
    wx.open.config-storage.http-proxy-password=
    ## 最大重试次数，默认：5 次，如果小于 0，则为 0
    wx.open.config-storage.max-retry-times=5
    ## 重试时间间隔步进，默认：1000 毫秒，如果小于 0，则为 1000
    wx.open.config-storage.retry-sleep-millis=1000
    ## 连接超时时间，单位毫秒，默认：5000
    wx.open.config-storage.connection-timeout=5000
    ## 读数据超时时间，即socketTimeout，单位毫秒，默认：5000
    wx.open.config-storage.so-timeout=5000
    ## 从连接池获取链接的超时时间，单位毫秒，默认：5000
    wx.open.config-storage.connection-request-timeout=5000
    ```
3. 自动注入的类型：`WxOpenMultiServices`

4. 使用样例

```java
import com.binarywang.spring.starter.wxjava.open.service.WxOpenMultiServices;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
  @Autowired
  private WxOpenMultiServices wxOpenMultiServices;

  public void test() {
    // 应用 1 的 WxOpenService
    WxOpenService wxOpenService1 = wxOpenMultiServices.getWxOpenService("tenantId1");
    WxOpenComponentService componentService1 = wxOpenService1.getWxOpenComponentService();
    // todo ...

    // 应用 2 的 WxOpenService
    WxOpenService wxOpenService2 = wxOpenMultiServices.getWxOpenService("tenantId2");
    WxOpenComponentService componentService2 = wxOpenService2.getWxOpenComponentService();
    // todo ...

    // 应用 3 的 WxOpenService
    WxOpenService wxOpenService3 = wxOpenMultiServices.getWxOpenService("tenantId3");
    // 判断是否为空
    if (wxOpenService3 == null) {
      // todo wxOpenService3 为空，请先配置 tenantId3 微信开放平台应用参数
      return;
    }
    WxOpenComponentService componentService3 = wxOpenService3.getWxOpenComponentService();
    // todo ...
  }
}
```
