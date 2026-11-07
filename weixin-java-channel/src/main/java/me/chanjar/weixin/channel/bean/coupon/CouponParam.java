package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 优惠券参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.coupon.CouponParam}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class CouponParam extends CouponIdInfo {

  private static final long serialVersionUID = -3663331372622943337L;
  /** 优惠券类型 **/
  @JsonProperty("type")
  private Integer type;

  /** 优惠券名称，最长10个中文字符 */
  @JsonProperty("name")
  private String name;

  /** 优惠信息 **/
  @JsonProperty("discount_info")
  private DiscountInfo discountInfo;

  /** 额外信息 **/
  @JsonProperty("ext_info")
  private ExtInfo extInfo;

  /** 推广信息 **/
  @JsonProperty("promote_info")
  private PromoteInfo promoteInfo;

  /** 领取信息 **/
  @JsonProperty("receive_info")
  private ReceiveInfo receiveInfo;

  /** 优惠券有效信息 **/
  @JsonProperty("valid_info")
  private ValidInfo validInfo;

  /** 优惠券自动生效信息 **/
  @JsonProperty("auto_valid_info")
  private AutoValidInfo autoValidInfo;
}
