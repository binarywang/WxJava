# Maven 测试

GitHub Actions 使用 Java 17 运行无需微信凭据的测试套件：

```shell
mvn clean test -Dmaven.test.skip=false --no-transfer-progress
```

默认构建通过 `maven.test.skip=true` 跳过测试编译及执行。显式设置为 `false` 才会运行测试。
各 SDK 模块的 `src/test/resources/testng.xml` 定义默认测试集合；新增离线测试时应将其注册到对应套件。

支付模块的默认套件涵盖 Bean、签名、配置及本地 Service 测试，不运行需要真实商户配置、证书或外部服务的示例。
需要运行集成测试时，在本地准备模块要求的测试配置及凭据，并显式选择测试类，例如：

```shell
mvn -pl weixin-java-pay -am test -Dmaven.test.skip=false \
  -Dtest=BaseWxPayServiceImplTest -Dsurefire.failIfNoSpecifiedTests=false
```

不要将真实测试配置或凭据提交到仓库。排查测试失败时查看模块的 `target/surefire-reports`。
CP 的反射开放参数仅在 Java 9 及以上激活，Java 8 不传入 `--add-opens`。
