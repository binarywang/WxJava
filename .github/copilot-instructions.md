# Copilot Instruction
请始终使用中文生成 Pull Request 的标题、描述和提交信息


# WxJava - 微信 Java SDK 开发说明

WxJava 是一个支持多种微信平台的完整 Java SDK，包含公众号、小程序、微信支付、企业微信、开放平台、视频号、企点等多种功能模块。

**请始终优先参考本说明，只有在遇到与此内容不一致的意外信息时，才退而使用搜索或 bash 命令。**

## 高效开发指南

### 前置条件与环境准备
- **Java 要求**：JDK 8+（项目最低目标为 Java 8）
- **Maven**：推荐 Maven 3.6+（已验证 Maven 3.9.11）
- **IDE**：推荐使用 IntelliJ IDEA（项目针对 IDEA 优化）

### 引导、构建与校验
克隆仓库后按顺序执行以下命令：

```bash
# 1. 基础编译（请勿中断 - 约需 4-5 分钟）
mvn clean compile -DskipTests=true --no-transfer-progress
# 超时时间：建议设置 8 分钟以上。实际时间：约 4 分钟

# 2. 完整打包（请勿中断 - 约需 2-3 分钟）  
mvn clean package -DskipTests=true --no-transfer-progress
# 超时时间：建议设置 5 分钟以上。实际时间：约 2 分钟

# 3. 代码质量校验（请勿中断 - 约需 45-60 秒）
mvn checkstyle:check --no-transfer-progress
# 超时时间：建议设置 3 分钟以上。实际时间：约 50 秒
```

重要时间说明：
- 绝对不要中断任意 Maven 构建命令
- 编译阶段耗时最长（约 4 分钟），原因是项目包含 34 个模块
- 后续构建会更快，因为存在增量编译
- 始终使用 `--no-transfer-progress` 以减少日志噪音

### 测试结构
- **测试框架**：TestNG（非 JUnit）
- **测试文件**：共有 298 个测试文件
- **默认行为**：pom.xml 中默认禁用测试（`<skip>true</skip>`）
- **测试配置**：测试需要通过 test-config.xml 提供真实的微信 API 凭据
- **注意**：没有真实微信 API 凭据请不要尝试运行测试，测试将会失败

## 项目结构与导航

### 核心 SDK 模块（主要开发区）
- `weixin-java-common/` - 通用工具与基础类（最重要）
- `weixin-java-mp/` - 公众号 SDK
- `weixin-java-pay/` - 微信支付 SDK
- `weixin-java-miniapp/` - 小程序 SDK
- `weixin-java-cp/` - 企业微信 SDK
- `weixin-java-open/` - 开放平台 SDK
- `weixin-java-channel/` - 视频号 / Channel SDK
- `weixin-java-qidian/` - 企点 SDK

### 框架集成模块
- `spring-boot-starters/` - Spring Boot 自动配置 starter
- `solon-plugins/` - Solon 框架插件
- `weixin-graal/` - GraalVM 本地镜像支持

### 配置与质量控制
- `quality-checks/google_checks.xml` - Checkstyle 配置
- `.editorconfig` - 代码格式规则（2 个空格等于 1 个制表）
- `pom.xml` - 根级 Maven 配置

## 开发工作流

### 修改代码的流程
1. 修改前务必先构建以建立干净基线：
   ```bash
   mvn clean compile --no-transfer-progress
   ```

2. 遵循代码风格（由 checkstyle 强制）：
   - 缩进使用 2 个空格（不要用制表符）
   - 遵循 Google Java 风格指南
   - 在 IDE 中安装 EditorConfig 插件

3. 增量验证修改：
   ```bash
   # 每次修改后运行：
   mvn compile --no-transfer-progress
   mvn checkstyle:check --no-transfer-progress
   ```

### 提交修改前的必须校验
请务必按顺序完成以下校验步骤：

1. 代码风格校验：
   ```bash
   mvn checkstyle:check --no-transfer-progress
   # 必须通过 - 约需 50 秒
   ```

2. 完整清理构建：
   ```bash
   mvn clean package -DskipTests=true --no-transfer-progress  
   # 必须成功 - 约需 2 分钟
   ```

3. 文档：为公共方法和类补充或更新 javadoc
4. 贡献规范：遵循 `CONTRIBUTING.md`，Pull Request 必须以 `develop` 分支为目标

## 模块依赖与构建顺序

### 核心模块依赖（构建顺序）
1. `weixin-graal`（GraalVM 支持）
2. `weixin-java-common`（所有模块的基础）
3. 核心 SDK 模块（mp、pay、miniapp、cp、open、channel、qidian）
4. 框架集成（spring-boot-starters、solon-plugins）

### 主要关系模式
- 所有 SDK 模块都依赖于 `weixin-java-common`
- Spring Boot starters 依赖对应的 SDK 模块
- Solon 插件遵循与 Spring Boot starters 相同的依赖模式
- 每个模块都有单账号与多账号配置支持

## 常见任务与命令

### 验证指定模块
```bash
# 构建单个模块（将 'weixin-java-mp' 替换为目标模块）：
cd weixin-java-mp
mvn clean compile --no-transfer-progress
```

### 检查依赖
```bash
# 分析依赖树：
mvn dependency:tree --no-transfer-progress

# 检查依赖更新：  
./others/check-dependency-updates.sh
```

### 发布与发布准备
```bash
# 版本检查：
mvn versions:display-property-updates --no-transfer-progress

# 部署（需要凭据）：
mvn clean deploy -P release --no-transfer-progress
```

## 重要文件与位置

### 配置文件
- `pom.xml` - 根级 Maven 配置与依赖管理
- `quality-checks/google_checks.xml` - Checkstyle 规则
- `.editorconfig` - IDE 格式化配置
- `.github/workflows/maven-publish.yml` - CI/CD 工作流

### 文档
- `README.md` - 项目概览与使用说明（中文）
- `CONTRIBUTING.md` - 贡献指南
- `demo.md` - 示例项目与演示链接
- 每个模块均有单独的文档与示例

### 测试资源
- `*/src/test/resources/test-config.sample.xml` - 测试配置模板
- 测试运行需要真实的微信 API 凭据

## SDK 使用模式

### Maven 依赖示例
```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>weixin-java-mp</artifactId>  <!-- 或其他模块 -->
  <version>4.7.0</version>
</dependency>
```

### 常见开发区域
- **API 客户端实现**：位于 `*/service/impl/` 目录
- **模型类**：位于 `*/bean/` 目录
- **配置**：位于 `*/config/` 目录
- **工具类**：位于 `weixin-java-common` 的 `*/util/` 目录

## 故障排查

### 构建问题
- **OutOfMemoryError**：增加 Maven 内存：`export MAVEN_OPTS="-Xmx2g"`
- **编译失败**：通常为依赖问题 - 先执行 `mvn clean`
- **Checkstyle 失败**：检查 IDE 的 `.editorconfig` 设置

### 常见陷阱
- **测试默认跳过**：这是正常现象 — 测试需要微信 API 凭据
- **多模块变更**：总是在仓库根目录构建，而不是单独模块
- **分支目标**：Pull Request 必须以 `develop` 分支为目标，而不是 `master` 或 `release`

## 性能说明
- **首次构建**：由于依赖下载，耗时 4-5 分钟
- **增量构建**：通常更快（约 30-60 秒）
- **Checkstyle**：运行迅速（约 50 秒），应当经常运行
- **IDE 性能**：项目使用 Lombok，请确保启用注解处理

注意：本项目为 SDK 库项目，而非可运行应用。修改应以 API 功能为主，不要改动应用级行为。
