# wx-java-store-multi-spring-boot-starter

## 快速开始

1. 引入依赖
    ```xml
    <dependencies>
        <dependency>
            <groupId>com.github.binarywang</groupId>
            <artifactId>wx-java-store-multi-spring-boot-starter</artifactId>
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
    # 视频号配置
    ## 应用 1 配置(必填)
    wx.store.apps.tenantId1.app-id=@appId
    wx.store.apps.tenantId1.secret=@secret
    ## 选填
    wx.store.apps.tenantId1.use-stable-access-token=false
    wx.store.apps.tenantId1.token=
    wx.store.apps.tenantId1.aes-key=
    ## 应用 2 配置(必填)
    wx.store.apps.tenantId2.app-id=@appId
    wx.store.apps.tenantId2.secret=@secret
    ## 选填
    wx.store.apps.tenantId2.use-stable-access-token=false
    wx.store.apps.tenantId2.token=
    wx.store.apps.tenantId2.aes-key=

    # ConfigStorage 配置（选填）
    ## 配置类型: memory(默认), jedis, redisson, redis_template
    wx.store.config-storage.type=memory
    ## 相关redis前缀配置: wx:store:multi(默认)
    wx.store.config-storage.key-prefix=wx:store:multi
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
3. 自动注入的类型：`WxStoreMultiServices`

4. 使用样例

    ```java
    import com.binarywang.spring.starter.wxjava.store.service.WxStoreMultiServices;
    import com.binarywang.wxjava.store.api.WxStoreService;
    import com.binarywang.wxjava.store.api.WxFinderLiveService;
    import com.binarywang.wxjava.store.bean.lead.component.response.FinderAttrResponse;
    import me.chanjar.weixin.common.error.WxErrorException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    @Service
    public class DemoService {
      @Autowired
      private WxStoreMultiServices wxStoreMultiServices;

      public void test() throws WxErrorException {
        // 应用 1 的 WxStoreService
        WxStoreService wxStoreService1 = wxStoreMultiServices.getWxStoreService("tenantId1");
        WxFinderLiveService finderLiveService = wxStoreService1.getFinderLiveService();
        FinderAttrResponse response1 = finderLiveService.getFinderAttrByAppid();
        // todo ...

        // 应用 2 的 WxStoreService
        WxStoreService wxStoreService2 = wxStoreMultiServices.getWxStoreService("tenantId2");
        WxFinderLiveService finderLiveService2 = wxStoreService2.getFinderLiveService();
        FinderAttrResponse response2 = finderLiveService2.getFinderAttrByAppid();
        // todo ...

        // 应用 3 的 WxStoreService
        WxStoreService wxStoreService3 = wxStoreMultiServices.getWxStoreService("tenantId3");
        // 判断是否为空
        if (wxStoreService3 == null) {
          // todo wxStoreService3 为空，请先配置 tenantId3 微信小店应用参数
          return;
        }
        WxFinderLiveService finderLiveService3 = wxStoreService3.getFinderLiveService();
        FinderAttrResponse response3 = finderLiveService3.getFinderAttrByAppid();
        // todo ...
      }
    }
    ```
