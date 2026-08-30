package com.binarywang.wxjava.store.bean.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserCouponListResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 5201633937239352879L;
  /** 优惠券id列表 */
  @JsonProperty("user_coupon_list")
  private List<UserCouponIdInfo> coupons;

  /** 优惠券总数 */
  @JsonProperty("total_num")
  private Integer totalNum;

  /** 优惠券上下文 */
  @JsonProperty("page_ctx")
  private String pageCtx;
}
