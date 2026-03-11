# 微信支付实名验证接口使用说明

## 概述

微信支付实名验证接口允许商户查询用户的实名认证状态，如果用户未实名认证，接口会返回引导用户进行实名认证的URL。

## 官方文档

https://pay.wechatpay.cn/doc/v2/merchant/4011987607

## 接口说明

### 查询用户实名认证信息

- **接口地址**：`https://api.mch.weixin.qq.com/userinfo/realnameauth/query`
- **请求方式**：POST（需要使用商户证书）
- **请求参数**：
  - `appid`：公众账号ID（自动填充）
  - `mch_id`：商户号（自动填充）
  - `openid`：用户在商户appid下的唯一标识
  - `nonce_str`：随机字符串（自动生成）
  - `sign`：签名（自动生成）

- **返回参数**：
  - `return_code`：返回状态码
  - `return_msg`：返回信息
  - `result_code`：业务结果
  - `openid`：用户标识
  - `is_certified`：实名认证状态（Y-已实名认证，N-未实名认证）
  - `cert_info`：实名认证信息（加密，仅已实名时返回）
  - `guide_url`：引导用户进行实名认证的URL（仅未实名时返回）

## 使用示例

### 1. 获取实名验证服务

```java
// 获取WxPayService实例
WxPayService wxPayService = ... // 根据你的配置初始化

// 获取实名验证服务
RealNameService realNameService = wxPayService.getRealNameService();
```

### 2. 查询用户实名认证状态（完整方式）

```java
import com.github.binarywang.wxpay.bean.realname.RealNameRequest;
import com.github.binarywang.wxpay.bean.realname.RealNameResult;
import com.github.binarywang.wxpay.exception.WxPayException;

try {
    // 构建请求对象
    RealNameRequest request = RealNameRequest.newBuilder()
        .openid("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o") // 用户的openid
        .build();

    // 调用查询接口
    RealNameResult result = realNameService.queryRealName(request);

    // 处理返回结果
    if ("Y".equals(result.getIsCertified())) {
        System.out.println("用户已实名认证");
        System.out.println("认证信息：" + result.getCertInfo());
    } else {
        System.out.println("用户未实名认证");
        System.out.println("引导链接：" + result.getGuideUrl());
        // 可以将guide_url提供给用户，引导其完成实名认证
    }
} catch (WxPayException e) {
    System.err.println("查询失败：" + e.getMessage());
}
```

### 3. 查询用户实名认证状态（简化方式）

```java
import com.github.binarywang.wxpay.bean.realname.RealNameResult;
import com.github.binarywang.wxpay.exception.WxPayException;

try {
    // 直接传入openid进行查询
    RealNameResult result = realNameService.queryRealName("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");

    // 处理返回结果
    if ("Y".equals(result.getIsCertified())) {
        System.out.println("用户已实名认证");
    } else {
        System.out.println("用户未实名认证，引导链接：" + result.getGuideUrl());
    }
} catch (WxPayException e) {
    System.err.println("查询失败：" + e.getMessage());
}
```

## 注意事项

1. **证书要求**：本接口需要使用商户证书进行请求，请确保已正确配置商户证书。

2. **OPENID获取**：openid是用户在商户appid下的唯一标识，需要通过微信公众平台或小程序获取。

3. **认证信息**：`cert_info`字段返回的信息是加密的，需要使用相应的解密方法才能获取明文信息。

4. **引导链接**：当用户未实名时，返回的`guide_url`可以用于引导用户完成实名认证，建议在小程序或H5页面中使用。

5. **频率限制**：请注意接口调用频率限制，避免频繁查询同一用户的实名状态。

## 业务场景

- **转账前校验**：在进行企业付款到零钱等操作前，可以先查询用户的实名认证状态
- **风控审核**：作为业务风控的一部分，确认用户已完成实名认证
- **用户引导**：发现用户未实名时，引导用户完成实名认证以使用相关功能

## 错误处理

```java
try {
    RealNameResult result = realNameService.queryRealName(openid);
    // 处理结果...
} catch (WxPayException e) {
    // 处理异常
    String errorCode = e.getErrCode();
    String errorMsg = e.getErrCodeDes();
    
    // 根据错误码进行相应处理
    switch (errorCode) {
        case "SYSTEMERROR":
            // 系统错误，建议稍后重试
            break;
        case "PARAM_ERROR":
            // 参数错误，检查openid是否正确
            break;
        default:
            // 其他错误
            break;
    }
}
```

## 相关链接

- [微信支付官方文档](https://pay.wechatpay.cn/doc/v2/merchant/4011987607)
- [WxJava项目主页](https://github.com/binarywang/WxJava)
