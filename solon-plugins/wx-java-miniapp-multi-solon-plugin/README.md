# wx-java-miniapp-multi-solon-plugin

## 快速开始

1. 引入依赖
    ```xml
    <dependency>
        <groupId>com.github.binarywang</groupId>
        <artifactId>wx-java-miniapp-multi-solon-plugin</artifactId>
        <version>${version}</version>
    </dependency>
    ```
2. 添加配置(app.properties)
    ```properties
    # 公众号配置
    ## 应用 1 配置(必填)
    wx.ma.apps.tenantId1.app-id=appId
    wx.ma.apps.tenantId1.app-secret=@secret
    ## 选填
    wx.ma.apps.tenantId1.token=@token
    wx.ma.apps.tenantId1.aes-key=@aesKey
    wx.ma.apps.tenantId1.use-stable-access-token=@useStableAccessToken
    ## 应用 2 配置(必填)
    wx.ma.apps.tenantId2.app-id=@appId
    wx.ma.apps.tenantId2.app-secret =@secret
    ## 选填
    wx.ma.apps.tenantId2.token=@token
    wx.ma.apps.tenantId2.aes-key=@aesKey
    wx.ma.apps.tenantId2.use-stable-access-token=@useStableAccessToken
   
    # ConfigStorage 配置（选填）
    ## 配置类型: memory(默认), jedis, redisson
    wx.ma.config-storage.type=memory
    ## 相关redis前缀配置: wx:ma:multi(默认)
    wx.ma.config-storage.key-prefix=wx:ma:multi
    wx.ma.config-storage.redis.host=127.0.0.1
    wx.ma.config-storage.redis.port=6379
	  ## 单机和 sentinel 同时存在时，优先使用sentinel配置
	  # wx.ma.config-storage.redis.sentinel-ips=127.0.0.1:16379,127.0.0.1:26379
	  # wx.ma.config-storage.redis.sentinel-name=mymaster 
   
    # http 客户端配置（选填）
    ## # http客户端类型: http_client(默认), ok_http, jodd_http
    wx.ma.config-storage.http-client-type=http_client
    wx.ma.config-storage.http-proxy-host=
    wx.ma.config-storage.http-proxy-port=
    wx.ma.config-storage.http-proxy-username=
    wx.ma.config-storage.http-proxy-password=
    ## 最大重试次数，默认：5 次，如果小于 0，则为 0
    wx.ma.config-storage.max-retry-times=5
    ## 重试时间间隔步进，默认：1000 毫秒，如果小于 0，则为 1000
    wx.ma.config-storage.retry-sleep-millis=1000
    ```
3. 自动注入的类型：`WxMaMultiServices`

4. 使用样例

```java
import com.binarywang.solon.wxjava.miniapp.service.WxMaMultiServices;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

@Component
public class DemoService {
  @Inject
  private WxMaMultiServices wxMaMultiServices;

  public void test() {
    // 应用 1 的 WxMaService
    WxMaService wxMaService1 = wxMaMultiServices.getWxMaService("tenantId1");
    WxMaUserService userService1 = wxMaService1.getUserService();
    userService1.userInfo("xxx");
    // todo ...

    // 应用 2 的 WxMaService
    WxMaService wxMaService2 = wxMaMultiServices.getWxMaService("tenantId2");
    WxMaUserService userService2 = wxMaService2.getUserService();
    userService2.userInfo("xxx");
    // todo ...

    // 应用 3 的 WxMaService
    WxMaService wxMaService3 = wxMaMultiServices.getWxMaService("tenantId3");
    // 判断是否为空
    if (wxMaService3 == null) {
      // todo wxMaService3 为空，请先配置 tenantId3 微信公众号应用参数
      return;
    }
    WxMaUserService userService3 = wxMaService3.getUserService();
    userService3.userInfo("xxx");
    // todo ...
  }
}
```
