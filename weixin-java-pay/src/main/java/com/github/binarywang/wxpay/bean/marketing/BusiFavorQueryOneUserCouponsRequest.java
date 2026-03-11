package com.github.binarywang.wxpay.bean.marketing;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询用户单张券详情API请求对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_5.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorQueryOneUserCouponsRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>* 字段名：用户标识
   * 变量名：openid
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   * path Openid信息，用户在appid下的唯一标识。 示例值：2323dfsdf342342
   * </pre>
   */
  @SerializedName(value = "openid")
  private String openid;

  /**
   * <pre>* 字段名：公众账号ID
   * 变量名：appid
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * query 支持传入与当前调用接口商户号有绑定关系的appid。支持小程序appid与公众号appid。 示例值：wx233544546545989
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appid;

  /**
   * <pre>* 字段名：券code
   * 变量名：coupon_code
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * path 券的唯一标识。 示例值：123446565767
   * </pre>
   */
  @SerializedName(value = "coupon_code")
  private String couponCode;
}
