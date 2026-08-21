# 企业微信智能机器人 API 模式支持 Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 在不影响旧 access-token 接口的前提下，支持新版智能机器人 API 模式回调解密和 response_url 加密回复。

**Architecture:** 用独立密码工具承载机器人 API 模式的 Token、AESKey、机器人 ID，避免误用企业应用配置。服务接口复用该工具解析回调，并以原始临时 URL 执行 HTTP POST，文档只展示这条链路。

**Tech Stack:** Java 8、Gson、现有 `WxCryptUtil`、Apache HttpClient 5、JUnit 5。

## Global Constraints

- 不删除或改变现有 `WxCpIntelligentRobotService` 的 access-token 方法。
- 不在 `response_url` 上拼接 `access_token`。
- 所有生产行为先由失败的单测定义。

---

### Task 1: API 模式密码工具

**Files:**
- Create: `weixin-java-cp/src/main/java/me/chanjar/weixin/cp/util/crypto/WxCpIntelligentRobotCryptUtil.java`
- Test: `weixin-java-cp/src/test/java/me/chanjar/weixin/cp/util/crypto/WxCpIntelligentRobotCryptUtilTest.java`

**Interfaces:**
- Produces: `WxCpIntelligentRobotCryptUtil(String token, String encodingAesKey, String aiBotId)` with `decrypt`, `encrypt`, and `verifyUrl` methods.

- [ ] **Step 1: Write failing encryption round-trip tests**

```java
assertEquals(plainJson, cryptUtil.decrypt(signature, timestamp, nonce,
  cryptUtil.encrypt(plainJson, timestamp, nonce)));
```

- [ ] **Step 2: Run the test to verify it fails**

Run: `mvn -pl weixin-java-cp -Dtest=WxCpIntelligentRobotCryptUtilTest test`
Expected: compilation failure because the utility does not exist.

- [ ] **Step 3: Implement the minimal utility**

Extend `WxCryptUtil`, initialize `token`, decoded `aesKey`, and `appidOrCorpid` from robot settings; delegate its encrypt/decrypt mechanics and convert encrypted JSON to the API-mode envelope.

- [ ] **Step 4: Run the test to verify it passes**

Run: `mvn -pl weixin-java-cp -Dtest=WxCpIntelligentRobotCryptUtilTest test`
Expected: PASS.

### Task 2: Service parsing and response posting

**Files:**
- Modify: `weixin-java-cp/src/main/java/me/chanjar/weixin/cp/api/WxCpIntelligentRobotService.java`
- Modify: `weixin-java-cp/src/main/java/me/chanjar/weixin/cp/api/impl/WxCpIntelligentRobotServiceImpl.java`
- Test: `weixin-java-cp/src/test/java/me/chanjar/weixin/cp/api/impl/WxCpIntelligentRobotServiceImplTest.java`

**Interfaces:**
- Consumes: `WxCpIntelligentRobotCryptUtil` from Task 1.
- Produces: encrypted callback parser and `response_url` reply method.

- [ ] **Step 1: Write failing tests for encrypted callback parsing and reply request**

```java
assertEquals("text", service.parseEncryptedCallbackMessage(...).getMsgType());
assertEquals(plainJson, decryptPostedBody(localResponseUrl));
```

- [ ] **Step 2: Run the targeted tests to verify they fail**

Run: `mvn -pl weixin-java-cp -Dtest=WxCpIntelligentRobotServiceImplTest test`
Expected: compilation failure because new service methods do not exist.

- [ ] **Step 3: Implement minimal service methods**

Construct the dedicated crypt utility, parse its plaintext with `WxCpIntelligentRobotMessage.fromJson`, encrypt outgoing JSON, and invoke the raw URL through the existing HTTP client abstraction without token refresh.

- [ ] **Step 4: Run targeted tests to verify they pass**

Run: `mvn -pl weixin-java-cp -Dtest=WxCpIntelligentRobotServiceImplTest test`
Expected: PASS.

### Task 3: Correct public documentation

**Files:**
- Modify: `weixin-java-cp/INTELLIGENT_ROBOT.md`

**Interfaces:**
- Consumes: final API names from Tasks 1 and 2.

- [ ] **Step 1: Replace XML and access-token examples for API mode**

Document robot-console configuration, encrypted JSON callback parsing, and `response_url` replies. Mark the pre-existing create/chat/send methods as legacy access-token endpoints.

- [ ] **Step 2: Verify all documented symbols exist**

Run: `rg -n 'parseEncryptedCallbackMessage|replyMessage' weixin-java-cp/src/main/java`
Expected: both APIs are found.

### Task 4: Full verification and publication

**Files:**
- Modify: all files from Tasks 1–3.

- [ ] **Step 1: Run module test suite**

Run: `mvn -pl weixin-java-cp test`
Expected: PASS.

- [ ] **Step 2: Inspect scope and commit only intended files**

Run: `git status --short && git diff --check`
Expected: only API-mode implementation, tests, and docs are changed; no whitespace errors.

- [ ] **Step 3: Publish a draft PR**

Run: `git add -- <intended paths> && git commit -m 'feat: 支持企业微信智能机器人 API 模式' && git pull --rebase && git push`
Expected: branch is pushed and a draft PR targets `develop`.
