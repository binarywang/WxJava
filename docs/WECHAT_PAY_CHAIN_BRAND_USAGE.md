# 微信支付连锁品牌

`weixin-java-pay` 提供服务商模式下的品牌入驻、品牌门店和连锁品牌分账。它们与品牌红包转账、普通商户分账分别使用独立服务和接口。

品牌服务需要支持 APIv3 的 ApacheHttp 或 HttpComponents 客户端；Jodd 客户端的品牌服务入口会明确抛出 `UnsupportedOperationException`。

## 服务入口

```java
BrandApplymentService applyments = wxPayService.getBrandApplymentService();
BrandStoreService stores = wxPayService.getBrandStoreService();
BrandProfitSharingService profitSharing = wxPayService.getBrandProfitSharingService();
```

三个服务位于 `com.github.binarywang.wxpay.service`，请求与响应模型分别位于 `bean.brandapplyment`、`bean.brandstore`、`bean.brandprofitsharing`。

| 服务 | 支持操作 |
| --- | --- |
| 品牌入驻 | 提交、撤销、按业务编号查询、按申请单编号查询 |
| 品牌门店 | 创建、查询、更新、删除、绑定与解绑收款商户号 |
| 品牌分账 | 请求、查询、回退、查询回退、完结、查询剩余金额、查询最大比例、添加与删除接收方 |

## 使用示例

查询品牌入驻申请：

```java
BrandApplymentQueryResult application = applyments.queryApplymentByBusinessCode("brand_application_001");
```

查询或删除品牌门店：

```java
BrandStoreResult store = stores.query("BRAND_ID", "STORE_ID");
stores.delete("BRAND_ID", "STORE_ID");
```

查询连锁品牌分账：

```java
BrandProfitSharingQueryRequest request = new BrandProfitSharingQueryRequest();
request.setSubMchId("SUB_MCH_ID");
request.setTransactionId("WECHAT_TRANSACTION_ID");
request.setOutOrderNo("profit-sharing-001");
BrandProfitSharingResult result = profitSharing.query(request);
```

入驻与门店管理使用 `brand_id`，连锁品牌分账相关接口按文档使用 `brand_mchid` 与 `sub_mchid`，不要混用这些标识。分账是异步处理，应通过查询或通知确认最终状态，不能将受理成功等同于资金划拨完成。

## 接入条件

配置已有 APIv3 商户私钥、APIv3 密钥及微信支付平台证书或公钥。品牌入驻管理员姓名和证件号等敏感字段由 SDK 加密，请传入明文，不要预先加密。

SDK 不代替业务授权和审核：品牌入驻需要管理员授权、主体确认及审核；门店收款号需要先建立品牌关系；品牌分账需要申请连锁品牌工具箱并配置比例，支付下单按官方要求启用分账。

离线测试验证接口协议、序列化与加密；真实接口联调需要业务方提供已开通权限的测试账号。

官方文档：[品牌入驻](https://pay.weixin.qq.com/doc/v3/partner/4016985537)、[品牌门店](https://pay.weixin.qq.com/doc/v3/partner/4016628135)、[连锁品牌分账](https://pay.weixin.qq.com/doc/v3/partner/4012072637)。
