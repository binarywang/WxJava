package com.binarywang.wxjava.store.bean.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 品牌列表响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BrandListResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -5335449078706304920L;

  /** 品牌库中的品牌信息 */
  @JsonProperty("brands")
  private List<BasicBrand> brands;

  /** 本次翻页的上下文，用于请求下一页 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 是否还有下一页内容 */
  @JsonProperty("continue_flag")
  private boolean continueFlag;
}
