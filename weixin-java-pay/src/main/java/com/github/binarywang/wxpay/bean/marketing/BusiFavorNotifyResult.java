package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 领券事件回调通知API返回对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_15.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorNotifyResult implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>* 字段名：返回状态码
   * 变量名：code
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * 错误码，SUCCESS为清算机构接收成功，其他错误码为失败。 示例值：SUCCESS
   * </pre>
   */
  @SerializedName(value = "code")
  private String code;

  /**
   * <pre>* 字段名：返回信息
   * 变量名：message
   * 是否必填：是
   * 类型：string[1,64]
   * 描述：
   * 返回信息，如非空，为错误原因。 示例值：系统错误
   * </pre>
   */
  @SerializedName(value = "message")
  private String message;
}
