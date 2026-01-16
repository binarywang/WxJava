# wx-java-pay-multi-spring-boot-starter

## 快速开始

本starter支持微信支付多公众号关联配置，适用于以下场景：
- 一个服务商需要为多个公众号提供支付服务
- 一个系统需要支持多个公众号的支付业务
- 需要根据不同的appId动态切换支付配置

## 使用说明

### 1. 引入依赖

在项目的 `pom.xml` 中添加以下依赖：

```xml
<dependency>
    <groupId>com.github.binarywang</groupId>
    <artifactId>wx-java-pay-multi-spring-boot-starter</artifactId>
    <version>${version}</version>
</dependency>
```

### 2. 添加配置

在 `application.yml` 或 `application.properties` 中配置多个公众号的支付信息。

#### 配置示例（application.yml）

##### V2版本配置
```yml
wx:
  pay:
    configs:
      # 配置1 - 可以使用appId作为key
      wx1234567890abcdef:
        appId: wx1234567890abcdef
        mchId: 1234567890
        mchKey: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        keyPath: classpath:cert/app1/apiclient_cert.p12
        notifyUrl: https://example.com/pay/notify
      # 配置2 - 也可以使用自定义标识作为key
      config2:
        appId: wx9876543210fedcba
        mchId: 9876543210
        mchKey: yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy
        keyPath: classpath:cert/app2/apiclient_cert.p12
        notifyUrl: https://example.com/pay/notify
```

##### V3版本配置
```yml
wx:
  pay:
    configs:
      # 公众号1配置
      wx1234567890abcdef:
        appId: wx1234567890abcdef
        mchId: 1234567890
        apiV3Key: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        certSerialNo: 62C6CEAA360BCxxxxxxxxxxxxxxx
        privateKeyPath: classpath:cert/app1/apiclient_key.pem
        privateCertPath: classpath:cert/app1/apiclient_cert.pem
        notifyUrl: https://example.com/pay/notify
      # 公众号2配置  
      wx9876543210fedcba:
        appId: wx9876543210fedcba
        mchId: 9876543210
        apiV3Key: yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy
        certSerialNo: 73D7DFBB471CDxxxxxxxxxxxxxxx
        privateKeyPath: classpath:cert/app2/apiclient_key.pem
        privateCertPath: classpath:cert/app2/apiclient_cert.pem
        notifyUrl: https://example.com/pay/notify
```

##### V3服务商版本配置
```yml
wx:
  pay:
    configs:
      # 服务商为公众号1提供服务
      config1:
        appId: wxe97b2x9c2b3d      # 服务商appId
        mchId: 16486610            # 服务商商户号
        subAppId: wx118cexxe3c07679  # 子商户公众号appId
        subMchId: 16496705         # 子商户号
        apiV3Key: Dc1DBwSc094jAKDGR5aqqb7PTHr
        privateKeyPath: classpath:cert/apiclient_key.pem
        privateCertPath: classpath:cert/apiclient_cert.pem
      # 服务商为公众号2提供服务
      config2:
        appId: wxe97b2x9c2b3d      # 服务商appId（可以相同）
        mchId: 16486610            # 服务商商户号（可以相同）
        subAppId: wx228dexxf4d18890  # 子商户公众号appId（不同）
        subMchId: 16496706         # 子商户号（不同）
        apiV3Key: Dc1DBwSc094jAKDGR5aqqb7PTHr
        privateKeyPath: classpath:cert/apiclient_key.pem
        privateCertPath: classpath:cert/apiclient_cert.pem
```

#### 配置示例（application.properties）

```properties
# 公众号1配置
wx.pay.configs.wx1234567890abcdef.app-id=wx1234567890abcdef
wx.pay.configs.wx1234567890abcdef.mch-id=1234567890
wx.pay.configs.wx1234567890abcdef.apiv3-key=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
wx.pay.configs.wx1234567890abcdef.cert-serial-no=62C6CEAA360BCxxxxxxxxxxxxxxx
wx.pay.configs.wx1234567890abcdef.private-key-path=classpath:cert/app1/apiclient_key.pem
wx.pay.configs.wx1234567890abcdef.private-cert-path=classpath:cert/app1/apiclient_cert.pem
wx.pay.configs.wx1234567890abcdef.notify-url=https://example.com/pay/notify

# 公众号2配置
wx.pay.configs.wx9876543210fedcba.app-id=wx9876543210fedcba
wx.pay.configs.wx9876543210fedcba.mch-id=9876543210
wx.pay.configs.wx9876543210fedcba.apiv3-key=yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy
wx.pay.configs.wx9876543210fedcba.cert-serial-no=73D7DFBB471CDxxxxxxxxxxxxxxx
wx.pay.configs.wx9876543210fedcba.private-key-path=classpath:cert/app2/apiclient_key.pem
wx.pay.configs.wx9876543210fedcba.private-cert-path=classpath:cert/app2/apiclient_cert.pem
wx.pay.configs.wx9876543210fedcba.notify-url=https://example.com/pay/notify
```

### 3. 使用示例

自动注入的类型：`WxPayMultiServices`

```java
import com.binarywang.spring.starter.wxjava.pay.service.WxPayMultiServices;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryV3Result;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayService {
  @Autowired
  private WxPayMultiServices wxPayMultiServices;

  /**
   * 为不同的公众号创建支付订单
   *
   * @param configKey 配置标识（即 wx.pay.configs.&lt;configKey&gt; 中的 key，可以是 appId 或自定义标识）
   */
  public void createOrder(String configKey, String openId, Integer totalFee, String body) throws Exception {
    // 根据配置标识获取对应的WxPayService
    WxPayService wxPayService = wxPayMultiServices.getWxPayService(configKey);

    if (wxPayService == null) {
      throw new IllegalArgumentException("未找到配置标识对应的微信支付配置: " + configKey);
    }
    
    // 使用WxPayService进行支付操作
    WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
    request.setOutTradeNo(generateOutTradeNo());
    request.setDescription(body);
    request.setAmount(new WxPayUnifiedOrderV3Request.Amount().setTotal(totalFee));
    request.setPayer(new WxPayUnifiedOrderV3Request.Payer().setOpenid(openId));
    request.setNotifyUrl(wxPayService.getConfig().getNotifyUrl());
    
    // V3统一下单
    WxPayUnifiedOrderV3Result.JsapiResult result = 
      wxPayService.createOrderV3(TradeTypeEnum.JSAPI, request);
    
    // 返回给前端用于调起支付
    // ...
  }

  /**
   * 服务商模式示例
   */
  public void serviceProviderExample(String configKey) throws Exception {
    // 使用配置标识获取WxPayService
    WxPayService wxPayService = wxPayMultiServices.getWxPayService(configKey);
    
    if (wxPayService == null) {
      throw new IllegalArgumentException("未找到配置: " + configKey);
    }
    
    // 获取子商户的配置信息
    String subAppId = wxPayService.getConfig().getSubAppId();
    String subMchId = wxPayService.getConfig().getSubMchId();
    
    // 进行支付操作
    // ...
  }
  
  /**
   * 查询订单示例
   *
   * @param configKey 配置标识（即 wx.pay.configs.&lt;configKey&gt; 中的 key）
   */
  public void queryOrder(String configKey, String outTradeNo) throws Exception {
    WxPayService wxPayService = wxPayMultiServices.getWxPayService(configKey);

    if (wxPayService == null) {
      throw new IllegalArgumentException("未找到配置标识对应的微信支付配置: " + configKey);
    }
    
    // 查询订单
    WxPayOrderQueryV3Result result = wxPayService.queryOrderV3(null, outTradeNo);
    // 处理查询结果
    // ...
  }

  private String generateOutTradeNo() {
    // 生成商户订单号
    return "ORDER_" + System.currentTimeMillis();
  }
}
```

### 4. 配置说明

#### 必填配置项

| 配置项 | 说明 | 示例 |
|--------|------|------|
| appId | 公众号或小程序的appId | wx1234567890abcdef |
| mchId | 商户号 | 1234567890 |

#### V2版本配置项

| 配置项 | 说明 | 是否必填 |
|--------|------|----------|
| mchKey | 商户密钥 | 是（V2） |
| keyPath | p12证书文件路径 | 部分接口需要 |

#### V3版本配置项

| 配置项 | 说明 | 是否必填 |
|--------|------|----------|
| apiV3Key | API V3密钥 | 是（V3） |
| certSerialNo | 证书序列号 | 是（V3） |
| privateKeyPath | apiclient_key.pem路径 | 是（V3） |
| privateCertPath | apiclient_cert.pem路径 | 是（V3） |

#### 服务商模式配置项

| 配置项 | 说明 | 是否必填 |
|--------|------|----------|
| subAppId | 子商户公众号appId | 服务商模式必填 |
| subMchId | 子商户号 | 服务商模式必填 |

#### 可选配置项

| 配置项 | 说明 | 默认值 |
|--------|------|--------|
| notifyUrl | 支付结果通知URL | 无 |
| refundNotifyUrl | 退款结果通知URL | 无 |
| serviceId | 微信支付分serviceId | 无 |
| payScoreNotifyUrl | 支付分回调地址 | 无 |
| payScorePermissionNotifyUrl | 支付分授权回调地址 | 无 |
| useSandboxEnv | 是否使用沙箱环境 | false |
| apiHostUrl | 自定义API主机地址 | https://api.mch.weixin.qq.com |
| strictlyNeedWechatPaySerial | 是否所有V3请求都添加序列号头 | false |
| fullPublicKeyModel | 是否完全使用公钥模式 | false |
| publicKeyId | 公钥ID | 无 |
| publicKeyPath | 公钥文件路径 | 无 |

## 常见问题

### 1. 如何选择配置的key？

配置的key（即 `wx.pay.configs.<configKey>` 中的 `<configKey>` 部分）可以自由选择：
- 可以使用appId作为key（如 `wx.pay.configs.wx1234567890abcdef`），这样调用 `getWxPayService("wx1234567890abcdef")` 时就像直接用 appId 获取服务
- 可以使用自定义标识（如 `wx.pay.configs.config1`），调用时使用 `getWxPayService("config1")`

**注意**：`getWxPayService(configKey)` 方法的参数是配置文件中定义的 key，而不是 appId。只有当你使用 appId 作为配置 key 时，才能直接传入 appId。

### 2. V2和V3配置可以混用吗？

可以。不同的配置可以使用不同的版本，例如：
```yml
wx:
  pay:
    configs:
      app1:  # V2配置
        appId: wx111
        mchId: 111
        mchKey: xxx
      app2:  # V3配置
        appId: wx222
        mchId: 222
        apiV3Key: yyy
        privateKeyPath: xxx
```

### 3. 证书文件如何放置？

证书文件可以放在以下位置：
- `src/main/resources` 目录下，使用 `classpath:` 前缀
- 服务器绝对路径，直接填写完整路径
- 建议为不同配置使用不同的目录组织证书

### 4. 服务商模式如何配置？

服务商模式需要同时配置服务商信息和子商户信息：
- `appId` 和 `mchId` 填写服务商的信息
- `subAppId` 和 `subMchId` 填写子商户的信息

## 注意事项

1. **配置安全**：生产环境中的密钥、证书等敏感信息，建议使用配置中心或环境变量管理
2. **证书管理**：不同公众号的证书文件要分开存放，避免混淆
3. **懒加载**：WxPayService 实例采用懒加载策略，只有在首次调用时才会创建
4. **线程安全**：WxPayMultiServices 的实现是线程安全的
5. **配置更新**：如需动态更新配置，可调用 `removeWxPayService(configKey)` 方法移除缓存的实例

## 更多信息

- [WxJava 项目首页](https://github.com/Wechat-Group/WxJava)
- [微信支付官方文档](https://pay.weixin.qq.com/wiki/doc/api/)
- [微信支付V3接口文档](https://pay.weixin.qq.com/wiki/doc/apiv3/index.shtml)
