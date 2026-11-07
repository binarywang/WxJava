package com.binarywang.wxjava.store.bean.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.binarywang.wxjava.store.bean.base.WxStoreBaseResponse;

/**
 * 商品列表信息 响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpuListResponse extends WxStoreBaseResponse {

  private static final long serialVersionUID = -7448819335418389308L;

  /** 总数 */
  @JsonProperty("total_num")
  private Integer totalNum;

  /** 本次翻页的上下文，用于请求下一页 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 商品 id 列表 */
  @JsonProperty("product_ids")
  private List<String> ids;
}
