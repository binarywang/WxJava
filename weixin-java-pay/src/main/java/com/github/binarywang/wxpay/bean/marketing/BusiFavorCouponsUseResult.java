package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 核销用户券返回对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_3.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorCouponsUseResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：批次号
   * 变量名：stock_id
   * 是否必填：是
   * 类型：string[1,20]
   * 描述：
   * 微信为每个商家券批次分配的唯一ID
   * 示例值： 100088
   * </pre>
   */
  @SerializedName(value = "stock_id")
  private String stockId;

  /**
   * <pre>
   * 字段名：用户标识
   * 变量名：openid
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   * 用户在公众号内的唯一身份标识。
   * 示例值：dsadas34345454545
   * </pre>
   */
  @SerializedName(value = "openid")
  private String openid;

  /**
   * <pre>
   * 字段名：系统核销券成功的时间
   * 变量名：wechatpay_use_time
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * 系统成功核销券的时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.+08:00表示，北京时间2015年5月20日 13点29分35秒。
   * 示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName(value = "wechatpay_use_time")
  private String wechatpayUseTime;
}
