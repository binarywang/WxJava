package com.binarywang.spring.starter.wxjava.pay.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <pre>
 *  微信支付属性配置类
 * Created by Binary Wang on 2019/4/17.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@ConfigurationProperties(prefix = "wx.pay")
public class WxPayProperties {
  /**
   * 设置微信公众号或者小程序等的appid.
   */
  private String appId;

  /**
   * 微信支付商户号.
   */
  private String mchId;

  /**
   * 微信支付商户密钥.
   */
  private String mchKey;

  /**
   * 服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除.
   */
  private String subAppId;

  /**
   * 服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除.
   */
  private String subMchId;

  /**
   * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定.
   */
  private String keyPath;

  /**
   * 微信支付分serviceId
   */
  private String serviceId;

  /**
   * 证书序列号
   */
  private String certSerialNo;

  /**
   * apiV3秘钥
   */
  private String apiv3Key;

  /**
   * 微信支付异步回调地址，通知url必须为直接可访问的url，不能携带参数
   */
  private String notifyUrl;

  /**
   * 微信支付分回调地址
   */
  private String payScoreNotifyUrl;

  /**
   * 微信支付分授权回调地址
   */
  private String payScorePermissionNotifyUrl;

  /**
   * apiv3 商户apiclient_key.pem
   */
  private String privateKeyPath;

  /**
   * apiv3 商户apiclient_cert.pem
   */
  private String privateCertPath;

  /**
   * 公钥ID
   */
  private String publicKeyId;

  /**
   * pub_key.pem证书文件的绝对路径或者以classpath:开头的类路径.
   */
  private String publicKeyPath;

  /**
   * 微信支付是否使用仿真测试环境.
   * 默认不使用
   */
  private boolean useSandboxEnv;

  /**
   * 自定义API主机地址，用于替换默认的 https://api.mch.weixin.qq.com
   * 例如：http://proxy.company.com:8080
   */
  private String apiHostUrl;

  /**
   * 是否将全部v3接口的请求都添加Wechatpay-Serial请求头，默认不添加
   */
  private boolean strictlyNeedWechatPaySerial = false;

  /**
   * 是否完全使用公钥模式(用以微信从平台证书到公钥的灰度切换)，默认不使用
   */
  private boolean fullPublicKeyModel = false;

}
