# wx-java-channel-multi-spring-boot-starter

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
    # 视频号配置
    ## 应用 1 配置(必填)
    wx.channel.apps.tenantId1.app-id=@appId
    wx.channel.apps.tenantId1.secret=@secret
    ## 选填
    wx.channel.apps.tenantId1.use-stable-access-token=false
    wx.channel.apps.tenantId1.token=
    wx.channel.apps.tenantId1.aes-key=
    ## 应用 2 配置(必填)
    wx.channel.apps.tenantId2.app-id=@appId
    wx.channel.apps.tenantId2.secret=@secret
    ## 选填
    wx.channel.apps.tenantId2.use-stable-access-token=false
    wx.channel.apps.tenantId2.token=
    wx.channel.apps.tenantId2.aes-key=
    
    # ConfigStorage 配置（选填）
    ## 配置类型: memory(默认), jedis, redisson, redis_template
    wx.channel.config-storage.type=memory
    ## 相关redis前缀配置: wx:channel:multi(默认)
    wx.channel.config-storage.key-prefix=wx:channel:multi
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
3. 自动注入的类型：`WxChannelMultiServices`

4. 使用样例

    ```java
    import com.binarywang.spring.starter.wxjava.channel.service.WxChannelMultiServices;
    import me.chanjar.weixin.channel.api.WxChannelService;
    import me.chanjar.weixin.channel.api.WxFinderLiveService;
    import me.chanjar.weixin.channel.bean.lead.component.response.FinderAttrResponse;
    import me.chanjar.weixin.common.error.WxErrorException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    
    @Service
    public class DemoService {
      @Autowired
      private WxChannelMultiServices wxChannelMultiServices;
    
      public void test() throws WxErrorException {
        // 应用 1 的 WxChannelService
        WxChannelService wxChannelService1 = wxChannelMultiServices.getWxChannelService("tenantId1");
        WxFinderLiveService finderLiveService = wxChannelService1.getFinderLiveService();
        FinderAttrResponse response1 = finderLiveService.getFinderAttrByAppid();
        // todo ...
    
        // 应用 2 的 WxChannelService
        WxChannelService wxChannelService2 = wxChannelMultiServices.getWxChannelService("tenantId2");
        WxFinderLiveService finderLiveService2 = wxChannelService2.getFinderLiveService();
        FinderAttrResponse response2 = finderLiveService2.getFinderAttrByAppid();
        // todo ...
    
        // 应用 3 的 WxChannelService
        WxChannelService wxChannelService3 = wxChannelMultiServices.getWxChannelService("tenantId3");
        // 判断是否为空
        if (wxChannelService3 == null) {
          // todo wxChannelService3 为空，请先配置 tenantId3 微信视频号应用参数
          return;
        }
        WxFinderLiveService finderLiveService3 = wxChannelService3.getFinderLiveService();
        FinderAttrResponse response3 = finderLiveService3.getFinderAttrByAppid();
        // todo ...
      }
    }
    ```
