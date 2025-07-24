package me.chanjar.weixin.channel.bean.home.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资金流水响应 结果
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class TreeProductListResult implements Serializable {

  private static final long serialVersionUID = 4566848209585635054L;

  /** 关联的商品ID。如果返回为空，返回翻页到底了 */
  @JsonProperty("product_ids")
  private List<Long> productIds;

  /** 总条数 */
  @JsonProperty("total_count")
  private Integer totalCount;

  /** 拉取下一页的话，需要把这个值填到req的page_context里面 */
  @JsonProperty("page_context")
  private String pageContext;
}
