# WeChat Mini Program Customer Service Management

This document describes the new customer service management functionality added to the WxJava Mini Program SDK.

## Overview

Previously, the mini program module only had:
- `WxMaCustomserviceWorkService` - For binding mini programs to enterprise WeChat customer service
- `WxMaMsgService.sendKefuMsg()` - For sending customer service messages

The new `WxMaKefuService` adds comprehensive customer service management capabilities:

## Features

### Customer Service Account Management
- `kfList()` - Get list of customer service accounts
- `kfAccountAdd()` - Add new customer service account  
- `kfAccountUpdate()` - Update customer service account
- `kfAccountDel()` - Delete customer service account

### Session Management
- `kfSessionCreate()` - Create customer service session
- `kfSessionClose()` - Close customer service session
- `kfSessionGet()` - Get customer session status
- `kfSessionList()` - Get customer service session list

## Usage Example

```java
// Get the customer service management service
WxMaKefuService kefuService = wxMaService.getKefuService();

// Add a new customer service account
WxMaKfAccountRequest request = WxMaKfAccountRequest.builder()
    .kfAccount("service001@example")
    .kfNick("Customer Service 001")
    .kfPwd("password123")
    .build();
boolean result = kefuService.kfAccountAdd(request);

// Create a session between user and customer service
boolean sessionResult = kefuService.kfSessionCreate("user_openid", "service001@example");

// Get customer service list
WxMaKfList kfList = kefuService.kfList();
```

## Bean Classes

### Request Objects
- `WxMaKfAccountRequest` - For customer service account operations
- `WxMaKfSessionRequest` - For session operations

### Response Objects  
- `WxMaKfInfo` - Customer service account information
- `WxMaKfList` - List of customer service accounts
- `WxMaKfSession` - Session information
- `WxMaKfSessionList` - List of sessions

## API Endpoints

The service uses the following WeChat Mini Program API endpoints:
- `https://api.weixin.qq.com/cgi-bin/customservice/getkflist` - Get customer service list
- `https://api.weixin.qq.com/customservice/kfaccount/add` - Add customer service account
- `https://api.weixin.qq.com/customservice/kfaccount/update` - Update customer service account
- `https://api.weixin.qq.com/customservice/kfaccount/del` - Delete customer service account
- `https://api.weixin.qq.com/customservice/kfsession/create` - Create session
- `https://api.weixin.qq.com/customservice/kfsession/close` - Close session
- `https://api.weixin.qq.com/customservice/kfsession/getsession` - Get session
- `https://api.weixin.qq.com/customservice/kfsession/getsessionlist` - Get session list

## Integration

The service is automatically available through the main `WxMaService` interface:

```java
WxMaKefuService kefuService = wxMaService.getKefuService();
```

This fills the gap mentioned in the original issue and provides full customer service management capabilities for WeChat Mini Programs.