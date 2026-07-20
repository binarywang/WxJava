# WxJava 一手资料研究与 SKILL 增强建议

本文件为五个 WxJava SKILL 的维护者提供可复用的事实来源和细化方向。资料仅来自
`binarywang/WxJava` 的仓库、GitHub Issue 与 GitHub Wiki；Wiki 中有历史内容，使用时应
将其视为排障线索，代码、当前 README、当前 POM 和发布说明优先。

## 统一的事实来源与使用原则

- [README：模块表、JDK 8 下限、BOM 和 Demo 入口](https://github.com/binarywang/WxJava/blob/develop/README.md)
  是模块选择和依赖示例的首选入口。BOM 从 `4.8.3.B` 起提供，且 README 明确只在同时使用
  多个 WxJava 模块时推荐它。
- [CONTRIBUTING.md](https://github.com/binarywang/WxJava/blob/develop/CONTRIBUTING.md) 规定 PR
  的 fork、`develop` 目标分支及代码风格，是贡献型输出的最终依据。
- [GitHub Wiki 首页](https://github.com/binarywang/WxJava/wiki/Home) 集中列出了 token、依赖冲突、
  小程序解密和集群部署等常见问题；生成建议前必须与当前代码和 README 交叉核对。
- 回答具体微信接口的支持范围时，先搜索当前源码和 [Issues](https://github.com/binarywang/WxJava/issues)，
  不要从历史 Wiki 推断“当前仍支持”或“当前仍缺失”。例如 [#4007](https://github.com/binarywang/WxJava/issues/4007)
  是仍打开的 MP OAuth2 能力缺失请求，而 [#4005](https://github.com/binarywang/WxJava/issues/4005)
  是已关闭的服务号二维码跳转接口请求；两者不应得到同样结论。

## 按 SKILL 的可落地增强

### `wxjava-module-selector`

1. **把“产品边界”拆细。** 除 README 的 MP、MiniApp、Pay、CP、Open、Channel 映射外，明确追问：
   企业微信是自建应用还是第三方应用；开放平台是网站 OAuth 还是第三方平台代理；视频号是否已升级为
   微信小店。后一个边界可链接 [视频号／微信小店 Wiki](https://github.com/binarywang/WxJava/wiki/0_%E8%A7%86%E9%A2%91%E5%8F%B7_%E5%BE%AE%E4%BF%A1%E5%B0%8F%E5%BA%97%E5%BC%80%E5%8F%91%E6%96%87%E6%A1%A3)。
2. **把“SDK 是否已经覆盖”加入标准输出。** 先给主模块，再列“已在当前源码确认 / 需查 Issue /
   需用户自行调用底层接口”三种状态；[#4007](https://github.com/binarywang/WxJava/issues/4007)、
   [#4006](https://github.com/binarywang/WxJava/issues/4006)（MP OCR）展示了用户常把产品能力误认为
   SDK 已覆盖。
3. **多账号不是附加项。** 有多个独立 appId、公众号或商户时，输出必须让用户确认隔离方式；Wiki 的
   [CP 多应用说明](https://github.com/binarywang/WxJava/wiki/CP_%E5%A6%82%E4%BD%95%E6%94%AF%E6%8C%81%E5%A4%9A%E4%B8%AA%E4%BC%81%E4%B8%9A%E5%8F%B7%E5%BA%94%E7%94%A8%E6%88%96%E4%BC%81%E4%B8%9A%E5%8F%B7)
   明确提醒各应用共用 token、AES key 与 URL 有严重安全风险；[#3421](https://github.com/binarywang/WxJava/issues/3421)
   和 [#3556](https://github.com/binarywang/WxJava/issues/3556) 都是多实例需求的实际信号。
4. **BOM 的推荐应带条件。** 同时使用多个 WxJava 模块时推荐 BOM；如同时依赖 Spring Boot 等上游 BOM，
   要在输出中附加 `mvn help:effective-pom` 和 `mvn dependency:tree` 检查。已关闭的
   [#4058](https://github.com/binarywang/WxJava/issues/4058) 记录过 BOM import 影响 Spring Data Redis
   版本和 scope 的实例，不能把“使用 BOM”输出成无条件操作。
5. **允许真实的能力重叠。** 不能仅凭模块名称断言移动端 OAuth 能力归属：在
   [#3729](https://github.com/binarywang/WxJava/issues/3729) 中维护者明确 MP 和 Open 都实现了相关能力。
   技能应按授权主体、微信官方 API 域和回调场景推荐，并在存在重叠时解释两个可选项。

### `wxjava-integration-guide`

1. **接入输出按“依赖 → 配置 → 服务初始化 → 一条 API → 回调/验证”组织。** README 的 Maven 段和
   [Demo 入口](https://github.com/binarywang/WxJava/blob/develop/demo.md) 是依赖与示例的首选来源；不要用
   Wiki 的旧版本号直接生成 POM。
2. **在单/多账号分流前先问四项：** 产品、框架、独立账号数、首个 API。没有这些信息时，不要臆造
   Starter 的前缀或配置键。反向代理、统一 token 服务等非默认部署，需要转到
   [代理与反向代理 Wiki](https://github.com/binarywang/WxJava/wiki/SDK-%E9%92%88%E5%AF%B9%E5%BE%AE%E4%BF%A1-%E6%AD%A3%E5%90%91%E4%BB%A3%E7%90%86%E5%92%8C%E5%8F%8D%E5%90%91%E4%BB%A3%E7%90%86%E6%94%AF%E6%8C%81)
   再从当前 Starter 源码核验属性。
3. **支付最小示例必须有异步回调的验签和业务幂等。**
   [支付 Wiki](https://github.com/binarywang/WxJava/wiki/%E5%BE%AE%E4%BF%A1%E6%94%AF%E4%BB%98) 指出回调应
   校验签名、订单业务需避免重复处理；当前仓库的
   [新版商户转账用法](https://github.com/binarywang/WxJava/blob/develop/docs/NEW_TRANSFER_API_USAGE.md)
   还要求处理授权相关错误、转账状态和回调验签。技能应在产出中显式区分“示例可运行”和“生产安全”。
4. **集群部署默认提示共享存储。** [MP 配置存储 Wiki](https://github.com/binarywang/WxJava/wiki/MP_WxMpConfigStorage)
   和 [CP 配置存储 Wiki](https://github.com/binarywang/WxJava/wiki/CP_WxCpConfigStorage) 都说明生产集群应提供
   可共享 access token 的存储实现；这比让每个节点各自刷新 token 更可靠。
5. **加入部署环境分支。** 对 Quarkus/GraalVM，转到仓库的
   [Quarkus 支持文档](https://github.com/binarywang/WxJava/blob/develop/docs/QUARKUS_SUPPORT.md)，确认
   4.7.8.B+、Native Image 构建与反射限制，而非套用 Spring Boot 配置。
6. **Starter 与 demo 配置必须分开。** [#2177](https://github.com/binarywang/WxJava/issues/2177) 是将 demo
   配置与 Starter 属性模型混用而产生空 key/NPE 的历史案例。技能要先判定自动装配还是手动初始化，随后
   读取当前 Starter 的 `*Properties` 类核验属性；注入为空时先检查 profile、配置前缀和启动模块。

### `wxjava-troubleshooter`

将诊断树固定为“版本与模块 → 最小堆栈/响应 → 配置与多账号选择 → 认证材料 → 依赖树 →
网络/回调”。每次输出要提出一个检查动作和预期结果，而不是笼统建议重试。

| 症状／证据 | 先做的检查 | 一手来源与应写入 SKILL 的规则 |
| --- | --- | --- |
| token 失效、集群间不一致 | 确认 config storage 类型、节点是否共享、是否不必要地强制刷新 | [MP 刷新 token](https://github.com/binarywang/WxJava/wiki/MP_%E5%88%B7%E6%96%B0access_token) 与 [CP 刷新 token](https://github.com/binarywang/WxJava/wiki/CP_%E5%88%B7%E6%96%B0access_token) 说明常规调用自动刷新；[#3354](https://github.com/binarywang/WxJava/issues/3354)、[#3742](https://github.com/binarywang/WxJava/issues/3742) 是并发/刷新类报告。禁止将 token、secret 贴入日志。 |
| 签名错误、支付回调验签失败 | 保存脱敏后的响应码、请求路径、timestamp/nonce 是否存在、证书/公钥来源；先验签，后执行业务 | [新版转账文档](https://github.com/binarywang/WxJava/blob/develop/docs/NEW_TRANSFER_API_USAGE.md) 的回调示例；[#3399](https://github.com/binarywang/WxJava/issues/3399)、[#3610](https://github.com/binarywang/WxJava/issues/3610)、[#3915](https://github.com/binarywang/WxJava/issues/3915) 表明头部、证书和字段规范是高频根因。绝不建议关闭验签或 TLS 校验。 |
| `NoClassDefFoundError` / `NoSuchMethodError` / 启动失败 | 执行 `mvn dependency:tree`，检查冲突库、BOM import 顺序和打包产物 | [Wiki 依赖异常页](https://github.com/binarywang/WxJava/wiki/NoClassDefFoundError%E3%80%81NoSuchMethodError%E6%88%96ClassNotFoundException%E7%AD%89%E5%BC%82%E5%B8%B8%E7%9A%84%E8%A7%A3%E5%86%B3%E5%8A%9E%E6%B3%95)；[#4058](https://github.com/binarywang/WxJava/issues/4058) 是 Spring Boot BOM 影响的具体复现。输出中必须区分 IDE classpath 与部署包。 |
| Boot 3 或依赖库升级后注入失败 | 记录 JDK、Boot、WxJava 组合；先升级到适配的当前 WxJava 发行版，并用 dependency tree 核对 Jedis/OkHttp 等 | [#3150](https://github.com/binarywang/WxJava/issues/3150)（历史 Boot 3 兼容性）与 [#3129](https://github.com/binarywang/WxJava/issues/3129)（Jedis 冲突）说明必须检查实际组合；[#2987](https://github.com/binarywang/WxJava/issues/2987) 是 OkHttp 版本不匹配导致 `NoSuchFieldError` 的案例。不要靠猜单一库版本修复。 |
| 升级到 4.8 后 HTTP 客户端异常 | 先确认 HTTP client 版本、代理 host/port/username/password 是否完整，再看当前升级指南 | [HttpClient 升级指南](https://github.com/binarywang/WxJava/blob/develop/docs/HTTPCLIENT_UPGRADE_GUIDE.md) 与 [#3836](https://github.com/binarywang/WxJava/issues/3836)；技能应特别要求检查可选代理密码的空值，而非让用户修改无关业务代码。 |
| CP 会话存档偶发 SIGSEGV/JVM 崩溃 | 立即检查是否还在调用旧 API 或手动 `Finance.DestroySdk()`；升级后改用框架管理生命周期的新 API | [会话存档生命周期重构](https://github.com/binarywang/WxJava/blob/develop/docs/CP_MSG_AUDIT_THREADLOCAL_LIFECYCLE_REFACTOR.md) 和 [安全使用迁移文档](https://github.com/binarywang/WxJava/blob/develop/docs/CP_MSG_AUDIT_SDK_SAFE_USAGE.md)；[#3670](https://github.com/binarywang/WxJava/issues/3670) 提供了旧 API + 手动销毁导致 native 崩溃的完整实例。此类问题不要建议“多重试”。 |
| 小程序用户数据解密 JSON 异常 | 核验 session key、encryptedData/iv、Base64 和微信端签名条件，保留脱敏异常 | [Wiki 首页](https://github.com/binarywang/WxJava/wiki/Home) 指向 [#359](https://github.com/binarywang/WxJava/issues/359)；技能应把它归入输入/签名契约，而非泛化为 Gson 故障。 |

### `wxjava-api-contributor`

1. **把“缺接口”和“实现错误”分开走。** 新接口先检索当前 Service、实现、Bean 和未关闭 Issue；已有
   [CP/MP 调用未支持接口 Wiki](https://github.com/binarywang/WxJava/wiki/MP_%E5%A6%82%E4%BD%95%E8%B0%83%E7%94%A8%E6%9C%AA%E6%94%AF%E6%8C%81%E7%9A%84%E6%8E%A5%E5%8F%A3)
   可作为临时绕过线索（实际实现时需按目标模块选择对应 API），但不能替代 SDK 的正式扩展。
2. **新增接口的 checklist 具体到仓库结构：** 官方契约 → 目标 `*Service` 方法 → `*ServiceImpl`
   的 URL/HTTP 执行 → 请求与响应 Bean 的 JSON/XML 映射 → Javadoc → TestNG 成功、错误和字段回归测试
   → 相邻 Starter/多账号/HTTP 实现是否受影响。这对应仓库 AGENTS 指南和
   [CONTRIBUTING.md](https://github.com/binarywang/WxJava/blob/develop/CONTRIBUTING.md) 的当前流程。
3. **用近期 Issue 强制字段审查。** [#3999](https://github.com/binarywang/WxJava/issues/3999)、
   [#4000](https://github.com/binarywang/WxJava/issues/4000)、[#3917](https://github.com/binarywang/WxJava/issues/3917)、
   [#3941](https://github.com/binarywang/WxJava/issues/3941) 都是支付 Bean 漏字段或映射缺失。技能应该要求对
   官方请求/响应字段逐项对照，尤其是 optional 字段、嵌套金额字段和 callback `change_type`。
4. **提交前给出精确命令。** 默认 `mvn -pl <module> -am test`；公共模块/BOM/多模块改动扩展范围，并总是
   跑 `git diff --check`。要明确 PR 面向 `develop`，不混入格式化或依赖升级。
5. **Issue 模板要足以落地实现。** 参考 [#3327](https://github.com/binarywang/WxJava/issues/3327) 和
   [#4008](https://github.com/binarywang/WxJava/issues/4008)，要求提供官方文档 URL、API 域/Base URL、认证
   方式、请求/响应样例、与既有模块的边界和遗漏接口清单。新 API 不能仅因名称相近就塞进既有模块。
   若是紧急生产需求，参考 [#3163](https://github.com/binarywang/WxJava/issues/3163)：使用已验证 commit
   或组织内构建物，同时仍以单主题、可合并的 PR 回馈上游。

### `wxjava-upgrade-guide`

1. **升级前的基线收集必须可执行：** `mvn dependency:tree`、现用 WxJava artifact 与版本、JDK、
   Spring Boot/Solon 版本、是否 import BOM、代理配置、关键 API（尤其 token、回调、支付、会话存档）。
2. **按升级类型分支，而不是只改版本号。**
   - HTTP client 迁移：按 [HTTPCLIENT_UPGRADE_GUIDE.md](https://github.com/binarywang/WxJava/blob/develop/docs/HTTPCLIENT_UPGRADE_GUIDE.md)
     执行依赖、配置、应用测试；[#3836](https://github.com/binarywang/WxJava/issues/3836) 说明代理配置的空值路径也要回归。
   - CP 会话存档：4.8.0 后按 [ThreadLocal 生命周期迁移](https://github.com/binarywang/WxJava/blob/develop/docs/CP_MSG_AUDIT_THREADLOCAL_LIFECYCLE_REFACTOR.md)
     逐一替换旧 API，删除手动 SDK 生命周期管理，并进行并发/压力验证。
   - BOM 导入：根据 README 的 `4.8.3.B+` 前提，先验证 effective POM 和 dependency tree；[#4058](https://github.com/binarywang/WxJava/issues/4058)
     的已关闭报告意味着要在 Spring Boot 项目中专门回归 Redis 等受 Spring BOM 管理的依赖。
3. **升级验证应分三层：** 编译与单测 → 非生产凭据下的 API 冒烟（token、回调验签、支付） → 灰度与可观测性。
   支付与转账不能仅凭 HTTP 2xx 宣称成功；[#4050](https://github.com/binarywang/WxJava/issues/4050) 的 202
   响应问题说明要核对该 API 的官方成功语义及业务后续状态。
4. **回滚条件必须明确：** 保留上一个可用依赖锁定/构建物；若出现签名、证书、回调、依赖树或 native
   崩溃异常，停止扩大灰度，先以最小复现定位。不要把关闭校验、固定 token 或跳过验签当作回滚方案。
5. **多账号回调是独立回归项。** [#2995](https://github.com/binarywang/WxJava/issues/2995) 的历史回归显示：
   回调处理应先由请求/消息确定 appId，显式切换服务上下文后再路由；异步任务也要显式传递 appId，不能
   假定 ThreadLocal 自动继承。升级后需至少覆盖单账号和多账号的真实回调路径。

## 维护方式

每次细化某个 SKILL 时，优先把本文件列出的通用原则转成该 SKILL 的简短决策步骤，并把专题细节放入其
自己的 `references/` 文件。新增事实必须附上当前仓库、Wiki 或 GitHub Issue 的精确 URL；若 Issue 已关闭，
只把它作为问题模式或历史复现，不作为当前行为的证明。
