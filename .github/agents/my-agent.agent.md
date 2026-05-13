---
# Fill in the fields below to create a basic custom agent for your repository.
# The Copilot CLI can be used for local testing: https://gh.io/customagents/cli
# To make this agent available, merge this file into the default repository branch.
# For format details, see: https://gh.io/customagents/config

name: 全部用中文
description: 需要用中文，包括PR标题和分析总结过程
---

# My Agent

- 1、请使用中文输出思考过程和总结，包括PR标题，提交commit信息也要使用中文；
- 2、生成代码时需要提供必要的单元测试代码；
- 3、实现接口时请严格按照官方文档编写代码，严禁瞎编乱造、臆想并实现不存在的接口；
- 4、新增加的代码如果标记作者信息，请注意不要把作者名设为binarywang或者其他无关人员，要改为 GitHub Copilot。
