# wx-java-cp-multi-solon-plugin

企业微信多账号配置

- 实现多 WxCpService 初始化。
- 未实现 WxCpTpService 初始化，需要的小伙伴可以参考多 WxCpService 配置的实现。
- 未实现 WxCpCgService 初始化，需要的小伙伴可以参考多 WxCpService 配置的实现。

## 关于 corp-secret 的说明

企业微信中不同功能模块对应不同的 `corp-secret`，每种 Secret 只对对应模块的接口具有调用权限：

| Secret 类型 | 获取位置 | 可调用的接口 | 是否需要 agent-id |
|---|---|---|---|
| 自建应用 Secret | 应用管理 → 自建应用 → 选择应用 → 查看 Secret | 该应用有权限的接口 | **必填** |
| 通讯录同步 Secret | 管理工具 → 通讯录同步 → 查看 Secret | 部门/成员增删改查等通讯录接口 | **不填** |
| 客户联系 Secret | 客户联系 → API → Secret | 客户联系相关接口 | 不填 |

> **常见问题**：
> - 使用自建应用 Secret + agent-id 可以获取部门列表，但**无法更新部门**（因为写接口需要通讯录同步权限）
> - 使用通讯录同步 Secret 可以同步部门，但**调用某些需要 agent-id 的应用接口会报错**

如需同时使用多种权限范围，可在 `wx.cp.corps` 下配置多个条目，每个条目使用对应权限的 Secret，通过不同的 `tenantId` 区分后使用。

> **注意**：
> 当前插件实现会校验同一 `corp-id` 下的 `agent-id` **必须唯一**，并且 **只能有一个条目不填写 `agent-id`**。
> 如果在同一 `corp-id` 下同时配置多个未填写 `agent-id` 的条目，会因 token/ticket 缓存 key 冲突而在启动时直接抛异常。
## 快速开始

1. 引入依赖
    ```xml
    <dependency>
        <groupId>com.github.binarywang</groupId>
        <artifactId>wx-java-cp-multi-solon-plugin</artifactId>
        <version>${version}</version>
    </dependency>
    ```
2. 添加配置(app.properties)
    ```properties
    # 自建应用 1 配置（使用自建应用 Secret，需填写 agent-id）
    wx.cp.corps.app1.corp-id = @corp-id
    wx.cp.corps.app1.corp-secret = @自建应用的Secret（在"应用管理-自建应用"中查看）
    wx.cp.corps.app1.agent-id = @自建应用的AgentId
    ## 选填
    wx.cp.corps.app1.token = @token
    wx.cp.corps.app1.aes-key = @aes-key
    wx.cp.corps.app1.msg-audit-priKey = @msg-audit-priKey
    wx.cp.corps.app1.msg-audit-lib-path = @msg-audit-lib-path

    # 通讯录同步配置（使用通讯录同步 Secret，不需要填写 agent-id）
    # 此配置用于部门、成员的增删改查等通讯录管理操作
    wx.cp.corps.contact.corp-id = @corp-id
    wx.cp.corps.contact.corp-secret = @通讯录同步的Secret（在"管理工具-通讯录同步"中查看）
    ## agent-id 不填，通讯录同步不需要 agentId

    # 公共配置
    ## ConfigStorage 配置（选填）
    wx.cp.config-storage.type=memory # 配置类型: memory(默认), jedis, redisson, redistemplate
    ## http 客户端配置（选填）
    ## # http客户端类型: http_client(默认), ok_http, jodd_http
    wx.cp.config-storage.http-client-type=http_client
    wx.cp.config-storage.http-proxy-host=
    wx.cp.config-storage.http-proxy-port=
    wx.cp.config-storage.http-proxy-username=
    wx.cp.config-storage.http-proxy-password=
    ## 最大重试次数，默认：5 次，如果小于 0，则为 0
    wx.cp.config-storage.max-retry-times=5
    ## 重试时间间隔步进，默认：1000 毫秒，如果小于 0，则为 1000
    wx.cp.config-storage.retry-sleep-millis=1000
    ```
3. 支持自动注入的类型: `WxCpMultiServices`

4. 使用样例

```java
import com.binarywang.solon.wxjava.cp_multi.service.WxCpMultiServices;
import me.chanjar.weixin.cp.api.WxCpDepartmentService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpUserService;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

@Component
public class DemoService {
  @Inject
  private WxCpMultiServices wxCpMultiServices;

  public void test() {
    // 使用自建应用的 WxCpService（对应 corp-secret 为自建应用 Secret）
    WxCpService appService = wxCpMultiServices.getWxCpService("app1");
    WxCpUserService userService = appService.getUserService();
    userService.getUserId("xxx");
    // todo ...

    // 使用通讯录同步的 WxCpService（对应 corp-secret 为通讯录同步 Secret）
    // 通讯录同步 Secret 具有部门/成员增删改查等权限
    WxCpService contactService = wxCpMultiServices.getWxCpService("contact");
    WxCpDepartmentService departmentService = contactService.getDepartmentService();
    // 更新部门示例（WxCpDepart 包含 id、name、parentId 等字段）
    WxCpDepart depart = new WxCpDepart();
    depart.setId(100L);
    depart.setName("新部门名称");
    departmentService.update(depart);
    // todo ...
  }
}
```
