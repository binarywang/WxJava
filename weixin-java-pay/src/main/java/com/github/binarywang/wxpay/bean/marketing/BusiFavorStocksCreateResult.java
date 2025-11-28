package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 创建商家券返回对象
 * 文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_1.shtml
 *
 * @author yujam
 */
@NoArgsConstructor
@Data
public class BusiFavorStocksCreateResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：批次号
   * 变量名：stock_id
   * 是否必填：是
   * 类型：string[1,20]
   * 描述：
   * 微信为每个商家券批次分配的唯一ID
   * 示例值： 98065001
   * </pre>
   */
  @SerializedName("stock_id")
  private String stockId;

  /**
   * <pre>
   * 字段名：创建时间
   * 变量名：create_time
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.+08:00表示，北京时间2015年5月20日 13点29分35秒。
   *  示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName("create_time")
  private String createTime;
}
