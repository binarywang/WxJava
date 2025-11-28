package com.github.binarywang.wxpay.bean.marketing.busifavor;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 自定义入口
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_1.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class CustomEntrance implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：小程序入口
   * 变量名：mini_programs_info
   * 是否必填：否
   * 类型：object
   * 描述：
   *  需要小程序APPID、path、入口文案、引导文案。如果需要跳转小程序，APPID、path、入口文案为必填，引导文案非必填。
   *  appid要与归属商户号有M-A or M-m-suba关系
   * </pre>
   */
  @SerializedName(value = "mini_programs_info")
  private MiniProgramsInfo miniProgramsInfo;

  /**
   * <pre>
   * 字段名：商户公众号appid
   * 变量名：appid
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   *  可配置商户公众号，从券详情可跳转至公众号，用户自定义字段。
   *  示例值：wx324345hgfhfghfg
   * </pre>
   */
  @SerializedName(value = "appid")
  private String appId;

  /**
   * <pre>
   * 字段名：营销馆id
   * 变量名：hall_id
   * 是否必填：否
   * 类型：string[1,64]
   * 描述：
   *  填写微信支付营销馆的馆id，用户自定义字段。 营销馆需在商户平台 创建。
   *  示例值：233455656
   * </pre>
   */
  @SerializedName(value = "hall_id")
  private String hallId;

  /**
   * <pre>
   * 字段名：可用门店id
   * 变量名：store_id
   * 是否必填：否
   * 类型：string[1,64]
   * 描述：
   *  填写代金券可用门店id，用户自定义字段。
   *  示例值：233554655
   * </pre>
   */
  @SerializedName(value = "store_id")
  private String storeId;

  /**
   * <pre>
   * 字段名：code展示模式
   * 变量名：code_display_mode
   * 是否必填：否
   * 类型：string[1,8]
   * 描述：
   *  枚举值：
   *   NOT_SHOW：不展示code
   *   BARCODE：一维码
   *   QRCODE：二维码
   *  示例值：BARCODE
   * </pre>
   */
  @SerializedName(value = "code_display_mode")
  private String codeDisplayMode;

  @Data
  @NoArgsConstructor
  public static class MiniProgramsInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * 字段名：商家小程序appid
     * 变量名：mini_programs_appid
     * 是否必填：是
     * 类型：string[1,32]
     * 描述：
     *  商家小程序appid要与归属商户号有M-A or M-m-suba关系。
     *  示例值：wx234545656765876
     * </pre>
     */
    @SerializedName(value = "mini_programs_appid")
    private String miniProgramsAppid;

    /**
     * <pre>
     * 字段名：商家小程序path
     * 变量名：mini_programs_path
     * 是否必填：是
     * 类型：string[1,128]
     * 描述：
     *  商家小程序path
     *  示例值：/path/index/index
     * </pre>
     */
    @SerializedName(value = "mini_programs_path")
    private String miniProgramsPath;

    /**
     * <pre>
     * 字段名：入口文案
     * 变量名：entrance_words
     * 是否必填：是
     * 类型：string[1,5]
     * 描述：
     *  入口文案，字数上限为5个，一个中文汉字/英文字母/数字均占用一个字数。
     *  示例值：欢迎选购
     * </pre>
     */
    @SerializedName(value = "entrance_words")
    private String entranceWords;

    /**
     * <pre>
     * 字段名：引导文案
     * 变量名：guiding_words
     * 是否必填：否
     * 类型：string[1,6]
     * 描述：
     *  小程序入口引导文案，用户自定义字段。字数上限为6个，一个中文汉字/英文字母/数字均占用一个字数。
     *  示例值：获取更多优惠
     * </pre>
     */
    @SerializedName(value = "guiding_words")
    private String guidingWords;
  }
}
