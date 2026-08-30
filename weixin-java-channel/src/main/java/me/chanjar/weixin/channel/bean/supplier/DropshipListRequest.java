package me.chanjar.weixin.channel.bean.supplier;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代发单列表请求。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class DropshipListRequest implements Serializable {
  private static final long serialVersionUID = 2638071229335192596L;

  @JsonProperty("supplier_id")
  private String supplierId;

  @JsonProperty("status")
  private Integer status;

  @JsonProperty("create_time_start")
  private Long createTimeStart;

  @JsonProperty("create_time_end")
  private Long createTimeEnd;

  @JsonProperty("page_size")
  private Integer pageSize;

  @JsonProperty("next_key")
  private String nextKey;
}
