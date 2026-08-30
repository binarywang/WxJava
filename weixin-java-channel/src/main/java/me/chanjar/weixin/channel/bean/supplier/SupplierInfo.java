package me.chanjar.weixin.channel.bean.supplier;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 供货商信息。
 *
 * @author <a href="https://github.com/github-copilot">GitHub Copilot</a>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class SupplierInfo implements Serializable {
  private static final long serialVersionUID = -6480813119738259476L;

  @JsonProperty("supplier_id")
  private String supplierId;

  @JsonProperty("supplier_name")
  private String supplierName;

  @JsonProperty("status")
  private Integer status;
}
