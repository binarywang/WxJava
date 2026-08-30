package me.chanjar.weixin.channel.bean.supplier;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 按商品自动分配请求。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class ProductDistributeRequest implements Serializable {
  private static final long serialVersionUID = 4201609097231290078L;

  @JsonProperty("supplier_id")
  private String supplierId;

  @JsonProperty("product_id_list")
  private List<String> productIdList;
}
