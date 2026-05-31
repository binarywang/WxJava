package me.chanjar.weixin.channel.bean.supplier;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代发单分配请求。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DropshipAssignRequest implements Serializable {
  private static final long serialVersionUID = 6945436332042017565L;

  @JsonProperty("order_id")
  private String orderId;

  @JsonProperty("supplier_id")
  private String supplierId;
}
