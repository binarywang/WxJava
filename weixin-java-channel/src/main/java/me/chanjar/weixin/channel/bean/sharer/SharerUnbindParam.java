package me.chanjar.weixin.channel.bean.sharer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 * @deprecated 请迁移至 {@link com.binarywang.wxjava.store.bean.sharer.SharerUnbindParam}。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@Deprecated
public class SharerUnbindParam implements Serializable {

  private static final long serialVersionUID = -4515654492511136037L;
  /** openid列表 */
  @JsonProperty("openid_list")
  private List<String> openIds;
}
