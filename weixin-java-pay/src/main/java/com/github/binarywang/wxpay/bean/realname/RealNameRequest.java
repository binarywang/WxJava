package com.github.binarywang.wxpay.bean.realname;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;

/**
 * <pre>
 * 微信支付实名验证请求对象.
 * 详见文档：https://pay.wechatpay.cn/doc/v2/merchant/4011987607
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class RealNameRequest extends BaseWxPayRequest {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：用户标识
   * 变量名：openid
   * 是否必填：是
   * 类型：String(128)
   * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
   * 描述：用户在商户appid下的唯一标识
   * </pre>
   */
  @Required
  @XStreamAlias("openid")
  private String openid;

  @Override
  protected void checkConstraints() {
    //do nothing
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("openid", openid);
  }
}
