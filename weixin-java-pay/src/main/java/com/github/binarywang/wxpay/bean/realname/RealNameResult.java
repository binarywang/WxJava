package com.github.binarywang.wxpay.bean.realname;

import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;

/**
 * <pre>
 * 微信支付实名验证返回结果.
 * 详见文档：https://pay.wechatpay.cn/doc/v2/merchant/4011987607
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class RealNameResult extends BaseWxPayResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：用户标识
   * 变量名：openid
   * 是否必填：否
   * 类型：String(128)
   * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
   * 描述：用户在商户appid下的唯一标识
   * </pre>
   */
  @XStreamAlias("openid")
  private String openid;

  /**
   * <pre>
   * 字段名：实名认证状态
   * 变量名：is_certified
   * 是否必填：是
   * 类型：String(1)
   * 示例值：Y
   * 描述：Y-已实名认证 N-未实名认证
   * </pre>
   */
  @XStreamAlias("is_certified")
  private String isCertified;

  /**
   * <pre>
   * 字段名：实名认证信息
   * 变量名：cert_info
   * 是否必填：否
   * 类型：String(256)
   * 示例值：
   * 描述：实名认证的相关信息，如姓名等（加密）
   * </pre>
   */
  @XStreamAlias("cert_info")
  private String certInfo;

  /**
   * <pre>
   * 字段名：引导链接
   * 变量名：guide_url
   * 是否必填：否
   * 类型：String(256)
   * 示例值：
   * 描述：未实名时，引导用户进行实名认证的URL
   * </pre>
   */
  @XStreamAlias("guide_url")
  private String guideUrl;

  /**
   * 从XML结构中加载额外的属性
   *
   * @param d Document
   */
  @Override
  protected void loadXml(Document d) {
    openid = readXmlString(d, "openid");
    isCertified = readXmlString(d, "is_certified");
    certInfo = readXmlString(d, "cert_info");
    guideUrl = readXmlString(d, "guide_url");
  }
}
