# 收付通旧 API 过渡兼容层 Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Restore the public e-commerce payment API removed by #4014 as deprecated adapters over the unified V3 API.

**Architecture:** Deprecated legacy models remain in `bean.ecommerce`; `EcommerceService` exposes overloads with those legacy types. Each overload maps the input to the unified request/enums, invokes the existing unified method, and maps the response back, so transport and signature logic remain singular.

**Tech Stack:** Java 8, Maven, JUnit 5, Gson, Lombok.

## Global Constraints

- Keep all new #4014 API signatures and behavior unchanged.
- Mark every restored legacy public class and service method `@Deprecated` with migration Javadoc.
- Do not recreate legacy HTTP, signing, or notification-verification implementations.
- Remove the compatibility layer only in 5.0.

---

### Task 1: Restore legacy model surface

**Files:**
- Create: `weixin-java-pay/src/main/java/com/github/binarywang/wxpay/bean/ecommerce/{TransactionsResult,CombineTransactionsRequest,CombineTransactionsResult,CombineTransactionsNotifyResult,PartnerTransactionsRequest,PartnerTransactionsResult,PartnerTransactionsNotifyResult,PartnerTransactionsQueryRequest,PartnerTransactionsCloseRequest,SignatureHeader}.java`
- Create: `weixin-java-pay/src/main/java/com/github/binarywang/wxpay/bean/ecommerce/enums/TradeTypeEnum.java`
- Test: `weixin-java-pay/src/test/java/com/github/binarywang/wxpay/service/LegacyEcommerceApiCompatibilityTest.java`

**Interfaces:**
- Produces legacy types with their pre-#4014 fully qualified names and accessors.

- [ ] **Step 1: Write a failing compilation test importing the old types.**
- [ ] **Step 2: Run `mvn -pl weixin-java-pay -Dtest=LegacyEcommerceApiCompatibilityTest test` and confirm compilation fails because the old types do not exist.**
- [ ] **Step 3: Restore the old model source and annotate each class `@Deprecated`.**
- [ ] **Step 4: Re-run the focused Maven test and confirm compilation succeeds.**

### Task 2: Add service-level adapters

**Files:**
- Modify: `weixin-java-pay/src/main/java/com/github/binarywang/wxpay/service/EcommerceService.java`
- Create: `weixin-java-pay/src/main/java/com/github/binarywang/wxpay/service/LegacyEcommerceApiAdapter.java`
- Test: `weixin-java-pay/src/test/java/com/github/binarywang/wxpay/service/LegacyEcommerceApiCompatibilityTest.java`

**Interfaces:**
- Consumes restored legacy models from Task 1 and current unified V3 APIs.
- Produces deprecated overloads for `combine`, `combineTransactions`, notification parsing, query/close, partner order creation, query/close and notification parsing.

- [ ] **Step 1: Write failing tests using legacy `EcommerceService` signatures and asserting delegation to the corresponding unified method.**
- [ ] **Step 2: Run the focused Maven test and confirm each test fails because no legacy overload exists.**
- [ ] **Step 3: Implement mapping helpers and `default` legacy overloads that delegate to current methods.**
- [ ] **Step 4: Re-run the focused Maven test and confirm the legacy paths pass.**

### Task 3: Regression verification and documentation

**Files:**
- Modify: `weixin-java-pay/src/test/java/com/github/binarywang/wxpay/service/LegacyEcommerceApiCompatibilityTest.java`
- Modify: `docs/superpowers/specs/2026-08-08-legacy-ecommerce-api-compatibility-design.md`

- [ ] **Step 1: Add tests proving current unified API calls still resolve to their current methods.**
- [ ] **Step 2: Run `mvn -pl weixin-java-pay test` and verify the module builds successfully.**
- [ ] **Step 3: Inspect `git diff --check` and `git diff` for accidental edits.**
- [ ] **Step 4: Commit the implementation and tests with a Chinese message.**
