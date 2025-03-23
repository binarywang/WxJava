package me.chanjar.weixin.channel.bean.audit;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新类目树类目ID
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatsV2 implements Serializable {
  private static final long serialVersionUID = -2484092110142035589L;

  /** 新类目树类目ID */
  @JsonProperty("cat_id")
  private String catId;
}
