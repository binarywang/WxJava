package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;

import java.util.Map;

/**
 * @author chenliang
 * created on  2021-08-02 5:13 下午
 * <pre>
 *   小程序纯签约入参
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxMaEntrustRequest extends BaseWxPayRequest {
  private static final long serialVersionUID = -2823017402712927893L;

  /**
   * <pre>
   * 协议模板ID
   * plan_id
   * 是
   * String(28)
   * 12535
   * 协议模板ID，分为首次签约，支付中签约，重新签约
   * </pre>
   */
  @Required
  @SerializedName(value = "plan_id")
  @XStreamAlias("plan_id")
  private String planId;

  /**
   * <pre>
   * 签约协议号
   * contract_code
   * 是
   * String(32)
   * 100000
   * 商户侧签约协议号，由商户生成，只能是数字，大小写字母组成
   * </pre>
   */
  @Required
  @SerializedName(value = "contract_code")
  @XStreamAlias("contract_code")
  private String contractCode;

  /**
   * <pre>
   * 请求序列号
   * request_serial
   * 是
   * int(64)
   * 1000
   * 商户请求签约时的序列号，要求唯一性，禁止使用0开头的，用户排序，纯数字
   * </pre>
   */
  @Required
  @SerializedName(value = "request_serial")
  @XStreamAlias("request_serial")
  private Long requestSerial;

  /**
   * <pre>
   * 用户账户展示名称
   * contract_display_account
   * 是
   * string(32)
   * 微信代扣
   * 签约用户的名称，用户页面展示，不支持符号表情
   * </pre>
   */
  @Required
  @SerializedName(value = "contract_display_account")
  @XStreamAlias("contract_display_account")
  private String contractDisplayAccount;

  /**
   * <pre>
   * 回调通知URL
   * notify_url
   * 是
   * string(256)
   * https://weixin.qq.com
   * 用于接收签约成功消息的回调通知地址
   * </pre>
   */
  @Required
  @SerializedName(value = "notify_url")
  @XStreamAlias("notify_url")
  private String notifyUrl;

  /**
   * @deprecated 小程序纯签约接口不支持该参数，设置后不会参与请求序列化或签名。
   */
  @Deprecated
  @XStreamOmitField
  @XStreamAlias("version")
  private transient String version;


  /**
   * <pre>
   * 时间戳
   * timestamp
   * 是
   * string(10)
   * 1414488825
   * 系统当前时间，10位
   * </pre>
   */
  @Required
  @XStreamAlias("timestamp")
  private String timestamp;

  /**
   * <pre>
   * 商户侧用户标识
   * outerId
   * 否
   * String
   * 陈*(141448825)
   * 用户在商户侧的标识
   * </pre>
   */
  @XStreamAlias("outerid")
  private String outerId;

  @Override
  protected void checkConstraints() throws WxPayException {

  }

  /**
   * 是否需要nonce_str
   */
  @Override
  protected boolean needNonceStr() {
    return false;
  }

  @Override
  protected String[] getIgnoredParamsForSign() {
    return new String[]{"version"};
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("plan_id", planId);
    map.put("contract_code", contractCode);
    map.put("request_serial", String.valueOf(requestSerial));
    map.put("contract_display_account", contractDisplayAccount);
    map.put("notify_url", notifyUrl);
    map.put("timestamp", timestamp);
    map.put("outerid", outerId);
  }

  @Override
  public String toString() {
    GsonBuilder gsonBuilder = new GsonBuilder();

    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

    Gson gson = gsonBuilder.create();

    return gson.toJson(this);
  }
}
