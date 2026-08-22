# 视频号小店商家客服 API Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 为 `weixin-java-channel` 增加商家客服媒体上传和消息发送 API。

**Architecture:** 以独立的客服子服务封装两条官方 API，通过既有 `BaseWxChannelServiceImpl` 完成鉴权、JSON 请求与 multipart 上传。请求模型保持强类型，服务层测试使用可记录调用的测试替身，避免真实网络依赖。

**Tech Stack:** Java 8、Maven、TestNG、Lombok、Jackson 注解。

## Global Constraints

- Java 8 兼容，不新增依赖。
- API 路径固定为 `/channels/ec/commkf/cosupload` 和 `/channels/ec/commkf/sendmsg`。
- 使用 TestNG，所有新增测试不使用真实微信凭据。

---

### Task 1: 请求与响应模型

**Files:**
- Create: `weixin-java-channel/src/main/java/me/chanjar/weixin/channel/bean/kf/WxChannelKfCosUploadResponse.java`
- Create: `weixin-java-channel/src/main/java/me/chanjar/weixin/channel/bean/kf/WxChannelKfSendMsgParam.java`
- Create: `weixin-java-channel/src/main/java/me/chanjar/weixin/channel/bean/kf/WxChannelKfSendMsgResponse.java`
- Test: `weixin-java-channel/src/test/java/me/chanjar/weixin/channel/bean/kf/WxChannelKfBeanTest.java`

- [ ] Write JSON encode/decode tests for `request_id`, `open_id`, `msg_type`, `text.content`, `cos_url` and `msg_id`.
- [ ] Run the test and verify it fails because the classes do not exist.
- [ ] Add the minimal annotated model classes and nested message content types.
- [ ] Run the test and verify it passes.

### Task 2: 服务入口与请求执行

**Files:**
- Create: `weixin-java-channel/src/main/java/me/chanjar/weixin/channel/api/WxChannelKfService.java`
- Create: `weixin-java-channel/src/main/java/me/chanjar/weixin/channel/api/impl/WxChannelKfServiceImpl.java`
- Modify: `weixin-java-channel/src/main/java/me/chanjar/weixin/channel/api/WxChannelService.java`
- Modify: `weixin-java-channel/src/main/java/me/chanjar/weixin/channel/api/impl/BaseWxChannelServiceImpl.java`
- Modify: `weixin-java-channel/src/main/java/me/chanjar/weixin/channel/constant/WxChannelApiUrlConstants.java`
- Test: `weixin-java-channel/src/test/java/me/chanjar/weixin/channel/api/impl/WxChannelKfServiceImplTest.java`

- [ ] Write tests proving the service uses the documented URLs, uploads `file`, `open_id`, `msg_type`, decodes both responses, and caches `getKfService()`.
- [ ] Run the test and verify it fails because the API is absent.
- [ ] Add the minimal service API, implementation, constants and cached service entry point.
- [ ] Run the focused tests and module test suite, then inspect `git diff --check`.
