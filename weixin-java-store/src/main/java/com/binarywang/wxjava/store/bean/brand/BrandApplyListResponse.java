package com.binarywang.wxjava.store.bean.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 品牌申请列表响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BrandApplyListResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = 243021267020609148L;

  /** 品牌资质申请信息 */
  @JsonProperty("brands")
  private List<BasicBrand> brands;

  /** 本次翻页的上下文，用于请求下一页 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 品牌资质总数 */
  @JsonProperty("total_num")
  private Integer totalNum;
}
