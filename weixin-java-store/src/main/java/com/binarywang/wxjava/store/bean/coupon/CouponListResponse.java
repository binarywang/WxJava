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
public class CouponListResponse extends WxStoreBaseResponse {

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
