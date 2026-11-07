# 服务商电子发票接口体系对比

## 结论

Gitee Issue `IDMETW` 补充的官方链接
[获取开通服务商电子发票能力邀请链接](https://pay.weixin.qq.com/doc/v3/partner/4015941495)
与 GitHub Issue [#4066](https://github.com/binarywang/WxJava/issues/4066) 给出的
[开发接入准备](https://pay.weixin.qq.com/doc/v3/partner/4015792554) 是**同一套**微信支付
V3 服务商电子发票产品文档：前者是 API 列表中的具体接口，后者是该产品的接入总览。

但是，当前 `weixin-java-mp` 的 `WxMpMerchantInvoiceService` 是另一套旧的公众号
`/card/invoice/*` 接口；不能把它当作这两个 Issue 所要求的支付 V3 能力实现。

## 当前仓库既有的公众号体系

- 当前代码将该能力归入公众号模块：
  `weixin-java-mp` 的
  `WxMpMerchantInvoiceService`。
- 该接口的 Javadoc 明确引用公众号官方文档：
  [商户开票模式说明](https://developers.weixin.qq.com/doc/offiaccount/WeChat_Invoice/E_Invoice/Vendor_and_Invoicing_Platform_Mode_Instruction.html)
  和
  [商户开票接口列表](https://developers.weixin.qq.com/doc/offiaccount/WeChat_Invoice/E_Invoice/Vendor_API_List.html)。
  后者当前重定向至
  [新版服务号文档](https://developers.weixin.qq.com/doc/service/guide/product/weixin_invoice/E_Invoice/Vendor_API_List.html)。
- 代码实际请求公众号 API：`/card/invoice/getauthurl`、
  `/card/invoice/getauthdata`、`/card/invoice/makeoutinvoice`、
  `/card/invoice/clearoutinvoice`、`/card/invoice/queryinvoceinfo` 等；见
  `WxMpApiUrl.Invoice`。这套接口围绕授权页、获取用户授权数据、开票、冲红和
  查询发票信息。
- 历史提交 `058ce62a2b932633931e30762f44c561481dde5f` 将该服务及其请求/响应
  Bean 加入 `weixin-java-mp`；提交说明关联 #1305。

> 说明：调研时 Gitee 页面/API 的 TLS 连接失败，未能独立读取 IDMETW 的评论；
> 但用户随后提供的 `4015941495` 链接已明确该 Issue 实际指向支付 V3 服务商电子发票。

## 两个 Issue 共同指向的支付 V3 体系

- #4066 链接的[开发接入准备](https://pay.weixin.qq.com/doc/v3/partner/4015792554)
  属于
  “微信电子发票”产品，要求在**微信支付服务商号**申请“服务商电子发票”权限，
  并提到数电发票资源。
- IDMETW 链接的[获取开通服务商电子发票能力邀请链接](https://pay.weixin.qq.com/doc/v3/partner/4015941495)
  位于相同产品的“API 列表”下，路径为
  `GET /v3/new-tax-control-fapiao/fapiaomerchant/getspinviteurl`。
- 同一官方文档导航的 API 列表包含：邀请子商户开通、检查子商户开票状态、创建电子
  发票卡券模板、配置开发选项、抬头填写链接/信息、各行业开票、冲红、查询、下载/上传
  发票文件、插入用户卡包，以及多个异步通知。
- 例如“开具通用行业电子发票”接口为
  `POST /v3/new-tax-control-fapiao/fapiao-applications/issue-general`，请求域名为
  `https://api.mch.weixin.qq.com`，并要求微信支付 API 证书签名、服务商模式的
  `sub_mchid` 和唯一开票申请单号 `fapiao_apply_id`；官方文档：
  [开具通用行业电子发票](https://pay.weixin.qq.com/doc/v3/partner/4015792574)。
  该页还规定敏感字段使用微信支付公钥或平台证书加密。

## 直接差异

| 维度 | 既有公众号能力 | 两个 Issue 指向的微信支付能力 |
| --- | --- | --- |
| SDK 模块 | `weixin-java-mp` | 应位于 `weixin-java-pay` |
| 官方文档产品线 | `developers.weixin.qq.com` 的公众号电子发票 | `pay.weixin.qq.com/doc/v3/partner` 的微信支付合作伙伴电子发票 |
| API 形态 | `/card/invoice/*` | `/v3/new-tax-control-fapiao/*` |
| 身份/鉴权上下文 | 公众号 access token、用户授权页/授权数据 | 微信支付服务商号、子商户号、V3 签名与敏感字段加密 |
| 覆盖范围 | 授权、开票、冲红、查询及公众号商户配置 | 子商户邀约/状态、模板/开发配置、行业开票、文件和卡包、通知 |

因此，两个 Issue 都是同一个微信支付 V3 服务商电子发票接入需求，当前由
`weixin-java-pay` 的 `PartnerInvoiceService` 提供对应的 `new-tax-control-fapiao` API 实现。
