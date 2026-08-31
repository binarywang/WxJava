package me.chanjar.weixin.channel.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.coupon.CouponListResponse}。
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class CouponListResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = -5330296358041282751L;
  /** 优惠券id列表 */
  @JsonProperty("coupons")
  private List<CouponIdInfo> coupons;

  /** 优惠券总数 */
  @JsonProperty("total_num")
  private Integer totalNum;

  /** 优惠券上下文 */
  @JsonProperty("page_ctx")
  private String pageCtx;

}
