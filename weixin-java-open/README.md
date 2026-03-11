# 微信开放平台模块 (weixin-java-open)

## 模块说明

本模块主要用于**微信第三方平台**的开发，适用于以下场景：

### 适用场景
1. **第三方平台开发**：作为第三方平台，代替多个公众号或小程序进行管理和开发
2. **代公众号实现业务**：通过授权代替公众号进行消息管理、素材管理等操作
3. **代小程序实现业务**：通过授权代替小程序进行代码管理、基本信息设置等操作

### 移动应用开发说明

**如果您要开发移动应用（iOS/Android App）并接入微信功能，请注意：**

- **微信登录**：
  - 移动应用的微信登录（网页授权）需要在**微信开放平台**（open.weixin.qq.com）创建移动应用
  - 服务端处理 OAuth 授权时使用本模块 `weixin-java-open`
  - 移动端需集成微信官方SDK（iOS/Android），本项目仅提供服务端SDK

- **微信支付**：
  - 使用 `weixin-java-pay` 模块，参考 [微信支付文档](../weixin-java-pay/)
  - 移动应用支付使用 APP 支付类型（TradeType.APP）

- **微信分享**：
  - 需集成微信官方移动端SDK，本项目不涉及客户端功能

**参考资料**：
- [微信开放平台官方文档](https://open.weixin.qq.com/)
- [移动应用接入指南](https://developers.weixin.qq.com/doc/oplatform/Mobile_App/Access_Guide/iOS.html)

---

## 重要提示：小程序审核额度限制

**在使用第三方平台代小程序提交审核时，请注意以下限制：**

### 审核额度说明

- **默认额度**: 每个第三方平台账号每月默认有 **20 个** 审核额度
- **消耗规则**: 每次调用 `submitAudit()` 提交一个小程序审核，会消耗 **1 个** 审核额度
- **重置周期**: 额度每月初自动重置
- **额度查询**: 使用 `queryQuota()` 方法查询剩余额度

### 最佳实践

```java
// 1. 先查询剩余额度
WxOpenMaQueryQuotaResult quota = wxOpenMaService.queryQuota();
if (quota.getRest() <= 0) {
  throw new RuntimeException("审核额度不足，剩余：" + quota.getRest());
}

// 2. 提交审核
WxOpenMaSubmitAuditMessage message = new WxOpenMaSubmitAuditMessage();
message.setItemList(itemList);
WxOpenMaSubmitAuditResult result = wxOpenMaService.submitAudit(message);
```

**详细说明**: 请参考 [AUDIT_QUOTA_MANAGEMENT_GUIDE.md](AUDIT_QUOTA_MANAGEMENT_GUIDE.md)

---

## 代码示例

消息机制未实现，下面为通知回调中设置的代码部分

以下代码可通过腾讯全网发布测试用例

```Java
@RestController
@RequestMapping("notify")
public class NotifyController extends WechatThridBaseController {
    @Autowired
    protected WxOpenServiceDemo wxOpenService;
    @RequestMapping("receive_ticket")
    public Object receiveTicket(@RequestBody(required = false) String requestBody, @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce, @RequestParam("signature") String signature,
                                @RequestParam(name = "encrypt_type", required = false) String encType,
                                @RequestParam(name = "msg_signature", required = false) String msgSignature) {
        this.logger.info(
                "\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                signature, encType, msgSignature, timestamp, nonce, requestBody);

        if (!StringUtils.equalsIgnoreCase("aes", encType) || !wxOpenService.getWxOpenComponentService().checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        // aes加密的消息
        WxOpenXmlMessage inMessage = WxOpenXmlMessage.fromEncryptedXml(requestBody, wxOpenService.getWxOpenConfigStorage(), timestamp, nonce, msgSignature);
        this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
        String out = null;
        try {
            out = wxOpenService.getWxOpenComponentService().route(inMessage);
        } catch (WxErrorException e) {
            throw new ResponseException(ErrorCodeEnum.ERROR, e);
        }

        this.logger.debug("\n组装回复信息：{}", out);

        return out;
    }
    @RequestMapping("{appId}/callback")
    public Object callback(@RequestBody(required = false)String requestBody,
                           @PathVariable ("appId") String appId,
                           @RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("openid") String openid,
                           @RequestParam("encrypt_type") String encType,
                           @RequestParam("msg_signature") String msgSignature) {
        this.logger.info(
                "\n接收微信请求：[appId=[{}], openid=[{}], signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                appId, openid, signature, encType, msgSignature, timestamp, nonce, requestBody);
        logger.info("query:"+getHttpServletRequest().getQueryString()+"\nbody:"+requestBody);
        if (!StringUtils.equalsIgnoreCase("aes", encType) || !wxOpenService.getWxOpenComponentService().checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        String out = "";
        // aes加密的消息
        WxMpXmlMessage inMessage = WxOpenXmlMessage.fromEncryptedMpXml(requestBody, wxOpenService.getWxOpenConfigStorage(), timestamp, nonce, msgSignature);
        this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
        // 全网发布测试用例
        if (StringUtils.equalsAnyIgnoreCase(appId, "wxd101a85aa106f53e", "wx570bc396a51b8ff8")) {
            try {
                if (StringUtils.equals(inMessage.getMsgType(), "text")) {
                    if (StringUtils.equals(inMessage.getContent(), "TESTCOMPONENT_MSG_TYPE_TEXT")) {
                        out = new WxOpenCryptUtil(wxOpenService.getWxOpenConfigStorage()).encrypt(
                                WxMpXmlOutMessage.TEXT().content("TESTCOMPONENT_MSG_TYPE_TEXT_callback")
                                        .fromUser(inMessage.getToUser())
                                        .toUser(inMessage.getFromUser())
                                        .build()
                                        .toXml()
                        );
                    } else if (StringUtils.startsWith(inMessage.getContent(), "QUERY_AUTH_CODE:")) {
                        String msg = inMessage.getContent().replace("QUERY_AUTH_CODE:", "") + "_from_api";
                        WxMpKefuMessage kefuMessage = WxMpKefuMessage.TEXT().content(msg).toUser(inMessage.getFromUser()).build();
                        wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId).getKefuService().sendKefuMessage(kefuMessage);
                    }
                } else if (StringUtils.equals(inMessage.getMsgType(), "event")) {
                    WxMpKefuMessage kefuMessage = WxMpKefuMessage.TEXT().content(inMessage.getEvent() + "from_callback").toUser(inMessage.getFromUser()).build();
                    wxOpenService.getWxOpenComponentService().getWxMpServiceByAppid(appId).getKefuService().sendKefuMessage(kefuMessage);
                }
            } catch (WxErrorException e) {
                logger.error("callback", e);
            }
        }
        return out;
    }
}
```
