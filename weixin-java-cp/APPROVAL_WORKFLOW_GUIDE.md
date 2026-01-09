# WeChat Enterprise Workflow Approval Guide
# 企业微信流程审批功能使用指南

## Overview / 概述

WxJava SDK provides comprehensive support for WeChat Enterprise workflow approval (企业微信流程审批), including both traditional OA approval and the approval process engine.

WxJava SDK 提供全面的企业微信流程审批支持，包括传统OA审批和审批流程引擎。

## Current Implementation Status / 当前实现状态

### ✅ Fully Implemented APIs / 已完整实现的API

1. **Submit Approval Application / 提交审批申请**
   - Endpoint: `/cgi-bin/oa/applyevent`
   - Documentation: [91853](https://work.weixin.qq.com/api/doc/90000/90135/91853)
   - Implementation: `WxCpOaService.apply(WxCpOaApplyEventRequest)`

2. **Get Approval Details / 获取审批申请详情**
   - Endpoint: `/cgi-bin/oa/getapprovaldetail`
   - Implementation: `WxCpOaService.getApprovalDetail(String spNo)`

3. **Batch Get Approval Numbers / 批量获取审批单号**
   - Endpoint: `/cgi-bin/oa/getapprovalinfo`
   - Implementation: `WxCpOaService.getApprovalInfo(...)`

4. **Approval Process Engine / 审批流程引擎**
   - Endpoint: `/cgi-bin/corp/getopenapprovaldata`
   - Implementation: `WxCpOaAgentService.getOpenApprovalData(String thirdNo)`

5. **Template Management / 模板管理**
   - Create: `WxCpOaService.createOaApprovalTemplate(...)`
   - Update: `WxCpOaService.updateOaApprovalTemplate(...)`
   - Get Details: `WxCpOaService.getTemplateDetail(...)`

## Usage Examples / 使用示例

### 1. Submit Approval Application / 提交审批申请

```java
// Create approval request
WxCpOaApplyEventRequest request = new WxCpOaApplyEventRequest()
    .setCreatorUserId("userId")
    .setTemplateId("templateId")
    .setUseTemplateApprover(0)
    .setApprovers(Arrays.asList(
        new WxCpOaApplyEventRequest.Approver()
            .setAttr(2)
            .setUserIds(new String[]{"approver1", "approver2"})
    ))
    .setNotifiers(new String[]{"notifier1", "notifier2"})
    .setNotifyType(1)
    .setApplyData(new WxCpOaApplyEventRequest.ApplyData()
        .setContents(Arrays.asList(
            new ApplyDataContent()
                .setControl("Text")
                .setId("Text-1234567890")
                .setValue(new ContentValue().setText("Approval content"))
        ))
    );

// Submit approval
String spNo = wxCpService.getOaService().apply(request);
```

### 2. Get Approval Details / 获取审批详情

```java
// Get approval details by approval number
WxCpApprovalDetailResult result = wxCpService.getOaService()
    .getApprovalDetail("approval_number");

WxCpApprovalDetailResult.WxCpApprovalDetail detail = result.getInfo();
System.out.println("Approval Status: " + detail.getSpStatus());
System.out.println("Approval Name: " + detail.getSpName());
```

### 3. Batch Get Approval Information / 批量获取审批信息

```java
// Get approval info with filters
Date startTime = new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
Date endTime = new Date();

WxCpApprovalInfo approvalInfo = wxCpService.getOaService()
    .getApprovalInfo(startTime, endTime, "0", 100, null);

List<String> spNumbers = approvalInfo.getSpNoList();
```

### 4. Third-Party Application Support / 第三方应用支持

```java
// For third-party applications
WxCpTpOAService tpOaService = wxCpTpService.getOaService();

// Submit approval for specific corp
String spNo = tpOaService.apply(request, "corpId");

// Get approval details for specific corp
WxCpApprovalDetailResult detail = tpOaService.getApprovalDetail("spNo", "corpId");
```

## Multi-Account Configuration / 多账号配置支持

WxJava supports multi-account configurations for enterprise scenarios:

```java
// Spring Boot configuration example
@Autowired
private WxCpMultiServices wxCpMultiServices;

// Get service for specific corp
WxCpService wxCpService = wxCpMultiServices.getWxCpService("corpId");
WxCpOaService oaService = wxCpService.getOaService();
```

## Available Data Models / 可用数据模型

- `WxCpOaApplyEventRequest` - Approval application request
- `WxCpApprovalDetailResult` - Approval details response
- `WxCpApprovalInfo` - Batch approval information
- `WxCpXmlApprovalInfo` - XML approval message handling
- `WxCpOaApprovalTemplate` - Approval template management

## Documentation References / 文档参考

- [Submit Approval Application (91853)](https://work.weixin.qq.com/api/doc/90000/90135/91853)
- [Get Approval Details (91983)](https://work.weixin.qq.com/api/doc/90000/90135/91983)
- [Batch Get Approval Numbers (91816)](https://work.weixin.qq.com/api/doc/90000/90135/91816)
- [Approval Process Engine (90269)](https://developer.work.weixin.qq.com/document/path/90269)

## Conclusion / 结论

WxJava already provides comprehensive support for WeChat Enterprise workflow approval. The "new version" (新版) approval functionality referenced in issue requests is **already fully implemented** and available for use.

WxJava 已经提供了企业微信流程审批的全面支持。问题中提到的"新版"流程审批功能**已经完全实现**并可使用。

For questions about specific usage, please refer to the test cases in `WxCpOaServiceImplTest` and the comprehensive API documentation.

有关具体使用问题，请参考 `WxCpOaServiceImplTest` 中的测试用例和全面的API文档。