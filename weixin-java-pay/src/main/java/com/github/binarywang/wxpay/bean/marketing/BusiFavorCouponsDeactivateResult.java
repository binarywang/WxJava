package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 使券失效返回对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_14.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorCouponsDeactivateResult implements Serializable {

  /**
   * <pre>* 字段名：券成功失效的时间
   * 变量名：wechatpay_deactivate_time
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * 系统券成功失效的时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。 示例值：2020-05-20T13:29:35.08:00
   * </pre>
   */
  @SerializedName(value = "wechatpay_deactivate_time")
  private String wechatpayDeactivateTime;
}
