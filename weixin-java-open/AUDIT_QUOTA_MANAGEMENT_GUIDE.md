# 微信开放平台小程序审核额度管理最佳实践

## 问题背景

在使用微信开放平台第三方服务为多个授权小程序提交审核时，需要注意以下重要限制：

### 审核额度限制

- **默认额度**: 每个第三方平台账号每月默认有 **20 个** 审核额度
- **消耗规则**: 每次调用 `submitAudit()` 提交一个小程序审核，会消耗 **1 个** 审核额度
- **重置周期**: 额度每月初自动重置
- **不可返还**: 审核撤回（undoCodeAudit）不会返还已消耗的额度
- **增加额度**: 如需更多额度，需要联系微信开放平台客服申请

### 常见问题

**问题**: 开放平台是每个 appId 都要这样提交审核吗？额度不够吧？

**回答**: 是的，每个授权的小程序（appId）都需要单独调用 `submitAudit()` 提交审核，每次提交会消耗 1 个审核额度。默认的 20 个额度对于管理大量小程序的第三方平台来说可能不够用，建议：
1. 提交审核前先查询剩余额度
2. 合理规划审核计划，避免重复提交审核
3. 联系微信开放平台申请增加额度

## API 使用说明

### 1. 查询审核额度

```java
// 查询当前审核额度
WxOpenMaQueryQuotaResult quota = wxOpenMaService.queryQuota();

System.out.println("当月剩余提交审核次数: " + quota.getRest());         // 剩余额度
System.out.println("当月提交审核额度上限: " + quota.getLimit());        // 总额度
System.out.println("剩余加急次数: " + quota.getSpeedupRest());      // 剩余加急次数
System.out.println("加急额度上限: " + quota.getSpeedupLimit());     // 加急额度上限
```

**返回字段说明**:
- `rest`: 当月剩余提交审核次数
- `limit`: 当月提交审核额度上限（默认 20）
- `speedupRest`: 剩余加急次数
- `speedupLimit`: 加急额度上限

### 2. 提交审核

```java
// 构建审核项
WxMaCodeSubmitAuditItem item = new WxMaCodeSubmitAuditItem();
item.setAddress("index");              // 页面路径
item.setTag("工具");                   // 标签
item.setFirstClass("工具");            // 一级类目
item.setSecondClass("效率");           // 二级类目
item.setTitle("首页");                 // 页面标题

// 构建提交审核消息
WxOpenMaSubmitAuditMessage message = new WxOpenMaSubmitAuditMessage();
message.setItemList(Collections.singletonList(item));
message.setVersionDesc("版本描述");

// 提交审核
WxOpenMaSubmitAuditResult result = wxOpenMaService.submitAudit(message);
System.out.println("审核ID: " + result.getAuditId());
```

## 最佳实践

### 方案一：单个小程序提交前检查额度

这是最基本的做法，适用于偶尔提交审核的场景。

```java
import me.chanjar.weixin.open.api.WxOpenMaService;
import me.chanjar.weixin.open.bean.message.WxOpenMaSubmitAuditMessage;
import me.chanjar.weixin.open.bean.result.WxOpenMaQueryQuotaResult;
import me.chanjar.weixin.open.bean.result.WxOpenMaSubmitAuditResult;
import me.chanjar.weixin.common.error.WxErrorException;

public class AuditSubmitter {
  
  /**
   * 提交审核前检查额度
   */
  public WxOpenMaSubmitAuditResult submitWithQuotaCheck(
      WxOpenMaService wxOpenMaService,
      WxOpenMaSubmitAuditMessage message) throws WxErrorException {
    
    // 1. 检查审核额度
    WxOpenMaQueryQuotaResult quota = wxOpenMaService.queryQuota();
    System.out.println("当前剩余审核额度: " + quota.getRest());
    
    if (quota.getRest() <= 0) {
      throw new RuntimeException("审核额度不足，无法提交审核。剩余额度: " + quota.getRest());
    }
    
    // 2. 提交审核
    WxOpenMaSubmitAuditResult result = wxOpenMaService.submitAudit(message);
    System.out.println("提交审核成功，审核ID: " + result.getAuditId());
    
    // 3. 再次查询额度（可选）
    quota = wxOpenMaService.queryQuota();
    System.out.println("提交后剩余审核额度: " + quota.getRest());
    
    return result;
  }
}
```

### 方案二：批量提交审核的额度管理

适用于需要同时为多个小程序提交审核的场景。

```java
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenMaService;
import me.chanjar.weixin.open.bean.message.WxOpenMaSubmitAuditMessage;
import me.chanjar.weixin.open.bean.result.WxOpenMaQueryQuotaResult;
import me.chanjar.weixin.open.bean.result.WxOpenMaSubmitAuditResult;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BatchAuditSubmitter {
  
  /**
   * 批量提交审核结果
   */
  public static class BatchSubmitResult {
    private int successCount;      // 成功数量
    private int failCount;         // 失败数量
    private int skipCount;         // 跳过数量（额度不足）
    private List<String> failedAppIds;  // 失败的 appId
    
    // getters and setters...
  }
  
  /**
   * 批量提交审核，带额度检查
   */
  public BatchSubmitResult batchSubmitWithQuotaCheck(
      WxOpenComponentService wxOpenComponentService,
      Map<String, WxOpenMaSubmitAuditMessage> appIdToMessageMap) {
    
    BatchSubmitResult result = new BatchSubmitResult();
    result.setFailedAppIds(new ArrayList<>());
    
    // 基本参数校验：避免空指针和空集合导致的 NoSuchElementException
    if (appIdToMessageMap == null || appIdToMessageMap.isEmpty()) {
      System.err.println("错误：待提交的小程序列表为空，未执行任何审核提交");
      return result;
    }
    
    try {
      // 1. 检查总体额度是否充足
      // 使用任意一个已授权的小程序 appId 获取 WxMaService 来查询审核额度。
      // 注意：审核额度是以第三方平台维度统计的，因此这里选择任意一个 appId 即可。
      WxOpenMaQueryQuotaResult quota = wxOpenComponentService
          .getWxMaServiceByAppid(appIdToMessageMap.keySet().iterator().next())
          .queryQuota();
      
      System.out.println("=== 批量提交审核开始 ===");
      System.out.println("待提交数量: " + appIdToMessageMap.size());
      System.out.println("当前剩余审核额度: " + quota.getRest());
      
      if (quota.getRest() < appIdToMessageMap.size()) {
        System.err.println("警告：审核额度不足！");
        System.err.println("  需要提交: " + appIdToMessageMap.size() + " 个");
        System.err.println("  剩余额度: " + quota.getRest());
        System.err.println("  缺少额度: " + (appIdToMessageMap.size() - quota.getRest()));
        System.err.println("将仅提交前 " + quota.getRest() + " 个小程序");
      }
      
      // 2. 依次提交审核
      int count = 0;
      for (Map.Entry<String, WxOpenMaSubmitAuditMessage> entry : appIdToMessageMap.entrySet()) {
        String appId = entry.getKey();
        WxOpenMaSubmitAuditMessage message = entry.getValue();
        
        // 检查是否还有额度
        if (count >= quota.getRest()) {
          System.out.println("AppId: " + appId + " 跳过（额度不足）");
          result.setSkipCount(result.getSkipCount() + 1);
          continue;
        }
        
        try {
          WxOpenMaService maService = wxOpenComponentService.getWxMaServiceByAppid(appId);
          WxOpenMaSubmitAuditResult submitResult = maService.submitAudit(message);
          
          System.out.println("AppId: " + appId + " 提交成功，审核ID: " + submitResult.getAuditId());
          result.setSuccessCount(result.getSuccessCount() + 1);
          count++;
          
        } catch (WxErrorException e) {
          System.err.println("AppId: " + appId + " 提交失败: " + e.getMessage());
          result.setFailCount(result.getFailCount() + 1);
          result.getFailedAppIds().add(appId);
          count++;
        }
      }
      
      // 3. 输出统计信息
      System.out.println("=== 批量提交审核完成 ===");
      System.out.println("成功: " + result.getSuccessCount());
      System.out.println("失败: " + result.getFailCount());
      System.out.println("跳过: " + result.getSkipCount());
      
      // 4. 查询剩余额度
      quota = wxOpenComponentService
          .getWxMaServiceByAppid(appIdToMessageMap.keySet().iterator().next())
          .queryQuota();
      System.out.println("剩余额度: " + quota.getRest());
      
    } catch (WxErrorException e) {
      System.err.println("批量提交审核失败: " + e.getMessage());
      e.printStackTrace();
    }
    
    return result;
  }
}
```

### 方案三：审核额度监控和告警

建议实现一个审核额度监控机制，及时发现额度不足的情况。

```java
import me.chanjar.weixin.open.api.WxOpenMaService;
import me.chanjar.weixin.open.bean.result.WxOpenMaQueryQuotaResult;
import me.chanjar.weixin.common.error.WxErrorException;

public class QuotaMonitor {
  
  /**
   * 检查审核额度并发出告警
   */
  public void checkAndAlert(WxOpenMaService wxOpenMaService) {
    try {
      WxOpenMaQueryQuotaResult quota = wxOpenMaService.queryQuota();
      
      int rest = quota.getRest();
      int limit = quota.getLimit();
      double percentage = (double) rest / limit * 100;
      
      System.out.println("审核额度状态：");
      System.out.println("  剩余: " + rest + " / " + limit);
      System.out.println("  使用率: " + String.format("%.1f", 100 - percentage) + "%");
      
      // 根据剩余额度发出不同级别的告警
      if (rest <= 0) {
        sendCriticalAlert("审核额度已用尽！无法提交新的审核。");
      } else if (rest <= 3) {
        sendWarningAlert("审核额度严重不足！剩余额度: " + rest);
      } else if (percentage < 30) {
        sendInfoAlert("审核额度偏低，剩余: " + rest + " (" + String.format("%.1f", percentage) + "%)");
      }
      
    } catch (WxErrorException e) {
      System.err.println("查询审核额度失败: " + e.getMessage());
    }
  }
  
  private void sendCriticalAlert(String message) {
    // 发送紧急告警（如：发送邮件、短信、钉钉消息等）
    System.err.println("[严重] " + message);
  }
  
  private void sendWarningAlert(String message) {
    // 发送警告（如：发送邮件、企业微信消息等）
    System.out.println("[警告] " + message);
  }
  
  private void sendInfoAlert(String message) {
    // 发送普通提示
    System.out.println("[提示] " + message);
  }
}
```

## 常见问题 FAQ

### Q1: 审核额度什么时候重置？
A: 审核额度在每月初自动重置为默认值（通常是 20 个）。

### Q2: 审核撤回会返还额度吗？
A: 不会。调用 `undoCodeAudit()` 撤回审核不会返还已消耗的额度。

### Q3: 如何增加审核额度？
A: 需要联系微信开放平台客服申请增加额度。具体联系方式请参考微信开放平台官方文档。

### Q4: 审核失败会消耗额度吗？
A: 会。只要调用了 `submitAudit()` 接口提交审核，无论审核是否通过，都会消耗 1 个额度。

### Q5: 加急审核会额外消耗额度吗？
A: 加急审核（`speedAudit()`）使用的是单独的加急额度（`speedupRest`），不会消耗普通审核额度。但加急审核的前提是已经提交了审核，所以提交审核时仍会消耗 1 个普通审核额度。

### Q6: 多个小程序共享审核额度吗？
A: 是的。同一个第三方平台账号下，所有授权的小程序共享审核额度。每提交一个小程序审核，都会消耗该第三方平台的 1 个审核额度。

### Q7: 如何避免审核额度不足？
A: 建议采取以下措施：
- 在批量提交审核前，先调用 `queryQuota()` 检查剩余额度
- 实现审核额度监控和告警机制
- 合理规划审核计划，避免不必要的重复提交审核
- 提高代码质量，减少审核不通过的情况
- 联系微信开放平台申请增加额度

### Q8: 能否查询历史审核额度使用情况？
A: 微信开放平台 API 目前只提供当前剩余额度查询，不提供历史使用记录。如需统计历史使用情况，需要自行记录每次调用 `submitAudit()` 的时间和次数。

## 相关文档

- [微信开放平台官方文档 - 查询额度](https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Mini_Programs/code/query_quota.html)
- [微信开放平台官方文档 - 提交审核](https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Mini_Programs/code/submit_audit.html)
- [微信开放平台官方文档 - 加急审核](https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/Mini_Programs/code/speedup_audit.html)

## 技术支持

如有问题，请提交 Issue 到 [WxJava GitHub 仓库](https://github.com/binarywang/WxJava/issues)。
