# 微信支付新版商户转账API支持

## 问题解答

**问题**: 新开通的商户号只能使用最新版本的商户转账接口，WxJava是否支持？

**答案**: **WxJava 已经完整支持新版商户转账API！** 从2025年1月15日开始生效的新版转账API已在WxJava中实现。

## 新版转账API特性

### 1. API接口对比

| 特性 | 传统转账API | 新版转账API (2025.1.15+) |
|------|-------------|-------------------------|
| **服务类** | `MerchantTransferService` | `TransferService` |
| **API路径** | `/v3/transfer/batches` | `/v3/fund-app/mch-transfer/transfer-bills` |
| **转账方式** | 批量转账 | 单笔转账 |
| **场景支持** | 基础场景 | 丰富场景（如佣金报酬等） |
| **撤销功能** | ❌ 不支持 | ✅ 支持 |
| **适用范围** | 所有商户 | **新开通商户必须使用** |

### 2. 新版API功能列表

✅ **发起转账** - `transferBills()`
✅ **查询转账** - `getBillsByOutBillNo()` / `getBillsByTransferBillNo()`  
✅ **撤销转账** - `transformBillsCancel()`
✅ **回调通知** - `parseTransferBillsNotifyResult()`
✅ **RSA加密** - 自动处理用户姓名加密
✅ **场景支持** - 支持多种转账场景ID

## 快速开始

### 1. 获取服务实例

```java
// 获取WxPayService实例
WxPayService wxPayService = new WxPayServiceImpl();
wxPayService.setConfig(config);

// 获取新版转账服务 - 这就是新开通商户需要使用的服务！
TransferService transferService = wxPayService.getTransferService();
```

### 2. 发起转账（新版API）

```java
// 构建转账请求
TransferBillsRequest request = TransferBillsRequest.newBuilder()
    .appid("your_appid")                    // 应用ID
    .outBillNo("T" + System.currentTimeMillis()) // 商户转账单号
    .transferSceneId("1005")                // 转账场景ID（佣金报酬）
    .openid("user_openid")                  // 用户openid
    .userName("张三")                        // 收款用户姓名（可选，自动加密）
    .transferAmount(100)                    // 转账金额（分）
    .transferRemark("佣金报酬")              // 转账备注
    .build();

// 发起转账
TransferBillsResult result = transferService.transferBills(request);
System.out.println("转账成功，微信转账单号：" + result.getTransferBillNo());
```

### 3. 查询转账结果

```java
// 通过商户单号查询
TransferBillsGetResult result = transferService.getBillsByOutBillNo("T1642567890123");

// 通过微信转账单号查询  
TransferBillsGetResult result2 = transferService.getBillsByTransferBillNo("wx_transfer_bill_no");

System.out.println("转账状态：" + result.getState());
```

### 4. 撤销转账（新功能）

```java
// 撤销转账
TransferBillsCancelResult cancelResult = transferService.transformBillsCancel("T1642567890123");
System.out.println("撤销状态：" + cancelResult.getState());
```

## 重要说明

### 转账场景ID (transfer_scene_id)
- **1005**: 佣金报酬（常用场景）
- 其他场景需要在微信商户平台申请

### 转账状态说明
- **ACCEPTED**: 转账已受理
- **PROCESSING**: 转账处理中
- **SUCCESS**: 转账成功
- **FAIL**: 转账失败
- **CANCELLED**: 转账撤销完成

### 新开通商户使用建议

1. **优先使用** `TransferService` （新版API）
2. **不要使用** `MerchantTransferService` （可能不支持）
3. **必须设置** 转账场景ID (`transfer_scene_id`)
4. **建议开启** 回调通知以实时获取转账结果

## 完整示例代码

详细的使用示例请参考：
- 📄 [NEW_TRANSFER_API_USAGE.md](./NEW_TRANSFER_API_USAGE.md) - 详细使用指南
- 💻 [NewTransferApiExample.java](./weixin-java-pay/src/main/java/com/github/binarywang/wxpay/example/NewTransferApiExample.java) - 完整代码示例

## 常见问题

**Q: 我是新开通的商户，应该使用哪个服务？**
A: 使用 `TransferService`，这是专为新版API设计的服务。

**Q: 新版API和旧版API有什么区别？**
A: 新版API使用单笔转账模式，支持更丰富的转账场景，并且支持撤销功能。

**Q: 如何设置转账场景ID？**
A: 在商户平台申请相应场景，常用的佣金报酬场景ID是"1005"。

**Q: 用户姓名需要加密吗？**
A: WxJava会自动处理RSA加密，您只需要传入明文姓名即可。

## 版本要求

- WxJava 版本：4.7.0+ 
- 支持时间：2025年1月15日+
- 适用商户：所有商户（新开通商户强制使用）

通过以上说明，新开通的微信支付商户可以放心使用WxJava进行商户转账操作！