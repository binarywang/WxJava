# WxJava Quarkus/GraalVM Native Image Support

## 概述

从 4.7.8.B 版本开始，WxJava 提供了对 Quarkus 和 GraalVM Native Image 的支持。这允许您将使用 WxJava 的应用程序编译为原生可执行文件，从而获得更快的启动速度和更低的内存占用。

## 问题背景

在之前的版本中，使用 Quarkus 构建 Native Image 时会遇到以下错误：

```
Error: Unsupported features in 3 methods
Detailed message:
Error: Detected an instance of Random/SplittableRandom class in the image heap. 
Instances created during image generation have cached seed values and don't behave as expected.
The culprit object has been instantiated by the 'org.apache.http.impl.auth.NTLMEngineImpl' class initializer
```

## 解决方案

为了解决这个问题，WxJava 进行了以下改进：

### 1. Random 实例的延迟初始化

所有 `java.util.Random` 实例都已改为延迟初始化，避免在类加载时创建：

- `RandomUtils` - 使用双重检查锁定模式延迟初始化
- `SignUtils` - 使用双重检查锁定模式延迟初始化
- `WxCryptUtil` - 使用双重检查锁定模式延迟初始化

### 2. Native Image 配置

在 `weixin-java-common` 模块中添加了 GraalVM Native Image 配置文件：

- `META-INF/native-image/com.github.binarywang/weixin-java-common/native-image.properties`
  - 配置 Apache HttpClient 相关类在运行时初始化，避免在构建时创建 SecureRandom 实例

- `META-INF/native-image/com.github.binarywang/weixin-java-common/reflect-config.json`
  - 配置反射访问的类和方法

## 使用方式

### Quarkus 项目配置

在您的 Quarkus 项目中使用 WxJava，只需正常引入依赖即可：

```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>weixin-java-miniapp</artifactId>  <!-- 或其他模块 -->
  <version>4.7.8.B</version>
</dependency>
```

### 构建 Native Image

使用 Quarkus 构建原生可执行文件：

```bash
./mvnw package -Pnative
```

或使用容器构建：

```bash
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

### GraalVM Native Image

如果直接使用 GraalVM Native Image 工具：

```bash
native-image --no-fallback \
  -H:+ReportExceptionStackTraces \
  -jar your-application.jar
```

WxJava 的配置文件会自动被 Native Image 工具识别和应用。

## 测试验证

建议在构建 Native Image 后进行以下测试：

1. 验证应用程序可以正常启动
2. 验证微信 API 调用功能正常
3. 验证随机字符串生成功能正常
4. 验证加密/解密功能正常

## 已知限制

- 本配置主要针对 Quarkus 3.x 和 GraalVM 22.x+ 版本进行测试
- 如果使用其他 Native Image 构建工具（如 Spring Native），可能需要额外配置
- 部分反射使用可能需要根据实际使用的 WxJava 功能进行调整

## 问题反馈

如果在使用 Quarkus/GraalVM Native Image 时遇到问题，请通过以下方式反馈：

1. 在 [GitHub Issues](https://github.com/binarywang/WxJava/issues) 提交问题
2. 提供详细的错误信息和 Native Image 构建日志
3. 说明使用的 Quarkus 版本和 GraalVM 版本

## 参考资料

- [Quarkus 官方文档](https://quarkus.io/)
- [GraalVM Native Image 文档](https://www.graalvm.org/latest/reference-manual/native-image/)
- [Quarkus Tips for Writing Native Applications](https://quarkus.io/guides/writing-native-applications-tips)

## 贡献

欢迎提交 PR 完善 Quarkus/GraalVM 支持！如果您发现了新的兼容性问题或有改进建议，请参考 [代码贡献指南](CONTRIBUTING.md)。
