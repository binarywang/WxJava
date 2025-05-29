package me.chanjar.weixin.channel.bean.delivery;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品打包信息 参数
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class FreshInspectParam implements Serializable {
  private static final long serialVersionUID = -1635894867602084789L;

  /** 订单ID */
  @JsonProperty("order_id")
  private String orderId;

  /** 商品打包信息 */
  @JsonProperty("audit_items")
  private List<PackageAuditInfo> auditItems;
}
