package com.github.binarywang.wxpay.bean.marketing;

import com.github.binarywang.wxpay.bean.marketing.busifavor.CouponUseRule;
import com.github.binarywang.wxpay.bean.marketing.busifavor.CustomEntrance;
import com.github.binarywang.wxpay.bean.marketing.busifavor.DisplayPatternInfo;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 查询用户单张券详情API返回对象
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter9_2_5.shtml
 * </pre>
 *
 * @author yujam
 */
@Data
@NoArgsConstructor
public class BusiFavorQueryOneUserCouponsResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * <pre>* 字段名：批次归属商户号
   * 变量名：belong_merchant
   * 是否必填：是
   * 类型：string[8,15]
   * 描述：
   * 批次归属于哪个商户。 示例值：10000022
   * </pre>
   */
  @SerializedName(value = "belong_merchant")
  private String belongMerchant;

  /**
   * <pre>* 字段名：商家券批次名称
   * 变量名：stock_name
   * 是否必填：是
   * 类型：string[1,21]
   * 描述：
   * 批次名称，字数上限为21个，一个中文汉字/英文字母/数字均占用一个字数。 示例值：商家券
   * </pre>
   */
  @SerializedName(value = "stock_name")
  private String stockName;

  /**
   * <pre>* 字段名：批次备注
   * 变量名：comment
   * 是否必填：否
   * 类型：string[1,20]
   * 描述：
   * 仅配置商户可见，用于自定义信息。字数上限为20个，一个中文汉字/英文字母/数字均占用一个字数。 示例值：xxx可用
   * </pre>
   */
  @SerializedName(value = "comment")
  private String comment;

  /**
   * <pre>* 字段名：适用商品范围
   * 变量名：goods_name
   * 是否必填：是
   * 类型：string[1,15]
   * 描述：
   * 适用商品范围，字数上限为15个，一个中文汉字/英文字母/数字均占用一个字数。 示例值：xxx商品可用
   * </pre>
   */
  @SerializedName(value = "goods_name")
  private String goodsName;

  /**
   * <pre>* 字段名：批次类型
   * 变量名：stock_type
   * 是否必填：是
   * 类型：string[1,128]
   * 描述：
   * 批次类型 NORMAL：固定面额满减券批次 DISCOUNT：折扣券批次 EXCHANGE：换购券批次 示例值：NORMAL
   * </pre>
   */
  @SerializedName(value = "stock_type")
  private String stockType;

  /**
   * <pre>* 字段名：是否允许转赠
   * 变量名：transferable
   * 是否必填：否
   * 类型：bool
   * 描述：
   * 不填默认否，枚举值： true：是 false：否 该字段暂未开放 示例值：false
   * </pre>
   */
  @SerializedName(value = "transferable")
  private Boolean transferable;

  /**
   * <pre>* 字段名：是否允许分享领券链接
   * 变量名：shareable
   * 是否必填：否
   * 类型：bool
   * 描述：
   * 不填默认否，枚举值： true：是 false：否 该字段暂未开放 示例值：false
   * </pre>
   */
  @SerializedName(value = "shareable")
  private Boolean shareable;

  /**
   * <pre>* 字段名：券状态
   * 变量名：coupon_state
   * 是否必填：否
   * 类型：string[1,16]
   * 描述：
   * 商家券状态 枚举值： SENDED：可用 USED：已核销 EXPIRED：已过期 示例值：SENDED
   * </pre>
   */
  @SerializedName(value = "coupon_state")
  private String couponState;

  /**
   * <pre>* 字段名：+样式信息
   * 变量名：display_pattern_info
   * 是否必填：否
   * 类型：object
   * 描述：
   * 商家券详细信息
   * </pre>
   */
  @SerializedName(value = "display_pattern_info")
  private DisplayPatternInfo displayPatternInfo;

  /**
   * <pre>* 字段名：+券核销规则
   * 变量名：coupon_use_rule
   * 是否必填：是
   * 类型：券核销规则
   * 描述：
   * 券核销相关规则
   * </pre>
   */
  @SerializedName(value = "coupon_use_rule")
  private CouponUseRule couponUseRule;

  /**
   * <pre>* 字段名：+自定义入口
   * 变量名：custom_entrance
   * 是否必填：否
   * 类型：object
   * 描述：
   * 卡详情页面，可选择多种入口引导用户。
   * </pre>
   */
  @SerializedName(value = "custom_entrance")
  private CustomEntrance customEntrance;

  /**
   * <pre>* 字段名：券code
   * 变量名：coupon_code
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   * 券的唯一标识。 示例值：123446565767
   * </pre>
   */
  @SerializedName(value = "coupon_code")
  private String couponCode;

  /**
   * <pre>* 字段名：批次号
   * 变量名：stock_id
   * 是否必填：否
   * 类型：string[1,20]
   * 描述：
   * 微信为每个商家券批次分配的唯一ID，是否指定批次号查询。 示例值：1002323
   * </pre>
   */
  @SerializedName(value = "stock_id")
  private String stockId;

  /**
   * <pre>* 字段名：券可使用开始时间
   * 变量名：available_start_time
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * 1、用户领取到该张券实际可使用的开始时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.+08:00表示，北京时间2015年5月20日 13点29分35秒。 示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName(value = "available_start_time")
  private String availableStartTime;

  /**
   * <pre>* 字段名：券过期时间
   * 变量名：expire_time
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * 用户领取到该张券的过期时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.+08:00表示，北京时间2015年5月20日 13点29分35秒。 示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName(value = "expire_time")
  private String expireTime;

  /**
   * <pre>* 字段名：券领券时间
   * 变量名：receive_time
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * 用户领取到该张券的时间，遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.+08:00表示，北京时间2015年5月20日 13点29分35秒。 示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName(value = "receive_time")
  private String receiveTime;

  /**
   * <pre>* 字段名：发券请求单号
   * 变量名：send_request_no
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   * 发券时传入的唯一凭证 示例值: MCHSEND202003101234
   * </pre>
   */
  @SerializedName(value = "send_request_no")
  private String sendRequestNo;

  /**
   * <pre>* 字段名：核销请求单号
   * 变量名：use_request_no
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   * 核销时传入的唯一凭证（如券已被核销，将返回此字段） 示例值: MCHUSE202003101234
   * </pre>
   */
  @SerializedName(value = "use_request_no")
  private String useRequestNo;

  /**
   * <pre>* 字段名：券核销时间
   * 变量名：use_time
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   * 券被核销的时间（如券已被核销，将返回此字段）；遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.+08:00表示，北京时间2015年5月20日 13点29分35秒。 示例值：2015-05-20T13:29:35+08:00
   * </pre>
   */
  @SerializedName(value = "use_time")
  private String useTime;
}
