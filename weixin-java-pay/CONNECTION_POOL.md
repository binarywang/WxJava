# HTTP连接池功能说明

## 概述

`WxPayServiceApacheHttpImpl` 现在支持HTTP连接池功能，可以显著提高高并发场景下的性能表现。

## 主要改进

1. **连接复用**: 不再为每个请求创建新的HttpClient实例，而是复用连接池中的连接
2. **性能提升**: 减少连接建立和销毁的开销，提高吞吐量
3. **资源优化**: 合理控制并发连接数，避免资源浪费
4. **SSL支持**: 同时支持普通HTTP和SSL连接的连接池

## 配置说明

### 默认配置
```java
WxPayConfig config = new WxPayConfig();
// 默认配置：
// maxConnTotal = 20        (最大连接数)
// maxConnPerRoute = 10     (每个路由最大连接数)
```

### 自定义配置
```java
WxPayConfig config = new WxPayConfig();
config.setMaxConnTotal(50);      // 设置最大连接数
config.setMaxConnPerRoute(20);   // 设置每个路由最大连接数
```

## 使用方式

连接池功能是自动启用的，无需额外配置：

```java
// 1. 配置微信支付
WxPayConfig config = new WxPayConfig();
config.setAppId("your-app-id");
config.setMchId("your-mch-id");
config.setMchKey("your-mch-key");

// 2. 创建支付服务（连接池自动启用）
WxPayServiceApacheHttpImpl payService = new WxPayServiceApacheHttpImpl();
payService.setConfig(config);

// 3. 正常使用，所有HTTP请求都会使用连接池
WxPayUnifiedOrderResult result = payService.unifiedOrder(request);
```

## 向后兼容性

- 此功能完全向后兼容，现有代码无需修改
- 如果不设置连接池参数，将使用默认配置
- 支持原有的HttpClientBuilderCustomizer自定义功能

## 注意事项

1. 连接池中的HttpClient实例会被复用，不要手动关闭
2. SSL连接和普通连接使用不同的连接池
3. 连接池参数建议根据实际并发量调整
4. 代理配置仍然正常工作

## 性能建议

- 对于高并发应用，建议适当增加`maxConnTotal`和`maxConnPerRoute`
- 监控连接池使用情况，避免连接数不足导致的阻塞
- 在容器环境中，注意连接池配置与容器资源限制的平衡