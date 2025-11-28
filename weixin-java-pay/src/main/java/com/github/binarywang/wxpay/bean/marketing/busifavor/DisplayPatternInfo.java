package com.github.binarywang.wxpay.bean.marketing.busifavor;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 样式信息
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_1.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class DisplayPatternInfo implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * <pre>
   * 字段名：使用须知
   * 变量名：description
   * 是否必填：否
   * 类型：string[1,1000]
   * 描述：
   *  用于说明详细的活动规则，会展示在代金券详情页。
   *  示例值：xxx门店可用
   * </pre>
   */
  @SerializedName(value = "description")
  private String description;

  /**
   * <pre>
   * 字段名：商户logo
   * 变量名：merchant_logo_url
   * 是否必填：否
   * 类型：string[1,128]
   * 描述：
   *  商户logo的URL地址，仅支持通过《图片上传API》接口获取的图片URL地址。
   *  1、商户logo大小需为120像素*120像素。
   *  2、支持JPG/JPEG/PNG格式，且图片小于1M。
   *  示例值：https://qpic.cn/xxx
   * </pre>
   */
  @SerializedName(value = "merchant_logo_url")
  private String merchantLogoUrl;

  /**
   * <pre>
   * 字段名：商户名称
   * 变量名：merchant_name
   * 是否必填：否
   * 类型：string[1,16]
   * 描述：
   *  商户名称，字数上限为16个，一个中文汉字/英文字母/数字均占用一个字数。
   *  示例值：微信支付
   * </pre>
   */
  @SerializedName(value = "merchant_name")
  private String merchantName;

  /**
   * <pre>
   * 字段名：背景颜色
   * 变量名：background_color
   * 是否必填：否
   * 类型：string[1,16]
   * 描述：
   *  券的背景颜色，可设置10种颜色，色值请参考卡券背景颜色图。颜色取值为颜色图中的颜色名称。
   *  示例值：Color020
   * </pre>
   */
  @SerializedName(value = "background_color")
  private String backgroundColor;

  /**
   * <pre>
   * 字段名：券详情图片
   * 变量名：coupon_image_url
   * 是否必填：否
   * 类型：string[1,128]
   * 描述：
   *  券详情图片，850像素*350像素，且图片大小不超过2M，支持JPG/PNG格式，仅支持通过《图片上传API》接口获取的图片URL地址。
   *  示例值：https://qpic.cn/xxx
   * </pre>
   */
  @SerializedName(value = "coupon_image_url")
  private String couponImageUrl;
}
