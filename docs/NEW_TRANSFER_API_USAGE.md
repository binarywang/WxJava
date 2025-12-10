# 微信支付新版商户转账API使用指南

## 概述

从2025年1月15日开始，微信支付推出了新版的商户转账API。新开通的商户号只能使用最新版本的商户转账接口。WxJava 已经完整支持新版转账API。

## API对比

### 传统转账API (仍然支持)
- **服务类**: `MerchantTransferService` 
- **API前缀**: `/v3/transfer/batches`
- **特点**: 支持批量转账，一次可以转账给多个用户

### 新版转账API (2025.1.15+)
- **服务类**: `TransferService`
- **API前缀**: `/v3/fund-app/mch-transfer/transfer-bills`
- **特点**: 单笔转账，支持更丰富的转账场景

## 使用新版转账API

### 1. 获取服务实例

```java
// 获取WxPayService实例
WxPayService wxPayService = new WxPayServiceImpl();
wxPayService.setConfig(config);

// 获取新版转账服务
TransferService transferService = wxPayService.getTransferService();
```

### 2. 发起转账

```java
// 构建转账请求
TransferBillsRequest request = TransferBillsRequest.newBuilder()
    .appid("your_appid")                           // 应用ID
    .outBillNo("T" + System.currentTimeMillis())   // 商户转账单号
    .transferSceneId("1005")                       // 转账场景ID（佣金报酬）
    .openid("user_openid")                         // 用户openid
    .userName("张三")                               // 收款用户姓名（可选，需要加密）
    .transferAmount(100)                           // 转账金额（分）
    .transferRemark("佣金报酬")                     // 转账备注
    .notifyUrl("https://your-domain.com/notify")   // 回调地址（可选）
    .userRecvPerception("Y")                       // 用户收款感知（可选）
    .build();

try {
    TransferBillsResult result = transferService.transferBills(request);
    System.out.println("转账成功，微信转账单号：" + result.getTransferBillNo());
    System.out.println("状态：" + result.getState());
} catch (WxPayException e) {
    System.err.println("转账失败：" + e.getMessage());
}
```

### 3. 查询转账结果

```java
// 通过商户单号查询
String outBillNo = "T1642567890123";
TransferBillsGetResult result = transferService.getBillsByOutBillNo(outBillNo);

// 通过微信转账单号查询
String transferBillNo = "1000000000000000000000000001";
TransferBillsGetResult result2 = transferService.getBillsByTransferBillNo(transferBillNo);

System.out.println("转账状态：" + result.getState());
System.out.println("转账金额：" + result.getTransferAmount());
```

### 4. 撤销转账

```java
// 撤销转账（仅在特定状态下可撤销）
String outBillNo = "T1642567890123";
TransferBillsCancelResult cancelResult = transferService.transformBillsCancel(outBillNo);
System.out.println("撤销结果：" + cancelResult.getState());
```

### 5. 处理回调通知

```java
// 在回调接口中处理通知
@PostMapping("/transfer/notify")
public String handleTransferNotify(HttpServletRequest request) throws Exception {
    String notifyData = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
    
    // 构建签名头
    SignatureHeader header = new SignatureHeader();
    header.setTimeStamp(request.getHeader("Wechatpay-Timestamp"));
    header.setNonce(request.getHeader("Wechatpay-Nonce"));
    header.setSignature(request.getHeader("Wechatpay-Signature"));
    header.setSerial(request.getHeader("Wechatpay-Serial"));
    
    try {
        TransferBillsNotifyResult notifyResult = transferService.parseTransferBillsNotifyResult(notifyData, header);
        
        // 处理业务逻辑
        String outBillNo = notifyResult.getOutBillNo();
        String state = notifyResult.getState();
        
        System.out.println("转账单号：" + outBillNo + "，状态：" + state);
        
        return "SUCCESS";
    } catch (WxPayException e) {
        System.err.println("验签失败：" + e.getMessage());
        return "FAIL";
    }
}
```

## 重要参数说明

### 转账场景ID (transfer_scene_id)
- **1005**: 佣金报酬（常用）
- 其他场景ID需要在商户平台申请

### 转账状态
- **PROCESSING**: 转账中
- **SUCCESS**: 转账成功
- **FAILED**: 转账失败
- **REFUNDED**: 已退款

### 用户收款感知 (user_recv_perception)
- **Y**: 用户会收到微信转账通知
- **N**: 用户不会收到微信转账通知

## 新旧API对比总结

| 特性 | 传统API (MerchantTransferService) | 新版API (TransferService) |
|------|----------------------------------|---------------------------|
| 发起方式 | 批量转账 | 单笔转账 |
| API路径 | `/v3/transfer/batches` | `/v3/fund-app/mch-transfer/transfer-bills` |
| 场景支持 | 基础转账场景 | 丰富的转账场景 |
| 回调通知 | 支持 | 支持 |
| 撤销功能 | 不支持 | 支持 |
| 适用商户 | 所有商户 | 新开通商户必须使用 |

## 注意事项

1. **新开通的商户号**: 必须使用新版API (`TransferService`)
2. **转账场景ID**: 需要在商户平台申请相应的转账场景
3. **用户姓名加密**: 如果传入用户姓名，会自动进行RSA加密
4. **回调验签**: 建议开启回调验签以确保安全性
5. **错误处理**: 妥善处理各种异常情况

通过以上指南，您可以轻松使用WxJava的新版商户转账API功能。