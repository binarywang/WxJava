package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.chanjar.weixin.channel.bean.base.StreamPageParam;

/**
 * 赠品列表查询参数
 *
 * @author GitHub Copilot
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.product.GiftProductListParam}。
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class GiftProductListParam extends StreamPageParam {

  private static final long serialVersionUID = 7583500622060651067L;

  /** 赠品状态 */
  @JsonProperty("status")
  private Integer status;

  public GiftProductListParam() {
  }

  public GiftProductListParam(Integer pageSize, String nextKey, Integer status) {
    this.pageSize = pageSize;
    this.nextKey = nextKey;
    this.status = status;
  }
}
