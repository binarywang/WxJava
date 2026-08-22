# Task 1 报告

## 修改

- 新增 `WxChannelKfCosUploadResponse`，映射 `cos_url` 并继承频道基础响应。
- 新增 `WxChannelKfSendMsgParam`，映射 `request_id`、`open_id`、`msg_type` 和嵌套 `text.content`。
- 新增 `WxChannelKfSendMsgResponse`，映射 `msg_id` 并继承频道基础响应。
- 新增 TestNG JSON 编解码测试 `WxChannelKfBeanTest`。

## TDD 测试

- 红灯：`mvn -pl weixin-java-channel -Dtest=WxChannelKfBeanTest -DfailIfNoTests=false test`，因 3 个模型类不存在，在 testCompile 阶段失败。
- 绿灯：`mvn -pl weixin-java-channel dependency:build-classpath -Dmdep.outputFile=/tmp/wxjava-channel-test-cp.txt -Dmdep.includeScope=test -q && TEST_CP="$(cat /tmp/wxjava-channel-test-cp.txt)" && java -cp "weixin-java-channel/target/test-classes:weixin-java-channel/target/classes:${TEST_CP}" org.testng.TestNG -testclass me.chanjar.weixin.channel.bean.kf.WxChannelKfBeanTest`，3 个测试通过，0 失败，0 跳过。
- `git diff --check`：通过。

## Commit

`a651123a1`（新增视频号客服消息模型）。

## 顾虑

仓库父 POM 的 Surefire pluginManagement 默认配置 `skip=true`，因此 Maven 测试命令会显示 Tests are skipped；已使用同一编译产物和依赖通过 TestNG CLI 实际执行测试。
