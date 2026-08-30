package com.binarywang.wxjava.store.bean.coupon;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 获取优惠券ID列表接口的请求参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CouponListParam implements Serializable {
  private static final long serialVersionUID = 7123047113279657365L;

  /**
   * 优惠券状态 {@link com.binarywang.wxjava.store.enums.WxCouponStatus}
   */
  @JsonProperty("status")
  private Integer status;

  /**
   * 第几页（最小填1）
   */
  @JsonProperty("page")
  private Integer page;

  /**
   * 每页数量(不超过200）
   */
  @JsonProperty("page_size")
  private Integer pageSize;

  /**
   * 分页上下文
   */
  @JsonProperty("page_ctx")
  private String pageCtx;
}
