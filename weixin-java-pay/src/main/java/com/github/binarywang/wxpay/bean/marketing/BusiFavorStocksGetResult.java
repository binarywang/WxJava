package com.github.binarywang.wxpay.bean.marketing;

import com.github.binarywang.wxpay.bean.marketing.busifavor.*;
import com.github.binarywang.wxpay.bean.marketing.enums.StockTypeEnum;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商家券详情返回对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_2.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorStocksGetResult {

  private static final long serialVersionUID = 1L;

  /**
   * <pre>
   * 字段名：批次名称
   * 变量名：stock_name
   * 是否必填：是
   * 类型：string[1,21]
   * 描述：
   *  批次名称
   *  校验规则：
   *  1、批次名称最多9个中文汉字
   *  2、批次名称最多20个字母
   *  3、批次名称中不能包含不当内容和特殊字符 _ , ; |
   *  示例值：微信支付代金券批次
   * </pre>
   */
  @SerializedName(value = "stock_name")
  private String stockName;

  /**
   * <pre>
   * 字段名：归属商户号
   * 变量名：belong_merchant
   * 是否必填：是
   * 类型：string[8,15]
   * 描述：
   *  批次归属商户号
   *  该字段暂未开放
   *  示例值：98568865
   * </pre>
   */
  @SerializedName(value = "belong_merchant")
  private String belongMerchant;

  /**
   * <pre>
   * 字段名：批次备注
   * 变量名：comment
   * 是否必填：否
   * 类型：string[1,20]
   * 描述：
   *  仅制券商户可见，用于自定义信息。
   *  校验规则：批次备注最多60个UTF8字符数
   *  示例值：零售批次
   * </pre>
   */
  @SerializedName(value = "comment")
  private String comment;

  /**
   * <pre>
   * 字段名：适用商品范围
   * 变量名：goods_name
   * 是否必填：是
   * 类型：string[1,15]
   * 描述：
   *  用来描述批次在哪些商品可用，会显示在微信卡包中。字数上限为15个，一个中文汉字/英文字母/数字均占用一个字数。
   *  示例值：xxx商品使用
   * </pre>
   */
  @SerializedName(value = "goods_name")
  private String goodsName;

  /**
   * <pre>
   * 字段名：批次类型
   * 变量名：stock_type
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  批次类型
   *  NORMAL：固定面额满减券批次
   *  DISCOUNT：折扣券批次
   *  EXCHANGE：换购券批次
   *  示例值：NORMAL
   * </pre>
   */
  @SerializedName(value = "stock_type")
  private StockTypeEnum stockType;

  /**
   * <pre>
   * 字段名：核销规则
   * 变量名：coupon_use_rule
   * 是否必填：是
   * 类型：object
   * 描述：核销规则
   * </pre>
   */
  @SerializedName(value = "coupon_use_rule")
  private CouponUseRule couponUseRule;

  /**
   * <pre>
   *   字段名：券发放相关规则
   *   变量名：stock_send_rule
   *   是否必填：是
   *   类型：object
   *   描述：券发放相关规则
   * </pre>
   */
  @SerializedName(value = "stock_send_rule")
  private StockSendRule stockSendRule;

  /**
   * <pre>
   * 字段名：商户单据号
   * 变量名：out_request_no
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   *  商户创建批次凭据号（格式：商户id+日期+流水号），可包含英文字母，数字，|，_，*，-等内容，不允许出现其他不合法符号，商户侧需保持商户单据号全局唯一。
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;

  /**
   * <pre>
   *   字段名：自定义入口
   *   变量名：custom_entrance
   *   是否必填：否
   *   类型：object
   *   描述：卡详情页面，可选择多种入口引导用户。
   * </pre>
   */
  @SerializedName(value = "custom_entrance")
  private CustomEntrance customEntrance;

  /**
   * <pre>
   *   字段名：样式信息
   *   变量名：display_pattern_info
   *   是否必填：否
   *   类型：object
   *   描述：创建批次时的样式信息。
   * </pre>
   */
  @SerializedName(value = "display_pattern_info")
  private DisplayPatternInfo displayPatternInfo;

  /**
   * <pre>
   *   字段名：券code模式
   *   变量名：coupon_code_mode
   *   是否必填：是
   *   类型：string[1,128]
   *   描述：枚举值：
   * WECHATPAY_MODE：系统分配券code。（固定22位纯数字）
   * MERCHANT_API：商户发放时接口指定券code。
   * MERCHANT_UPLOAD：商户上传自定义code，发券时系统随机选取上传的券code。
   * </pre>
   */
  @SerializedName(value = "coupon_code_mode")
  private String couponCodeMode;

  /**
   * <pre>
   *   字段名：事件通知配置
   *   变量名：notify_config
   *   是否必填：否
   *   类型：object
   *   描述：事件回调通知商户的配置
   * </pre>
   */
  @SerializedName(value = "notify_config")
  private NotifyConfig notifyConfig;

  /**
   * <pre>
   *   字段名：批次发放情况
   *   变量名：send_count_information
   *   是否必填：否
   *   类型：object
   *   描述：批次发放情况
   * </pre>
   */
  @SerializedName(value = "send_count_information")
  private SendCountInformation sendCountInformation;

  @Data
  @NoArgsConstructor
  public static class SendCountInformation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * 字段名：已发放券张数
     * 变量名：total_send_num
     * 是否必填：否
     * 类型：uint64
     * 描述：
     *  批次已发放的券数量，满减、折扣、换购类型会返回该字段
     *  示例值：1
     * </pre>
     */
    @SerializedName(value = "total_send_num")
    private Integer totalSendNum;

    /**
     * <pre>
     * 字段名：已发放券金额
     * 变量名：total_send_amount
     * 是否必填：否
     * 类型：uint64
     * 描述：
     *  批次已发放的预算金额，满减券类型会返回该字段
     *  示例值：34
     * </pre>
     */
    @SerializedName(value = "total_send_amount")
    private Integer totalSendAmount;

    /**
     * <pre>
     * 字段名：单天已发放券张数
     * 变量名：today_send_num
     * 是否必填：否
     * 类型：uint64
     * 描述：
     *  批次当天已发放的券数量，设置了单天发放上限的满减、折扣、换购类型返回该字段
     *  示例值：1
     * </pre>
     */
    @SerializedName(value = "today_send_num")
    private String todaySendNum;

    /**
     * <pre>
     * 字段名：单天已发放券金额
     * 变量名：today_send_amount
     * 是否必填：否
     * 类型：uint64
     * 描述：
     *  批次当天已发放的预算金额，设置了当天发放上限的满减券类型返回该字段
     *  示例值：34
     * </pre>
     */
    @SerializedName(value = "today_send_amount")
    private String todaySendAmount;
  }
}
