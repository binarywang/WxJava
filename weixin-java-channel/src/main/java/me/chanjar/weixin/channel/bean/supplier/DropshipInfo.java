package me.chanjar.weixin.channel.bean.supplier;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代发单信息。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DropshipInfo implements Serializable {
  private static final long serialVersionUID = -7880364210849039278L;

  @JsonProperty("order_id")
  private String orderId;

  @JsonProperty("supplier_id")
  private String supplierId;

  @JsonProperty("ds_order_id")
  private String dropshipId;

  @JsonProperty("status")
  private Integer status;

  @JsonProperty("create_time")
  private Long createTime;

  @JsonProperty("update_time")
  private Long updateTime;
}
