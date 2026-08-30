package me.chanjar.weixin.channel.bean.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类目审核信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class CategoryAuditRequest implements Serializable {

  private static final long serialVersionUID = -1151634735247657643L;

  @JsonProperty("category_info")
  private CategoryAuditInfo categoryInfo;
}
