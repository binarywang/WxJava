# 微信小店模块迁移指南

从本版本开始，微信小店能力由独立的 `weixin-java-store` 模块提供。原有
`weixin-java-channel` 小店 API 仍可继续使用，且行为不变；它们已标记为
`@Deprecated`，以便在后续版本中引导迁移。

## 依赖替换

将：

```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>weixin-java-channel</artifactId>
</dependency>
```

替换或并行增加为：

```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>weixin-java-store</artifactId>
</dependency>
```

新模块不依赖 `weixin-java-channel`。仅接入微信小店的店铺、商品、订单、售后、
物流和资金等经营能力时，只需依赖 `weixin-java-store`；只有同时需要视频号直播、
Finder 或橱窗等视频号能力时，才额外依赖 `weixin-java-channel`。两者可以同时引入，
适合按业务逐步迁移。

## 包名与入口替换

| 旧入口 | 新入口 |
| --- | --- |
| `me.chanjar.weixin.channel.api.WxChannelService` | `com.binarywang.wxjava.store.api.WxStoreService` |
| `WxChannelProductService` | `WxStoreProductService` |
| `WxChannelOrderService` | `WxStoreOrderService` |
| `WxChannelAfterSaleService` | `WxStoreAfterSaleService` |
| `WxChannelFundService` | `WxStoreFundService` |
| `WxChannelWarehouseService` | `WxStoreWarehouseService` |

所有新模型均位于 `com.binarywang.wxjava.store.bean.*`。旧模型不会改包或删除；
由于新旧模型是独立类型，业务层应在迁移边界显式完成类型转换。

## 框架集成

微信小店提供独立的 Spring Boot 与 Solon 集成模块，并统一使用 `wx.store`
作为配置前缀：

| 场景 | 模块 |
| --- | --- |
| Spring Boot 单账号 | `wx-java-store-spring-boot-starter` |
| Spring Boot 多账号 | `wx-java-store-multi-spring-boot-starter` |
| Solon 单账号 | `wx-java-store-solon-plugin` |
| Solon 多账号 | `wx-java-store-multi-solon-plugin` |

原有 Channel Starter/Plugin 及其 `wx.channel` 配置保持不变，可与新的
Store Starter/Plugin 并存。

## 不属于 store 的能力

视频号直播、Finder、联盟分销、留资组件与达人罗盘继续使用
`weixin-java-channel`，不会被弃用，也不会由 `WxStoreService` 暴露。
