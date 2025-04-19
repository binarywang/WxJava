# 使用API签名

如果对API签名不了解，可先阅读微信文档 [服务端API签名指南](https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/getting_started/api_signature.html)

有API数据加密与签名两种功能，此处按照微信文档，简称为签名。

## 程序内设置

[WxMaConfig](src/main/java/cn/binarywang/wx/miniapp/config/WxMaConfig.java)类增加了几个属性，分别对应小程序内设置的签名密钥等。

* apiSignatureAesKey
* apiSignatureAesKeySn
* apiSignatureRsaPrivateKey
* apiSignatureRsaPrivateKeySn

这4个属性需要按照小程序后台设置。

## 小程序后台设置/查看签名用密钥

在小程序后台，开发管理 -> 开发设置 -> API安全处，可以看到如下图界面。

![图一](../images/api-signature/api-signature-1.png)

上图中A处对应 apiSignatureAesKeySn； B处对应apiSignatureAesKey; C处对应apiSignatureRsaPrivateKeySn

apiSignatureRsaPrivateKey 在上图中**无**对应，C处右侧是公钥，apiSignatureRsaPrivateKey 需要的是私钥。

可点击图上右上角的修改，打开如下图的设置页面

![图二](../images/api-signature/api-signature-2.png)

首先确保对称密钥选中 AES256，非对称密钥选中RSA。不要选SM4和SM2。
（如果需要支持SM4/SM2，可修改BaseWxMaServiceImpl.java中postWithSignature方法中相应部分实现）。

在API非对称密钥中下方左侧有个「随机生成密钥对」，点击它，然后点它右侧的「下载私钥」，之后点击「确认」保存。
下载得到的文件是PKCS1格式的私钥，用openssl可转成PKCS8格式。apiSignatureRsaPrivateKey 需要设置的是PKCS8格式的私钥。

注意：

1. 如果不先点击「随机生成密钥对」，直接点击「下载私钥」得到的是公钥，公钥在这里没有用途。
2. 打开下载的文件，第一行是「-----BEGIN RSA PRIVATE KEY-----」说明是PKCS1格式私钥。
3. PKCS8格式第一行是「-----BEGIN PRIVATE KEY-----」
4. 转换命令 `openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in PKCS1格式密钥文件名 -out 新的PKCS8格式密钥文件名`
5. 如果密钥文件有 PUBLIC KEY 字样，说明下载了公钥，重新生成密钥对，下载私钥


